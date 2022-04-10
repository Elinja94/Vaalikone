package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import data.Answer;
import data.Question;

/**
 * Servlet implementation class CandidateQuestions
 */
@WebServlet("/CandidateQuestions")
public class CandidateQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;
	private String candidateId = "6";
	
	@Override
	public void init() {
		dao=new Dao();
	}
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		
		if (session != null && session.getAttribute("uname") != null) {
			return true;
		}
		
		return false;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (isAuthenticated(request)) {
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
			questionList=dao.readAllQuestions();
			
			if (questionList == null) {
				response.getWriter().println("Couldn't get questions");
			}
			
			else {
				for (int i = 0; i<questionList.size(); i++) {
					int questionId = questionList.get(i).getId();
					
					if (questionId == Integer.parseInt(question)) {
						request.setAttribute("question", questionList.get(i));
						Answer candidateAnswer = null;
						
						candidateAnswer = dao.readAnswer(candidateId, Integer.toString(questionId));
						
						if (candidateAnswer == null) {
							request.setAttribute("method", "post");
						} else {
							request.setAttribute("method", "put");
						}			
						
						RequestDispatcher rd=request.getRequestDispatcher("/jsp/AnswerForm.jsp");		
						rd.forward(request, response);	
					}
				}
			}
		}

		else {
			response.sendRedirect("http://localhost:8080/");
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (isAuthenticated(request)) {
			String questionId = request.getParameter("questionId");
			String answer = request.getParameter("answer");
			String method = request.getParameter("method");
				
			if (method.equals("put")) {
				doPut(request, response);
			}
			
			else {
				if (answer != null) {
					dao.insertAnswer(candidateId, questionId, answer);					
				}
			}
				
			doGet(request,response);	
		}
		
		else {
			response.sendRedirect("http://localhost:8080/");
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (isAuthenticated(request)) {
			String questionId = request.getParameter("questionId");
			String answer = request.getParameter("answer");
			
			Answer candidateAnswer = dao.readAnswer(candidateId, questionId);
			candidateAnswer.setAnswer(answer);
			
			if (answer != null) {
				dao.updateAnswer(candidateAnswer);
				doGet(request,response);					
			}
		}
		
		else {
			response.sendRedirect("http://localhost:8080/");
		}
	}
}
