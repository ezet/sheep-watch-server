package sw.server.models;

public class Event {

	public enum MessageType {
		UPDATE, ALARM, EXCEPTION
	};

	public final long messageId;

	public final long rfid;

	public final MessageType type;
	public final long timeSent;
	public final long timeReceived;
	public final int pulse;
	public final double temperature;
	public final long latitude;
	public final long longitude;

	public Event(Message message) {
		this.messageId = message.getId();
		this.rfid = message.getRfid();
		this.type = MessageType.valueOf(String.valueOf(message.getMessageType()));
		this.timeSent = message.getTimeSent();
		this.timeReceived = 0;
		this.pulse = message.getPulse();
		this.temperature = message.getTemperature();
		this.latitude = message.getLatitude();
		this.longitude = message.getLongitude();
	}

	public Event(long messageId, long rfid, MessageType type, int timeSent, int timeReceived, int pulse,
			double temperature, long latitude, long longitude) {
		super();
		this.messageId = messageId;
		this.rfid = rfid;
		this.type = type;
		this.timeSent = timeSent;
		this.timeReceived = timeReceived;
		this.pulse = pulse;
		this.temperature = temperature;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getMessageId() {
		return messageId;
	}

	public long getRfid() {
		return rfid;
	}

	public MessageType getType() {
		return type;
	}

	public long getTimeSent() {
		return timeSent;
	}

	public long getTimeReceived() {
		return timeReceived;
	}

	public int getPulse() {
		return pulse;
	}

	public double getTemperature() {
		return temperature;
	}

	public long getLatitude() {
		return latitude;
	}

	public long getLongitude() {
		return longitude;
	}

}
