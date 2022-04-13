package app;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/ehdokkaankysymykset")
public class CandidateQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao = null;
	ArrayList<Question> questionList=null;
	ArrayList<Integer> questionIdList=new ArrayList<Integer>();
	private String candidateId = null;
	String question = "1";
	
	@Override
	public void init() {
		dao=new Dao();
		
		if (dao != null) {
			questionList=dao.readAllQuestions();
		}
		
		if (questionList != null) {
			for (Question q : questionList) {
				questionIdList.add(q.getId());
			}
		}
	}
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession(false);
		
		if (session != null && session.getAttribute("uname") != null) {
			return true;
		}
		
		response.sendRedirect("http://localhost:8080/");
		return false;
    }
    
    void getQueryString(HttpServletRequest request) {
		String queryString = request.getQueryString();
		question = "1";
		
		if (queryString != null) {
			String[] params  = queryString.split("&"); 
			
			for (String param : params) {
				String[] temp = param.split("=");
				
				if (temp != null && temp.length > 1) {
					if (param.startsWith("question=")) {
						question = temp[1];
					}
					else if (param.startsWith("candidate=")) {
						candidateId = temp[1];
					}
				}
			}			
		}
    }
    
    boolean checkCandidateId(HttpServletResponse response) throws IOException {
    	if (candidateId != null && Integer.parseInt(candidateId) > 0 && Dao.readCandidate(candidateId) != null) {
    		return true;
    	}
    	
    	response.getWriter().println("Missing or invalid candidate id");
    	return false;
    }
    
    boolean checkQuestionList(HttpServletResponse response) throws IOException {
    	if (questionList != null && questionList.size() > 0) {
    		return true;
    	}
    	
    	response.getWriter().println("Couldn't get questions");
    	return false;
    }
    
    boolean checkQuestionId(String questionId, HttpServletResponse response) throws IOException {
    	if (questionId != null && questionIdList.contains(Integer.parseInt(questionId))) {
    		return true;
    	}
    	
    	response.getWriter().println("Missing or invalid question id");
    	return false;
    }
    
    boolean checkAnswer(String answer, HttpServletResponse response) throws IOException {
    	if (answer != null && Integer.parseInt(answer)  >= 1 && Integer.parseInt(answer)  <= 5) {
    		return true;
    	}
    	
    	response.getWriter().println("Missing or invalid answer");
    	return false;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (isAuthenticated(request, response)) {
			getQueryString(request);
			boolean inputValid = checkCandidateId(response) && checkQuestionList(response);	
			
			if (inputValid) {
				if (Integer.parseInt(question) > questionList.size()+1) {
					response.getWriter().println("alert('Vastaukset tallennettu');");
					response.sendRedirect("http://localhost:8080/ehdokkaat");
				}
				
				else {
					for (int i = 0; i<questionList.size(); i++) {
						int questionId = questionList.get(i).getId();
						
						if (questionId == Integer.parseInt(question)) {
							request.setAttribute("question", questionList.get(i));
							
							if (i+1 < questionList.size()) {
								request.setAttribute("nextQuestion", questionList.get(i+1));
							}
							
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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (isAuthenticated(request, response)) {
			String questionId = request.getParameter("questionId");
			String answer = request.getParameter("answer");
			String method = request.getParameter("method");
			boolean inputValid = checkCandidateId(response) && checkQuestionId(questionId, response) && answer != null;
				
			if (method.equals("put")) {
				doPut(request, response);
			}
			
			else if (inputValid) {
					ArrayList<Answer> answers = dao.insertAnswer(candidateId, questionId, answer);	
					if (answers == null) {
						response.getWriter().println("alert('Vastausta ei saatu tallennettua');");
					}
			}
				
			doGet(request,response);
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (isAuthenticated(request, response)) {
			String questionId = request.getParameter("questionId");
			String answer = request.getParameter("answer");
			boolean inputValid = checkCandidateId(response) && checkQuestionId(questionId, response) && answer != null;
			
			if (inputValid) {
				Answer candidateAnswer = dao.readAnswer(candidateId, questionId);
				
				if (candidateAnswer != null && checkAnswer(answer, response)) {
					candidateAnswer.setAnswer(answer);
					ArrayList<Answer> answers = dao.updateAnswer(candidateAnswer);
					if (answers == null) {
						response.getWriter().println("alert('Vastausta ei saatu tallennettua');");
					}
				}
			}
			
			doGet(request,response);
		}
	}
}
