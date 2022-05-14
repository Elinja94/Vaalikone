package data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Kysymykset.findAll", query="SELECT k FROM Kysymykset k")
public class Kysymykset implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int KYSYMYS_ID;
	private String KYSYMYS;
	
	public Kysymykset(String id, String question) {
		setId(id);	
		this.KYSYMYS=question;
	}
	public Kysymykset() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return KYSYMYS_ID;
	}
	public void setId(int id) {
		this.KYSYMYS_ID = id;
	}
	public void setId(String id) {
		try {
			this.KYSYMYS_ID = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public String getQuestion() {
		return this.KYSYMYS;
	}
	public void setQuestion(String question) {
		this.KYSYMYS = question;
	}
	
}
