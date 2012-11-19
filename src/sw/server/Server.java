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

public class Server implements Runnable {

	private volatile boolean run;
	private volatile MessageBuffer buffer = new MessageBuffer();
	private ServerCLI ui;
	private EventDao eventDao;
	private SheepDao sheepDao;
	private ContactDao contactDao;

	public Server(ServerCLI ui, EventDao messageDao) {
		this.ui = ui;
		this.eventDao = messageDao;
		this.sheepDao = new SheepDao();
		this.contactDao = new ContactDao();
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
			Sheep sheep = sheepDao.findByRfid(event.getRfid());
			event.setSheepId(sheep.getId());
			eventDao.insert(event);
			if (event.getMessageType() == MessageType.ALARM) {
				List<Contact> contacts = contactDao.findByUserId(sheep.getUserId());
				for (Contact contact: contacts) {
					if (contact.isEmailAlert()) {
						Logger.log("Sending mail to: " + contact.getEmail());
					Email.send(contact.getName(), contact.getEmail(), sheep);
					}
				}
			} else {

			}
		}
	}
	private ArrayList<Contact> generateContacts(){
		ArrayList<Contact> list=new ArrayList<Contact>();
		list.add(new Contact("Espen","espenhellerud5@gmail.com"));
		
		return list;
	}
}
