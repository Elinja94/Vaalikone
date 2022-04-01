package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerDao;
import dao.QuestionDao;
import data.Answer;
import data.Question;

/**
 * Servlet implementation class CandidateQuestions
 */
@WebServlet("/candidatequestions")
public class CandidateQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDao questionDao=null;
	private AnswerDao answerDao=null;
	private String candidateId = "5";
	
	@Override
	public void init() {
		questionDao=new QuestionDao("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
		answerDao=new AnswerDao("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
	}
	 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryString = request.getQueryString();
		String question = "1";
		
		if (queryString != null) {
			String[] params  = queryString.split("&"); 
			
			for (String param : params) {
				if (param.startsWith("question=")) {
					String[] temp = param.split("=");
					question = temp[1];
				}
			}			
		}
		
		ArrayList<Question> questionList=null;
		if (questionDao.getConnection()) {
			questionList=questionDao.readAllQuestions();
		}
		else {
			System.out.println("No connection to database");
		}
		
		for (int i = 0; i<questionList.size(); i++) {
			int questionId = questionList.get(i).getId();
			
			if (questionId == Integer.parseInt(question)) {
				request.setAttribute("question", questionList.get(i));
				Answer candidateAnswer = null;
				
				if (answerDao.getConnection()) {
					candidateAnswer = answerDao.readAnswer(candidateId, Integer.toString(questionId));
				} else {
					System.out.println("No connection to database");
				}
				
				if (candidateAnswer == null) {
					RequestDispatcher rd=request.getRequestDispatcher("/jsp/addanswerform.jsp");		
					rd.forward(request, response);	
				} else {
					response.getWriter().append("Question already answered");
				}			
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (answerDao.getConnection()) {			
			String questionId = request.getParameter("questionId");
			String answer = request.getParameter("answer");
				
				if (candidateId == null) {
					response.getWriter().println("Candidate id is missing");
				} else if (questionId == null) {
					response.getWriter().println("Question id is missing");
				} else  {
					if (answer != null) {
						try {
							answerDao.insertAnswer(candidateId, questionId, answer);
							doGet(request,response);
						} catch (Exception e) {
							response.getWriter().println("An error occurred");
							System.out.println(e);
						}						
					}
				}
		}
		else {
			System.out.println("No connection to database");
		}
	}
}
