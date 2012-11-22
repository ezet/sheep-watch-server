package sw.server;

import java.util.ArrayList;
import java.util.List;

import sw.server.db.ContactDao;
import sw.server.db.EventDao;
import sw.server.db.SheepDao;
import sw.server.models.Contact;
import sw.server.models.Event;
import sw.server.models.Event.MessageType;
import sw.server.models.Message;
import sw.server.models.Sheep;

/**
 * Handles most of the server logic and operations
 * 
 * @author Lars Kristian
 * 
 */
public class Server implements Runnable {

	private volatile boolean run;
	private volatile MessageBuffer buffer = new MessageBuffer();
	private ServerCLI ui;
	private EventDao eventDao;
	private SheepDao sheepDao;
	private ContactDao contactDao;

	/**
	 * Constructor
	 * 
	 * @param ui The UI
	 * @param messageDao Database access object for messages
	 */
	public Server(ServerCLI ui, EventDao messageDao) {
		this.ui = ui;
		this.eventDao = messageDao;
		this.sheepDao = new SheepDao();
		this.contactDao = new ContactDao();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		run = true;
		while (run) {
			processMessage(buffer.take());
		}
	}

	/**
	 * Signals the thread to stop
	 */
	public void stop() {
		run = false;
	}

	/**
	 * Returns the buffer the server uses
	 * 
	 * @return The buffer
	 */
	public MessageBuffer getBuffer() {
		return buffer;
	}

	/**
	 * Processes a single message
	 * 
	 * @param message The message to process
	 */
	public void processMessage(Message message) {
		if (message != null) {
			Event event = new Event(message);
			Sheep sheep = sheepDao.findByRfid(event.getRfid());
			if (sheep == null) {
				return;
			}
			event.setSheepId(sheep.getId());
			eventDao.insert(event);
			if (event.getMessageType() == MessageType.ALARM) {
				List<Contact> contacts = contactDao.findByUserId(sheep.getUserId());
				for (Contact contact : contacts) {
					if (contact.isEmailAlert()) {
						Logger.log("Sending mail to: " + contact.getEmail());
						Email.send(contact.getName(), contact.getEmail(), sheep);
					}
				}
			}
		}
	}

}
