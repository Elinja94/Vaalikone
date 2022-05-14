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
import data.Candidate;


/**
 * Servlet implementation class QuestionsList
 */
@WebServlet(
	    name = "CandidatesList",
	    urlPatterns = {"/admin/ehdokkaat"}
	)
public class CandidatesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatesList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		//HttpSession session=request.getSession(false);
				
	    //if (session != null && session.getAttribute("uname") != null) {
			ArrayList<Candidate> listOfCandidates = null;
			if (Dao.getConnection() == true) {
				listOfCandidates = Dao.listOfCandidates();
				
			}
			
			else {
				System.out.println("Not connected to the database");
				
			}
			
			request.setAttribute("candidatesList", listOfCandidates);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/CandidatesList.jsp");
			rd.forward(request, response);
	    //}
	    
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    }
	}

}