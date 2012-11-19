package sw.server.models;

import java.util.Date;

public class Sheep {

	private long id;
	private long userId;
	private long sheepPid;
	private long rfid;
	private String name;
	private Date dateOfBirth;
	private Date timeOfDeath;
	private String notes;
	private double birthWeight;
	private Date creTime;
	private Date updTime;


	public Sheep(long userId, long sheepPid, long rfid, String name, Date dateOfBirth, Date timeOfDeath, String notes, double birthWeight, boolean attacked) {
		super();
		this.userId = userId;
		this.sheepPid = sheepPid;
		this.rfid = rfid;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.timeOfDeath = timeOfDeath;
		this.notes = notes;
		this.birthWeight = birthWeight;
	}


	public Sheep() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getSheepPid() {
		return sheepPid;
	}


	public void setSheepPid(long sheepPid) {
		this.sheepPid = sheepPid;
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


	public Date getCreTime() {
		return creTime;
	}


	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}


	public Date getUpdTime() {
		return updTime;
	}


	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}


}
