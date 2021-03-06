package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sw.server.Logger;
import sw.server.models.Contact;

/**
 * Database Access Object for Contacts
 * 
 * @author Lars Kristian
 * 
 */
public class ContactDao extends Dao {

	/**
	 * Finds all contacts for a specific user id
	 * 
	 * @param userId The user ID
	 * @return A list of contacts, or empty list if none are found
	 */
	public List<Contact> findByUserId(long userId) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			st = db.prepareStatement("SELECT * FROM contact WHERE user_id = " + userId + ';');
			rs = st.executeQuery();
			while (rs.next()) {
				contacts.add(getContact(rs));
			}
		} catch (SQLException e) {
			Logger.log(e);
		} finally {
			db.close(st, rs);
		}
		return contacts;
	}

	/**
	 * Binds a ResultSet to a Contact model
	 * 
	 * @param rs The ResultSet to bind
	 * @return The bound contact
	 * @throws SQLException
	 */
	private Contact getContact(ResultSet rs) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getLong("id"));
		contact.setUserId(rs.getLong("user_id"));
		contact.setName(rs.getString("name"));
		contact.setEmail(rs.getString("email"));
		contact.setEmailAlert(rs.getBoolean("email_alert"));
		contact.setCallAlert(rs.getBoolean("call_alert"));
		contact.setSmsAlert(rs.getBoolean("sms_alert"));
		contact.setCreTime(rs.getTimestamp("cre_time"));
		contact.setUpdTime(rs.getTimestamp("upd_time"));
		return contact;
	}
}
