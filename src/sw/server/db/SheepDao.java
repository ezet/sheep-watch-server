package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import sw.server.Logger;
import sw.server.models.Sheep;

/**
 * 
 * Data Access Object for Sheep
 * 
 * @author Lars Kristian
 * 
 */
public class SheepDao extends Dao {

	/**
	 * Stores a new sheep
	 * 
	 * @param sheep The sheep to store
	 * @return The sheep that was stored, with the new generated ID
	 */
	public Sheep insert(Sheep sheep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = db.prepareStatement("INSERT INTO sheep (user_id, sheep_pid, rfid, name, date_of_birth, birth_weight, notes, cre_time, upd_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			setParams(sheep, st);
			st.executeUpdate();
			rs = st.getGeneratedKeys();
			if (rs.next()) {
				sheep.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.log(e);
		} finally {
			db.close(st, rs);
		}
		return sheep;
	}

	/**
	 * Finds a Sheep by its RFID
	 * 
	 * @param rfid The rfid to find
	 * @return The Sheep object found, or null if it wasnt found
	 */
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

	/**
	 * Maps a ResultSet to a Sheep model
	 * 
	 * @param rs The ResultSet to map
	 * @return The created Sheep
	 * @throws SQLException
	 */
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

	/**
	 * Maps a Sheep model to a PreparedStatement
	 * 
	 * @param sheep The sheep to map
	 * @param st The statement to map to
	 * @throws SQLException
	 */
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
