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
		
		public Sheep findByRfid(long rfid) {
			PreparedStatement st = null;
			ResultSet rs = null;
			Sheep sheep = null;
			try {
				st = db.prepareStatement("SELECT * FROM sheep WHERE rfid = " + rfid + ';');
				rs = st.executeQuery();
				if (rs.next()) {
					sheep = createSheep(rs);
				}
			} catch (SQLException e) {
				Logger.log(e);
			} finally {
				db.close(st, rs);
			}
			return sheep;
		}
		
		private Sheep createSheep(ResultSet rs) throws SQLException {
			Sheep sheep = new Sheep();
			sheep.setId(rs.getLong("id"));
			sheep.setSheepPid(rs.getLong("sheep_pid"));
			sheep.setUserId(rs.getLong("user_id"));
			sheep.setRfid(rs.getLong("rfid"));
			sheep.setName(rs.getString("name"));
			sheep.setBirthWeight(rs.getDouble("birth_weight"));
			sheep.setDateOfBirth(rs.getTimestamp("date_of_birth"));
			sheep.setNotes(rs.getString("notes"));
			sheep.setCreTime(rs.getTimestamp("cre_time"));
			sheep.setUpdTime(rs.getTimestamp("upd_time"));
			return sheep;
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
