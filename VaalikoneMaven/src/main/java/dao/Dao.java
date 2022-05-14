package dao;

// Importing everything we need in this app
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import data.Answer;
import data.Question;
import data.Candidate;

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
		
		url = "jdbc:mysql://localhost:3306/vaalikone?useSSL=false&allowPublicKeyRetrieval=false";
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
	
	// Getting information from specific question with specific id -Sonja
	public static Question readQuestion(String id) {
		Question q = new Question();
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Selecting everything from the kysymykset table with the given id and preparing them to be used
			try {
				PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM kysymykset WHERE KYSYMYS_ID=?");
				pstmt.setString(1, id);
				ResultSet RS = pstmt.executeQuery();
				
				RS.next();	
				q.setId(RS.getInt(1));
				q.setQuestion(RS.getString(2));
				System.out.print(q);
				
				return q;
				
			} 
			
			// Just in case if something goes wrong
			catch (SQLException e) {
				return null;
				
			}
		}
		
		return null;
		
	}
	
	// Adding question to the database -Sonja
	public static ArrayList<Question> addQuestion(String Question) {
		
		ArrayList<Question> questionsList = listOfQuestions();

		// Connection to the database
		if (getConnection() == true) {
			
			// Adding with the given question information
			try {
				PreparedStatement pstmt=conn.prepareStatement("INSERT INTO kysymykset (KYSYMYS_ID, KYSYMYS) values(?, ?)");
				PreparedStatement largestID = conn.prepareStatement("SELECT MAX(KYSYMYS_ID) FROM kysymykset");
				ResultSet RS = largestID.executeQuery();
				RS.next();
				pstmt.setInt(1, RS.getInt(1)+1);
				pstmt.setString(2, Question);
				pstmt.execute();
				return questionsList;
			} 
			
			// Just in case if something goes wrong
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	
	// Updating question information to the database -Sonja
		public static ArrayList<Question> updateQuestion(Question q) {
						
			// Connection to the database
			if (getConnection() == true) {
				
				// Updating with the given question information
				try {
					PreparedStatement pstmt=conn.prepareStatement("UPDATE kysymykset SET KYSYMYS=? where KYSYMYS_ID=?");
					pstmt.setString(1, q.getQuestion());
					pstmt.setInt(2, q.getId());
					pstmt.executeUpdate();
					return listOfQuestions();
				} 
				
				// Just in case if something goes wrong
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return null;

		}
	
	// Delete question from the database with specific id -Sonja
	public static ArrayList<Question> deleteQuestion (String id) {
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Deleting from database with the given id
			try {
				PreparedStatement pstmt=conn.prepareStatement("DELETE FROM kysymykset WHERE KYSYMYS_ID=?");
				pstmt.setString(1, id);
				pstmt.execute();
				return listOfQuestions();
			} 
			
			// Just in case if something goes wrong
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	// Making an arraylist of candidates -Kaisa
	public static ArrayList<Candidate> listOfCandidates() {
		ArrayList<Candidate> candidatesList = new ArrayList<>();
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Selecting everything from the ehdokkaat table 
			try {
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("SELECT * FROM ehdokkaat");
				
				// Putting data to the arraylist
				while (result.next()) {
					Candidate cand = new Candidate();
					cand.setId(result.getInt(1));
					cand.setSukunimi(result.getString(2));
					cand.setEtunimi(result.getString(3));
					cand.setPuolue(result.getString(4));
					cand.setKotipaikkakunta(result.getString(5));
					cand.setIka(result.getInt(6));
					cand.setMiksi_eduskuntaan(result.getString(7));
					cand.setMita_asioita_haluat_edistaa(result.getString(8));
					cand.setAmmatti(result.getString(9));
					candidatesList.add(cand);
					
				}
				result.next();
				return candidatesList;
				
			} 
			
			// Just in case if something goes wrong
			catch (SQLException e) {
				return null;
				
			}
		}
		
		return null;
		
	}
	
	// Getting information from specific candidate with specific id -Kaisa
	public static Candidate readCandidate(String id) {
		Candidate c = new Candidate();
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Selecting everything from the ehdokkaat table with the given id and preparing them to be used
			try {
				PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM ehdokkaat WHERE EHDOKAS_ID=?");
				pstmt.setString(1, id);
				ResultSet RS = pstmt.executeQuery();
				
				RS.next();	
				c.setId(RS.getInt(1));
				c.setSukunimi(RS.getString(2));
				c.setEtunimi(RS.getString(3));
				c.setPuolue(RS.getString(4));
				c.setKotipaikkakunta(RS.getString(5));
				c.setIka(RS.getInt(6));
				c.setMiksi_eduskuntaan(RS.getString(7));
				c.setMita_asioita_haluat_edistaa(RS.getString(8));
				c.setAmmatti(RS.getString(9));
				System.out.print(c);
				
				return c;
				
			} 
			
			// Just in case if something goes wrong
			catch (SQLException e) {
				return null;
				
			}
		}
		
		return null;
		
	}
	
	// Adding candidate to the database -Kaisa
	public static ArrayList<Candidate> addCandidate(String sukunimi, String etunimi, String puolue, String kotipaikkakunta, String ika, String miksi_eduskuntaan, String mita_asioita_haluat_edistaa, String ammatti ) {
		
		ArrayList<Candidate> candidatesList = listOfCandidates();
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Adding with the given question information
			try {
				PreparedStatement pstmt=conn.prepareStatement("INSERT INTO ehdokkaat (EHDOKAS_ID, SUKUNIMI, ETUNIMI, PUOLUE, KOTIPAIKKAKUNTA, IKA, MIKSI_EDUSKUNTAAN, MITA_ASIOITA_HALUAT_EDISTAA, AMMATTI) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
				PreparedStatement largestID = conn.prepareStatement("SELECT MAX(EHDOKAS_ID) FROM ehdokkaat");
				ResultSet RS = largestID.executeQuery();
				RS.next();
				pstmt.setInt(1, RS.getInt(1)+1);
				pstmt.setString(2, sukunimi);
				pstmt.setString(3, etunimi);
				pstmt.setString(4, puolue);
				pstmt.setString(5, kotipaikkakunta);
				pstmt.setInt(6, Integer.parseInt(ika));
				pstmt.setString(7, miksi_eduskuntaan);
				pstmt.setString(8, mita_asioita_haluat_edistaa);
				pstmt.setString(9, ammatti);

				pstmt.execute();
				return candidatesList;
			} 
			
			// Just in case if something goes wrong
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	// Updating candidate information to the database -Kaisa
	public static ArrayList<Candidate> updateCandidate(Candidate c) {
					
		// Connection to the database
		if (getConnection() == true) {
			
			// Updating with the given candidates information
			try {
				PreparedStatement pstmt=conn.prepareStatement("UPDATE ehdokkaat SET SUKUNIMI=?, ETUNIMI=?, PUOLUE=?, KOTIPAIKKAKUNTA=?, IKA=?, MIKSI_EDUSKUNTAAN=?, MITA_ASIOITA_HALUAT_EDISTAA=?, AMMATTI=?  where EHDOKAS_ID=? ");
				pstmt.setString(1, c.getSukunimi());
				pstmt.setString(2, c.getEtunimi());
				pstmt.setString(3, c.getPuolue());
				pstmt.setString(4, c.getKotipaikkakunta());
				pstmt.setInt(5, c.getIka());
				pstmt.setString(6, c.getMiksi_eduskuntaan());
				pstmt.setString(7, c.getMita_asioita_haluat_edistaa());
				pstmt.setString(8, c.getAmmatti());
				pstmt.setInt(9, c.getId());
		
				
				pstmt.executeUpdate();
				return listOfCandidates();
			} 
			
			// Just in case if something goes wrong
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	
	// Delete candidate from the database with specific id -Kaisa
	public static ArrayList<Candidate> deleteCandidate (String id) {
		
		// Connection to the database
		if (getConnection() == true) {
			
			// Deleting from database with the given id
			try {
				PreparedStatement pstmt=conn.prepareStatement("DELETE FROM ehdokkaat WHERE EHDOKAS_ID=?");
				pstmt.setString(1, id);
				pstmt.execute();
				return listOfCandidates();
			} 
			
			// Just in case if something goes wrong
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
		
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
	
	public ArrayList<Answer> updateAnswer(Answer a) {
		if (getConnection() == true) {
			try {
				String sql="update vastaukset set VASTAUS=? where EHDOKAS_ID=? and KYSYMYS_ID=?";
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, a.getAnswer());
				pstmt.setInt(2, a.getCandidateId());
				pstmt.setInt(3, a.getQuestionId());
				pstmt.executeUpdate();
				return readCandidateAnswers("" + a.getCandidateId());
			}
			catch(Exception e) {
				System.out.println(e);
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
