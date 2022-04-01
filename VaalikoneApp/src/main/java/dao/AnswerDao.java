package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Answer;

public class AnswerDao {
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	public AnswerDao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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
	
	public ArrayList<Answer> readCandidateAnswers(String candidateId) {
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
			return null;
		}
	}
	public ArrayList<Answer> insertAnswer(String candidateId, String questionId, String answer) {
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
			System.out.println(e);
			return null;
		}
	}
	
	public Answer readAnswer(String candidateId, String questionId) {
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
			System.out.print(e);
			return null;
		}
	}
}
