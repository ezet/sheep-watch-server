package sw.server.db;

import java.util.ArrayList;

/**
 * Abstract base class for data access objects, handles errors and db connection
 * 
 * @author Lars Kristian
 * 
 */
public abstract class Dao {

	protected Dao() {
		db = DBConnection.getInstance();
	}

	protected DBConnection db = null;
	protected ArrayList<String> errors;

	/**
	 * Adds an error
	 * @param msg Error message
	 */
	protected void addError(String msg) {
		errors.add(msg);
	}

}