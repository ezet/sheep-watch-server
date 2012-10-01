package sw.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sw.server.Message;

public class DBConnection {
	String dburl;
	String user;
	String password;
	Connection connection;
	
	public DBConnection(String dburl, String user, String password){
		this.dburl="jdbc:mysql:"+dburl;
		this.user=user;
		this.password=password;
	}
	public void setUpConnection(){
		try {
			connection = DriverManager.getConnection(dburl,user,password);
			
		} catch (SQLException e) {
			System.out.println("Wrong url, user and/or password");
			e.printStackTrace();
		}
	}
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("You can't just cut me off!!");
			e.printStackTrace();
		}
	}
	public void doQuery(){
		PreparedStatement pstmt;
		//vet ikke hvordan vi skal sende queriet
		try {
			pstmt=connection.prepareStatement("",new int[42]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	void useLessMethod(Message message){
		try{
		PreparedStatement pstmt;
		//Register the JDBC driver for MySQL.
		//ikke nødvendig lenger?
		 Class.forName("com.mysql.jdbc.Driver");
		 
		//Define URL of database server
		 String url="jdbc:mysql:"+dburl;
		 
		 //setting up connection to DB
		 Connection con = DriverManager.getConnection(dburl,user,password);
		 
		 //Get a Statement object
		 pstmt = con.prepareStatement("INSERT INTO message(messageid,sheepid,messagetype,timesent,timereceived,location,pulse,temprature) VALUES (?,?)");
		 
		 pstmt.setLong(0,message.getMessageId());
		 pstmt.setLong(1, message.getSheepId());
		 pstmt.setString(2,message.getType().toString());
		 pstmt.setInt(3,message.getTimeSent());
		 pstmt.setInt(4,message.getTimeReceived());
		 pstmt.setString(5,message.getGpsData().toString());
		 pstmt.setInt(6, message.getPulse());
		 pstmt.setDouble(7, message.getTemperature());
		 //execute
		 pstmt.executeUpdate("INSERT something INTO something");
		 
		 con.close();
		}
		catch(Exception e){
		}
	}
}
