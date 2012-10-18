package sw.server;

import java.util.List;

import sw.server.db.MessageDao;
import sw.server.models.Event;
import sw.server.models.Event.MessageType;
import sw.server.models.Message;
import sw.server.models.User;

public class Server implements Runnable {

	private volatile boolean run;
	private volatile MessageBuffer buffer = new MessageBuffer();
	private ServerCLI ui;
	private MessageDao messageDao;

	public Server(ServerCLI ui, MessageDao messageDao) {
		this.ui = ui;
		this.messageDao = messageDao;
	}

	public void run() {
		run = true;
		while (run) {
			processMessage(buffer.take());
		}
	}

	public void stop() {
		run = false;
	}

	public MessageBuffer getBuffer() {
		return buffer;
	}

	public void processMessage(Message message) {
		if (message != null) {
			Event event = new Event(message);

			messageDao.insert(event);
			if (event.getType() == MessageType.ALARM) {
				List<User> users = messageDao.getContacts(event.getRfid());
				for (User user : users) {
					// contact em!!
				}
			} else {

			}

		}

	}

}
