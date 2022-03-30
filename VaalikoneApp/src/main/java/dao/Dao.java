package dao;

// Importing everything we need in this app
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Dao {

	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	// All the information that is needed for the database connection -Sonja
	public Dao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	// Checking the connection to the database -Sonja
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } 
	            
	            catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	            
	            conn = DriverManager.getConnection(url, user, pass);
	        }
	        return true;
		}
		
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
