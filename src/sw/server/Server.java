package sw.server;

import java.util.List;

import sw.server.db.MessageDao;
import sw.server.model.Message;
import sw.server.model.User;
import sw.server.model.Message.MessageType;

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
			messageDao.insert(message);
			if (message.getType() == MessageType.ALARM) {
				List<User> users = messageDao.getContacts(message.getRfid());
				for (User user : users) {
					// contact em!!
				}
			} else {

			}

		}

	}

}
