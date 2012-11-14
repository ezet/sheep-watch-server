package sw.server.models;

import java.util.Date;

public class User {

	private long id;
	private long producerId;
	private String username;
	private String password;
	private String name;
	private int accessLevel;
	private Date timeCreated;

	public User(long producerId, String username, String password, String name, int accessLevel) {
		super();
		this.producerId = producerId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.accessLevel = accessLevel;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String toString() {
		return "User(" + username + ")";
	}

}
