package sw.server.models;

/**
 * Message model
 * 
 * @author Lars Kristian
 * 
 */
public class Message {
	private final long id;
	private final long rfid;
	private final int messageType;
	private final long timeSent;
	private final int pulse;
	private final double temperature;
	private final double latitude;
	private final double longitude;

	public Message(long id, long rfid, int messageType, double latitude, double longitude, int pulse, double temperature) {
		super();
		this.id = id;
		this.rfid = rfid;
		this.messageType = messageType;
		this.pulse = pulse;
		this.temperature = temperature;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timeSent = System.currentTimeMillis();
	}

	public long getId() {
		return id;
	}

	public long getRfid() {
		return rfid;
	}

	public int getMessageType() {
		return messageType;
	}

	public long getTimeSent() {
		return timeSent;
	}

	public int getPulse() {
		return pulse;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

}
