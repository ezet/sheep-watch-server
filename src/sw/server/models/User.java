package sw.server.models;


public class User {

	public long id;

	public final long producerId;

	public String username;

	public String password;

	public int timeCreated;
	public boolean isAdmin;
	public Contact contactInfo;

	public User() {
		producerId = 0;
	}

	public User(long producerId, Contact contactInfo) {
		this.contactInfo = contactInfo;
		this.producerId = producerId;
	}

	public User(long id, long producerId, String username, String password, int timeCreated, boolean isAdmin) {
		this.id = id;
		this.producerId = producerId;
		this.username = username;
		this.password = password;
		this.timeCreated = timeCreated;
		this.isAdmin = isAdmin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(int timeCreated) {
		this.timeCreated = timeCreated;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Contact getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(Contact contactInfo) {
		this.contactInfo = contactInfo;
	}

	public long getProducerId() {
		return producerId;
	}

	public String toString() {
		return "User(" + username + ")";
	}

}
