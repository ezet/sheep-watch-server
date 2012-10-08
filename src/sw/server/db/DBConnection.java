package sw.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.Message;


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
	public DBConnection(){
		
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
	
	public ResultSet query(String sql) {
		try {
			return connection.createStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean execute(PreparedStatement st) throws SQLException {
		return st.execute();
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
	
	public void create(String table, List data) {
	}
}
