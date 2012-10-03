package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import sw.server.Message;

public class MessageDao {
	private DBConnection connection; 
	
	public MessageDao(DBConnection connection) {
		
	}
	
	public void insert(Message message) {
		
		PreparedStatement st;
		try {
			st = connection.getPreparedStatement("INSERT INTO Message VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			 st.setLong(0,message.getMessageId());
			 st.setLong(1, message.getSheepId());
			 st.setString(2,message.getType().toString());
			 st.setInt(3,message.getTimeSent());
			 st.setInt(4,message.getTimeReceived());
			 st.setString(5,message.getGpsData().toString());
			 st.setInt(6, message.getPulse());
			 st.setDouble(7, message.getTemperature());
			 //flere verdier!!!
			connection.execute(st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> getContacts(int rfid) {
		//lag en user klasse
		return null;
	}

}
