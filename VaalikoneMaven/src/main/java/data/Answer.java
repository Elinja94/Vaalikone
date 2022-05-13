package data;

public class Answer {
	private int candidateId;
	private int questionId;
	private String answer;
	private String comment;
	public Answer(String candidateId, String questionId, String answer, String comment) {
		setCandidateId(candidateId);
		setQuestionId(questionId);
		this.answer=answer;
		this.comment=comment;
	}
	public Answer() {
		// TODO Auto-generated constructor stub
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public void setCandidateId(String candidateId) {
		try {
			this.candidateId = Integer.parseInt(candidateId);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public void setQuestionId(String questionId) {
		try {
			this.questionId = Integer.parseInt(questionId);
		}
		catch(NumberFormatException | NullPointerException e) {

		}
	}
	
	public String getAnswer() {
		return this.answer;
	}
	public void setAnswer(String answer) {
		this.answer= answer;
	}
	
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
