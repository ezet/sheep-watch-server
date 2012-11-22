package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import sw.server.Logger;
import sw.server.models.User;

/**
 * Data Access Object for User models
 * 
 * @author Lars Kristian
 * 
 */
public class UserDao extends Dao {

	/**
	 * Inserts a new User
	 * 
	 * @param user The user to insert
	 * @return The user that was insterted, with the new generated ID
	 */
	public User insert(User user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = db.prepareStatement("INSERT INTO user (producer_id, username, password, name, access_level, cre_time, upd_time) VALUES (?, ?, ?, ?, ?, ?, ?)");
			setParams(user, st);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.log(e);
		} finally {
			db.close(st, rs);
		}
		return user;
	}

	/**
	 * Maps a user to a PreparedStatement
	 * 
	 * @param user The user to map
	 * @param st The statement to map to
	 * @throws SQLException
	 */
	private void setParams(User user, PreparedStatement st) throws SQLException {
		int i = 1;
		st.setLong(i++, user.getProducerId());
		st.setString(i++, user.getUsername());
		st.setString(i++, user.getPassword());
		st.setString(i++, user.getName());
		st.setInt(i++, user.getAccessLevel());
		st.setTimestamp(i++, new Timestamp(user.getCreTime().getTime()));
		st.setTimestamp(i++, new Timestamp(user.getCreTime().getTime()));
	}

}
