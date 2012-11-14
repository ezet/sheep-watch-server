package sw.server.simulator;

import java.sql.SQLException;
import java.util.Calendar;

import sw.server.MessageBuffer;
import sw.server.ServerCLI;
import sw.server.db.DBConnection;
import sw.server.db.SheepDao;
import sw.server.db.UserDao;
import sw.server.models.Message;
import sw.server.models.Sheep;
import sw.server.models.User;

public class MessageSimulator implements Runnable {

	private ServerCLI ui;
	private volatile boolean run;
	private volatile MessageBuffer buffer;

	private long maxRfid = 1;
	private long nextRfid = 1;
	private long msgId;
	private long updateInterval;

	public MessageSimulator(ServerCLI ui, MessageBuffer buffer) {
		this.buffer = buffer;
		this.ui = ui;
		updateInterval = UpdateInterval();
	}
	
	public void reset() {
	}

	public void run() {
		resetDb();
		generateInserts();
		run = true;
		while (run) {
			buffer.put(generateMessage());

			try {
				Thread.sleep(updateInterval);
			} catch (InterruptedException e) {
				run = false;
				return;
			}
		}
	}

	public void alert() {
		buffer.put(generateAlert());
	}

	private Message generateAlert() {
		return new Message(msgId++, maxRfid, 0, 0, 0, 0, 0);
	}

	public long UpdateInterval() {
		return 3600000 / ((Config.NUM_PRODUCERS * Config.NUM_SHEEP * Config.DAILY_UPDATES) / 24);
	}

	public void stop() {
		run = false;
	}

	private Message generateMessage() {
		nextRfid = nextRfid > maxRfid ? 1 : nextRfid; 
		return new Message(msgId++, nextRfid++, 0, 0, 0, 0, 0);
	}
	
	private void resetDb() {
		try {
			DBConnection db = new DBConnection();
			db.getStatement().execute("DELETE FROM Event");
			db.getStatement().execute("DELETE FROM Sheep");
			db.getStatement().execute("DELETE FROM User");
			db.getStatement().execute("DELETE FROM Contact");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateInserts() {
		UserDao userDao = new UserDao();
		SheepDao sheepDao = new SheepDao();
		for (int i = 1; i <= Config.NUM_PRODUCERS; ++i) {
			userDao.insert(new User(i, i +" @username", "password", "name", 5));
			for (int j = 1; j <= Config.NUM_SHEEP; ++j) {
				sheepDao.insert(new Sheep(i, j, maxRfid++, "name", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "notes", 50, false));
			}
		}
		System.out.println("Inserts complete.");
	}

}
