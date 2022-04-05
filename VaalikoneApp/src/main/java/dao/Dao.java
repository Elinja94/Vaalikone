package dao;

// Importing everything we need in this app
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;

import data.Question;

public class Dao {

	private static String url;
	private static String user;
	private static String pass;
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
	public static boolean getConnection() {
		
		url = "jdbc:mysql://localhost:3306/vaalikone";
		user= "root";
		pass= "Palvelin";
		
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
		if (getConnection() == true) {
			
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
	
	// Making an arraylist of questions -Sonja
	public static ArrayList<Question> listOfQuestions() {
		ArrayList<Question> questionsList = new ArrayList<>();
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Selecting everything from the kysymykset table 
			try {
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * FROM kysymykset");
				
				
				// Putting data to the arraylist
				while (result.next()) {
					Question ques = new Question();
					ques.setId(result.getInt(1));
					ques.setQuestion(result.getString(2));
					questionsList.add(ques);
					
				}
				result.next();
				return questionsList;
				
			} 
			
			// Just in case if something goes wrong
			catch (SQLException e) {
				return null;
				
			}
		}
		
		return null;
		
	}
	
}
