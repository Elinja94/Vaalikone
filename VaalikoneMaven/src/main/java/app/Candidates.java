package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
 * Servlet implementation class Questions
 */
@WebServlet("/candidates")
public class Candidates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Candidates() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		ArrayList<Candidate> listOfCandidates = null;
		if (Dao.getConnection() == true) {
			listOfCandidates = Dao.listOfCandidatesByParty();
			
		}
		
		else {
			System.out.println("Not connected to the database");
			
		}
		
		request.setAttribute("candidatesList", listOfCandidates);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Candidates.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String candidate = request.getParameter("1");
		
		PrintWriter out=response.getWriter();
		out.println(candidate);
	}

}
