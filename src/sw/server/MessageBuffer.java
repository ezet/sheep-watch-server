package sw.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import sw.server.models.Message;

/**
 * Handles inserting and retrieving messages from a blocking queue
 * @author Lars Kristian
 * 
 */
public class MessageBuffer {

	private volatile BlockingQueue<Message> buffer = new ArrayBlockingQueue<Message>(200);

	/**
	 * Retrieves the next message in the queue
	 * @return The next message
	 */
	public Message take() {
		Message message = null;
		try {
			message = buffer.take();
		} catch (InterruptedException e) {
			Logger.debug(e);
		}
		return message;
	}

	/**
	 * Inserts a new message in the queue
	 * @param message
	 */
	public void put(Message message) {
		// TODO implement prioritizing
		try {
			buffer.put(message);
		} catch (InterruptedException e) {
			Logger.debug(e);
		}
	}

}
