package sw.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import sw.server.Config;
import sw.server.Logger;

/**
 * Handles DB connectivity, functions as a wrapper for a jdbc connection
 * 
 * @author Lars Kristian
 * 
 */
public class DBConnection {

	private Connection conn = null;
	private Properties props = new Properties();

	private static DBConnection instance;

	public static DBConnection getInstance() {
		return instance = (instance == null) ? new DBConnection() : instance;
	}

	/**
	 * Constructor
	 */
	public DBConnection() {
		props.setProperty("user", Config.DB_USER);
		props.setProperty("password", Config.DB_PASSWORD);
	}

	/**
	 * Creates and returns a new Statement object for the database
	 * 
	 * @return The new statement
	 * @throws SQLException
	 */
	public Statement getStatement() throws SQLException {
		connect();
		Statement st = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			Logger.log(e);
			throw e;
		}
		return st;
	}

	/**
	 * Creates and returns a new PreparedStatement object
	 * 
	 * @param sql The sql to be used in the statement
	 * @return The new PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		connect();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			Logger.log(e);
			throw e;
		}
		return st;
	}

	/**
	 * Closes the provided statement
	 * 
	 * @param st
	 */
	public void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			Logger.log(e);
		}
	}

	/**
	 * Closes the provided parameters
	 * 
	 * @param st
	 * @param rs
	 */
	public void close(Statement st, ResultSet rs) {
		close(conn, st, rs);
	}

	/**
	 * Closes all input parameters
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public void close(Connection conn, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			// if (conn != null)
			// conn.close();
		} catch (Exception e) {
			Logger.log(e);
		}
	}

	/**
	 * Closes the current connection
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			Logger.log(e);
		}
	}

	/**
	 * Sets up a connection to a database
	 * 
	 * @throws SQLException
	 */
	private void connect() throws SQLException {
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(Config.JDBC_DRIVER + Config.DB_ADDRESS + "/" + Config.DB_NAME, props);
			}
		} catch (SQLException e) {
			Logger.log("Cannot connect to db.");
			Logger.debug(e);
			throw e;
		}
	}

}
