package sw.server.models;

import java.util.Calendar;

public class Message {
	private final long id;
	private final long rfid;
	private final int messageType;
	private final long timeSent;
	private final int pulse;
	private final double temperature;
	private final long latitude;
	private final long longitude;

	public Message(long id, long rfid, int messageType, long longitude, long latitude, int pulse,
			double temperature) {
		super();
		this.id = id;
		this.rfid = rfid;
		this.messageType = messageType;
		this.pulse = pulse;
		this.temperature = temperature;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timeSent = Calendar.getInstance().getTimeInMillis();
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

	public long getLongitude() {
		return longitude;
	}

	public long getLatitude() {
		return latitude;
	}

}
