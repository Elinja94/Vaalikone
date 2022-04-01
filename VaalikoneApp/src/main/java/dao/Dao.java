package dao;

// Importing everything we need in this app
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Dao {

	private String url;
	private String user;
	private String pass;
	private static Connection conn;
	
	// Script to encrypt the password (teacher's code) -Sonja
	public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encrypt cannot be null or zero length");
            
        }

        MessageDigest digester;
        
        try {
            digester = MessageDigest.getInstance("MD5");

            digester.update(str.getBytes());
            byte[] hash = digester.digest();
            StringBuffer hexString = new StringBuffer();
            
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                    
                } 
                
                else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                    
                }
            }
            
            return hexString.toString();
            
        } 
        
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            
        }
        
        return "";
    }
	
	// Checking the connection to the database -Sonja
	public static boolean getConnection(String url, String user, String pass) {
		
		url=url;
		user=user;
		pass=pass;
		
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

	//Checking if password and username are correct -Sonja
	public static boolean checkLogin(String user, String pass) {
		
		// Connection to the database
		if (getConnection("jdbc:mysql://localhost:3306/vaalikone", "root", "Palvelin") == true) {
			
			// Selecting only the password from the database that matches the given username
			try {
				String getUser = "SELECT kayttajaSalasana FROM kayttajat WHERE kayttajaTunnus='" + user + "'";
				
				Statement userGet = conn.createStatement();
				ResultSet result = userGet.executeQuery(getUser);
				result.next();
				
				// Checks if the given password and the password from the database are the same
				if (result.getString(1).equals(pass)) {
					return true;
				
				}
			} 
			
			// Just in case if something goes wrong
			catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		// If everything went wrong automatic false so can't progress further
		return false;
		
	}
}
