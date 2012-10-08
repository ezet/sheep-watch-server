package sw.server.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sw.server.model.Contact;
import sw.server.model.Message;
import sw.server.model.User;

public class MessageDao {
	private DBConnection connection; 
	
	public MessageDao(DBConnection connection) {
		
	}
	
	public void insert(Message message) {
		
		PreparedStatement st;
		try {
			st = connection.getPreparedStatement("INSERT INTO Message VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			 st.setLong(0,message.getMessageId());
			 st.setLong(1, message.getRfid());
			 st.setString(2,message.getType().toString());
			 st.setInt(3,message.getTimeSent());
			 st.setInt(4,message.getTimeReceived());
			 st.setDouble(5, message.getGpsData().getLongitude());
			 st.setDouble(6,message.getGpsData().getLatitude());
			 st.setInt(7, message.getPulse());
			 st.setDouble(8, message.getTemperature());
			connection.execute(st);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> getContacts(long rfid) {
		List<User> users = new ArrayList<User>();
		try {
			ResultSet query = connection.query("SELECT producerId FROM Sheep WHERE rfid="+rfid);
			int producerId=query.getInt("producerId");
			query = connection.query("SELECT * FROM Contact WHERE producerId="+producerId);
			while(query.next()){
				String name=query.getString("name");
				String email=query.getString("Email");
				String phoneSMS=query.getString("phoneSMS");
				String phoneCall=query.getString("phoneCall");
				users.add(new User(producerId, new Contact(name, email, phoneSMS, phoneCall)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

}
