package sw.server.simulator;

import sw.server.MessageBuffer;
import sw.server.ServerCLI;
import sw.server.models.Message;

public class MessageSimulator implements Runnable {

	private ServerCLI ui;
	volatile private boolean run;
	private volatile MessageBuffer buffer;
	private int nOfDailyUpdates = 3;
	private int nOfSheep = 10000;

	private long rfid;
	private long msgId;

	public MessageSimulator(ServerCLI ui, MessageBuffer buffer) {
		this.buffer = buffer;
		this.ui = ui;
	}

	public void run() {
		run = true;
		while (run) {
			buffer.put(generateMessage());

			try {
				Thread.sleep(getUpdateInterval());
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
		return new Message(msgId++, rfid, 0, 0, 0, 0, 0, 0);
	}

	public long getUpdateInterval() {
		return 3600000 / (nOfSheep * nOfDailyUpdates) / 24;
	}

	public void stop() {
		run = false;
	}

	private Message generateMessage() {
		return new Message(msgId++, rfid++, 0, 0, 0, 0, 0, 0);
	}

}
