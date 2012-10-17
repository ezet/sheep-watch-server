package sw.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import sw.server.models.Message;

public class MessageBuffer {

	private volatile BlockingQueue<Message> buffer = new ArrayBlockingQueue<Message>(200);

	public Message take() {
		Message message = null;
		try {
			message = buffer.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("interrupt");
			// e.printStackTrace();
		}
		return message;
	}

	public void put(Message message) {
		try {
			buffer.put(message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

}
