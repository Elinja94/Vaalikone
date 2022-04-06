package dao;

// Importing everything we need in this app
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Answer;
import data.Question;

import java.sql.Connection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		pass= "root";
		
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
	
	
	// Questions
	public ArrayList<Question> readAllQuestions() {
		if (getConnection() == true) {
			ArrayList<Question> list=new ArrayList<>();
			try {
				Statement stmt=conn.createStatement();
				ResultSet RS=stmt.executeQuery("select * from kysymykset");
				while (RS.next()){
					Question q=new Question();
					q.setId(RS.getInt("KYSYMYS_ID"));
					q.setQuestion(RS.getString("KYSYMYS"));
					list.add(q);
				}
				return list;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	// Answers
	public ArrayList<Answer> readCandidateAnswers(String candidateId) {
		if (getConnection() == true) {
			ArrayList<Answer> list=new ArrayList<>();
			try {			
				String sql = "select * from vastaukset where EHDOKAS_ID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(candidateId));
				ResultSet RS=pstmt.executeQuery();
				
				while (RS.next()){
					Answer a=new Answer();
					a.setCandidateId(RS.getInt("EHDOKAS_ID"));
					a.setQuestionId(RS.getInt("KYSYMYS_ID"));
					a.setAnswer(RS.getString("VASTAUS"));
					list.add(a);
				}
				return list;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public ArrayList<Answer> insertAnswer(String candidateId, String questionId, String answer) {
		if (getConnection() == true) {
			try {
				String sql="insert into vastaukset (EHDOKAS_ID, KYSYMYS_ID, VASTAUS, KOMMENTTI) values(?, ?, ?, ?)";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(candidateId));
				pstmt.setInt(2, Integer.parseInt(questionId));
				pstmt.setString(3, answer);
				pstmt.setString(4, String.format("ehdokkaan %s vastaus kysymykseen %s", candidateId, questionId));
				pstmt.execute();
				return readCandidateAnswers(candidateId);
			} 
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public Answer readAnswer(String candidateId, String questionId) {
		if (getConnection() == true) {
			Answer a=null;
			try {
				String sql = "select * from vastaukset where EHDOKAS_ID=? and KYSYMYS_ID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, candidateId);
				pstmt.setString(2, questionId);
				ResultSet RS=pstmt.executeQuery();
			
				while (RS.next()){
					a=new Answer();

					a.setCandidateId(RS.getInt("EHDOKAS_ID"));
					a.setQuestionId(RS.getInt("KYSYMYS_ID"));
					a.setAnswer(RS.getString("VASTAUS"));
					a.setComment(RS.getString("KOMMENTTI"));
				}
				return a;
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
