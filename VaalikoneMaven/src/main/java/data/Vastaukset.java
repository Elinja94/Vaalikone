package data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;

final class VastauksetId implements Serializable {
	private static final long serialVersionUID = 1L;
	private int EHDOKAS_ID;
	private int KYSYMYS_ID;
	
	public VastauksetId(int EHDOKAS_ID, int KYSYMYS_ID) {
		this.EHDOKAS_ID = EHDOKAS_ID;
		this.KYSYMYS_ID = KYSYMYS_ID;
	}
}

@Entity
@IdClass(VastauksetId.class)
@NamedQuery(name="Vastaukset.findAll", query="SELECT v FROM Vastaukset v")
public class Vastaukset implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int EHDOKAS_ID;
	@Id
	private int KYSYMYS_ID;
	private String VASTAUS;
	private String KOMMENTTI;
	
	public Vastaukset() {
		super();
	}
	
	public Vastaukset(String candidateId, String questionId, String answer, String comment) {
		setCandidateId(candidateId);
		setQuestionId(questionId);
		this.VASTAUS=answer;
		this.KOMMENTTI=comment;
	}
	public int getCandidateId() {
		return EHDOKAS_ID;
	}
	public void setCandidateId(int candidateId) {
		this.EHDOKAS_ID = candidateId;
	}
	public void setCandidateId(String candidateId) {
		try {
			this.EHDOKAS_ID = Integer.parseInt(candidateId);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public int getQuestionId() {
		return KYSYMYS_ID;
	}
	public void setQuestionId(int questionId) {
		this.KYSYMYS_ID = questionId;
	}
	public void setQuestionId(String questionId) {
		try {
			this.KYSYMYS_ID = Integer.parseInt(questionId);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public String getAnswer() {
		return this.VASTAUS;
	}
	public void setAnswer(String answer) {
		this.VASTAUS= answer;
	}
	
	public String getComment() {
		return this.KOMMENTTI;
	}
	public void setComment(String comment) {
		this.KOMMENTTI = comment;
	}
	
}