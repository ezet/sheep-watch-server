package sw.server;

import java.util.List;

import sw.server.db.EventDao;
import sw.server.models.Contact;
import sw.server.models.Event;
import sw.server.models.Event.MessageType;
import sw.server.models.Message;
import sw.server.models.User;

public class Server implements Runnable {

	private volatile boolean run;
	private volatile MessageBuffer buffer = new MessageBuffer();
	private ServerCLI ui;
	private EventDao messageDao;

	public Server(ServerCLI ui, EventDao messageDao) {
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
			System.out.println("Message processed.");
			if (event.getMessageType() == MessageType.ALARM) {
				List<Contact> contacts = messageDao.getContacts(event.getRfid());
				for (Contact contact: contacts) {
					// contact em!!
				}
			} else {

			}

		}

	}

}
