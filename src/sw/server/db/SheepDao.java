package sw.server.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import sw.server.Logger;
import sw.server.models.Sheep;


public class SheepDao extends Dao {
	
		
		public int insert(Sheep sheep) {
			PreparedStatement st = null;
			ResultSet rs = null;
			int rows = 0;
			try {
				st = db.prepareStatement("INSERT INTO sheep (user_id, sheep_pid, rfid, name, date_of_birth, birth_weight, notes, cre_time, upd_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				setParams(sheep, st);
				rows = st.executeUpdate();
				rs = st.getGeneratedKeys();
				if (rs.next()) {
					sheep.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				Logger.log(e);
			} finally {
				db.close(st, rs);
			}
			return rows;
		}
		
		public long findByRfid(long rfid) {
			PreparedStatement st = null;
			ResultSet rs = null;
			long id = -1;
			try {
				st = db.prepareStatement("SELECT id FROM sheep WHERE rfid = " + rfid + ';');
				rs = st.executeQuery();
				if (rs.next()) {
					id = rs.getLong("id");
				}
			} catch (SQLException e) {
				Logger.log(e);
			} finally {
				db.close(st, rs);
			}
			return id;
		}
		
		private void setParams(Sheep sheep, PreparedStatement st) throws SQLException {
			int i = 1;
			st.setLong(i++, sheep.getUserId());
			st.setLong(i++, sheep.getSheepPid());
			st.setLong(i++, sheep.getRfid());
			st.setString(i++, sheep.getName());
			st.setTimestamp(i++, new Timestamp(sheep.getDateOfBirth().getTime()));
			st.setDouble(i++, sheep.getBirthWeight());
			st.setString(i++, sheep.getNotes());
			st.setTimestamp(i++, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(i++, new Timestamp(System.currentTimeMillis()));
		}
}
