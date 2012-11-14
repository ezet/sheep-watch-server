package sw.server.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sw.server.Logger;
import sw.server.models.Sheep;
import sw.server.models.User;


public class SheepDao extends Dao {
	
		
		public int insert(Sheep sheep) {
			PreparedStatement st = null;
			ResultSet rs = null;
			int rows = 0;
			try {
				st = db.prepareStatement("INSERT INTO Sheep (producer_id, sheep_id, rfid, name, date_of_birth, time_of_death, birth_weight, notes, attacked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
		
		private void setParams(Sheep sheep, PreparedStatement st) throws SQLException {
			int i = 1;
			st.setLong(i++, sheep.getProducerId());
			st.setLong(i++, sheep.getSheepId());
			st.setLong(i++, sheep.getRfid());
			st.setString(i++, sheep.getName());
			st.setDate(i++, new Date(sheep.getDateOfBirth().getTime()));
			st.setDate(i++, new Date(sheep.getTimeOfDeath().getTime()));
			st.setDouble(i++, sheep.getBirthWeight());
			st.setString(i++, sheep.getNotes());
			st.setBoolean(i++, sheep.isAttacked());
		}
		



}
