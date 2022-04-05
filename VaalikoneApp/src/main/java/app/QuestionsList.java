package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;


/**
 * Servlet implementation class QuestionsList
 */
@WebServlet("/QuestionsList")
public class QuestionsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionsList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Question> listOfQuestions = null;
		if (Dao.getConnection() == true) {
			listOfQuestions = Dao.listOfQuestions();
			
		}
		
		else {
			System.out.println("Not connected to the database");
			
		}

		request.setAttribute("questionsList", listOfQuestions);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/QuestionsList.jsp");
		rd.forward(request, response);
		
	}

}
