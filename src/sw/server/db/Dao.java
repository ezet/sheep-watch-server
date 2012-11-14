package sw.server.db;


import java.util.ArrayList;


public abstract class Dao {
	
	protected Dao() {
		db = DBConnection.getInstance();
	}
	
	protected DBConnection db = null;
	protected ArrayList<String> errors;
	
	protected void addError(String msg) {
		errors.add(msg);
	}
	
}