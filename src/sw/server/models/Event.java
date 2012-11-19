package sw.server.models;

import java.util.Date;

public class Event {

	public enum MessageType {
		UPDATE(0), ALARM(1), EXCEPTION(2);
		
		public final int index;
		
		private MessageType(int n) {
			index = n;
		}
		
		public static MessageType valueOf(int n) {
			MessageType type = null;
			if (n == 0) {
				type = MessageType.UPDATE;
			} else if (n == 1) {
				type = MessageType.ALARM;
			} else if (n == 2) {
				type = MessageType.EXCEPTION;
			}
			return type;
		}
	};

	public long id;

	public long rfid;
	
	public long sheepId;

	public MessageType messageType;
	public Date timeSent;
	public Date timeReceived;
	public int pulse;
	public double temperature;
	public double latitude;
	public double longitude;

	public Event(Message message) {
		this.id = message.getId();
		this.rfid = message.getRfid();
		this.messageType = MessageType.valueOf(message.getMessageType());
		this.timeSent = new Date(message.getTimeSent());
		this.timeReceived = new Date(System.currentTimeMillis());
		this.pulse = message.getPulse();
		this.temperature = message.getTemperature();
		this.latitude = message.getLatitude();
		this.longitude = message.getLongitude();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getSheepId() {
		return sheepId;
	}

	public void setSheepId(long sheepId) {
		this.sheepId = sheepId;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getRfid() {
		return rfid;
	}

	public void setRfid(long rfid) {
		this.rfid = rfid;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Date getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Date timeSent) {
		this.timeSent = timeSent;
	}

	public Date getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(Date timeReceived) {
		this.timeReceived = timeReceived;
	}

	public int getPulse() {
		return pulse;
	}

	public void setPulse(int pulse) {
		this.pulse = pulse;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	
	

}
