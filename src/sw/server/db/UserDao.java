package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sw.server.Logger;
import sw.server.models.User;

public class UserDao extends Dao {
	
	public int insert(User user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		int rows = 0;
		try {
			st = db.prepareStatement("INSERT INTO User (producer_id, username, password, name, access_level) VALUES (?, ?, ?, ?, ?)");
			setParams(user, st);
			rows = st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.log(e);
		} finally {
			db.close(st, rs);
		}
		return rows;
	}
	private void setParams(User user, PreparedStatement st) throws SQLException {
		int i = 1;
		st.setLong(i++, user.getProducerId());
		st.setString(i++, user.getUsername());
		st.setString(i++, user.getPassword());
		st.setString(i++, user.getName());
		st.setInt(i++, user.getAccessLevel());
	}
	

}
