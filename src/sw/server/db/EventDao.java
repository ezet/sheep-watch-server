package sw.server.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sw.server.Logger;
import sw.server.models.Contact;
import sw.server.models.Event;
import sw.server.models.Sheep;

public class EventDao extends Dao {
	
	public int insert(Event event) {
		PreparedStatement st = null;
		ResultSet rs = null;
		int rows = 0;
		try {
			st = db.prepareStatement("INSERT INTO Event (rfid, time_sent, message_type, longitude, latitude, pulse, temperature) VALUES (?, ?, ?, ?, ?, ?, ?)");
			setParams(event, st);
			rows = st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				event.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.log(e);
		} finally {
			db.close(st, rs);
		}
		return rows;
	}
	
	private void setParams(Event event, PreparedStatement st) throws SQLException {
		int i = 1;
		st.setLong(i++, event.getRfid());
		st.setDate(i++, new Date(event.getTimeSent().getTime()));
		st.setInt(i++, event.getMessageType().index);
		st.setDouble(i++, event.getLongitude());
		st.setDouble(i++, event.getLatitude());
		st.setInt(i++, event.getPulse());
		st.setDouble(i++, event.getTemperature());
	}

	
	public List<Contact> getContacts(long id) {
		return new ArrayList<Contact>();
	}


}
