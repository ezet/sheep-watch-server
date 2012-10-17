package sw.server.models;

public class Message {

	public enum MessageType {
		UPDATE, ALARM, EXCEPTION
	};

	public final long id;

	public final long producerId;

	public final long rfid;

	public Sheep sheep;

	public final MessageType type;
	public final int timeSent;
	public final int timeReceived;
	public final GpsData gpsData;
	public final int pulse;
	public final double temperature;

	public Message(long id, long producerId, long rfid, MessageType type, int timeSent, int timeReceived,
			GpsData gpsData, int pulse, double temperature) {
		super();
		this.id = id;
		this.producerId = producerId;
		this.rfid = rfid;
		this.type = type;
		this.timeSent = timeSent;
		this.timeReceived = timeReceived;
		this.gpsData = gpsData;
		this.pulse = pulse;
		this.temperature = temperature;
	}
	
	public long getMessageId() {
		return id;
	}

	public long getRfid() {
		return rfid;
	}

	public MessageType getType() {
		return type;
	}

	public int getTimeSent() {
		return timeSent;
	}

	public int getTimeReceived() {
		return timeReceived;
	}

	public GpsData getGpsData() {
		return gpsData;
	}

	public int getPulse() {
		return pulse;
	}

	public double getTemperature() {
		return temperature;
	}
}
