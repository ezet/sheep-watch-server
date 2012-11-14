package sw.server.models;

import java.util.Date;

public class Sheep {

	private long id;
	private long producerId;
	private long sheepId;
	private long rfid;
	private String name;
	private Date dateOfBirth;
	private Date timeOfDeath;
	private String notes;
	private double birthWeight;
	private boolean attacked;
	private Date timeAdded;


	public Sheep(long producerId, long sheepId, long rfid, String name, Date dateOfBirth, Date timeOfDeath, String notes, double birthWeight, boolean attacked) {
		super();
		this.producerId = producerId;
		this.sheepId = sheepId;
		this.rfid = rfid;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.timeOfDeath = timeOfDeath;
		this.notes = notes;
		this.birthWeight = birthWeight;
		this.attacked = attacked;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProducerId() {
		return producerId;
	}

	public void setProducerId(long producerId) {
		this.producerId = producerId;
	}

	public long getSheepId() {
		return sheepId;
	}

	public void setSheepId(long sheepId) {
		this.sheepId = sheepId;
	}

	public long getRfid() {
		return rfid;
	}

	public void setRfid(long rfid) {
		this.rfid = rfid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getTimeOfDeath() {
		return timeOfDeath;
	}

	public void setTimeOfDeath(Date timeOfDeath) {
		this.timeOfDeath = timeOfDeath;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getBirthWeight() {
		return birthWeight;
	}

	public void setBirthWeight(double birthWeight) {
		this.birthWeight = birthWeight;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public Date getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

}
