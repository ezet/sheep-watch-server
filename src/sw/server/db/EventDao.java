package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import sw.server.Logger;
import sw.server.models.Contact;
import sw.server.models.Event;

/**
 * Data Access Object for Events
 * 
 * @author Lars Kristian
 * 
 */
public class EventDao extends Dao {

	/**
	 * Inserts a new Event row in the database
	 * 
	 * @param event The event to insert
	 * @return The event object that was inserted, with the generated ID
	 */
	public Event insert(Event event) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = db.prepareStatement("INSERT INTO Event (sheep_id, time_sent, time_received, message_type, longitude, latitude, pulse, temperature, rfid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			setParams(event, st);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				event.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.log(e);
		} finally {
			db.close(st, rs);
		}
		return event;
	}

	/**
	 * Maps an event to a PrepatedStatement
	 * 
	 * @param event The event to map
	 * @param st The statement to map to
	 * @throws SQLException
	 */
	private void setParams(Event event, PreparedStatement st) throws SQLException {
		int i = 1;
		st.setLong(i++, event.getSheepId());
		st.setTimestamp(i++, new Timestamp(event.getTimeSent().getTime()));
		st.setTimestamp(i++, new Timestamp(event.getTimeReceived().getTime()));
		st.setInt(i++, event.getMessageType().index);
		st.setDouble(i++, event.getLongitude());
		st.setDouble(i++, event.getLatitude());
		st.setInt(i++, event.getPulse());
		st.setDouble(i++, event.getTemperature());
		st.setLong(i++, event.getRfid());
	}

}
