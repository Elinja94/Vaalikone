package data;

public class Candidate {
	private int id;
	private String candidate;
	public Candidate(String id, String candidate) {
		setId(id);
		this.candidate=candidate;
	}
	public Candidate() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public String getCandidate() {
		return this.candidate;
	}
	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}
	
}
