package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import javax.ws.rs.PathParam;

import dao.Dao;
import data.Candidate;
import data.Ehdokkaat;
import data.Kysymykset;
import data.Question;
import data.Vastaukset;

/**
 * Servlet implementation class Questions
 */
@WebServlet("/ehdokas")
public class CandidateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidateInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
				
	   
    	String id = request.getParameter("id");
    	Candidate c = null;
    	
    	ArrayList<Question> listOfQuestions = null;
    	
    	// Making sure that there is an id so can get the information about question to the form
    	if (id != null && Dao.getConnection() == true) {	
    		c = Dao.readCandidate(id);
    		listOfQuestions = Dao.listOfQuestions();
    		
    		EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
    		EntityManager em=emf.createEntityManager();
    		
    		em.getTransaction().begin();
    		Ehdokkaat ehdokas = em.find(Ehdokkaat.class, Integer.parseInt((id)));
    		//List<Ehdokkaat> candidatelist=em.createQuery("select e from Ehdokkaat e").getResultList();
    		List<Vastaukset> candidateAnswers=em.createQuery("select v from Vastaukset v").getResultList();
    		em.getTransaction().commit();
    		
    		List<Vastaukset> filteredAnswers = candidateAnswers
    				.stream()
    				.filter(a -> a.getCandidateId() == Integer.parseInt(id))
    				.collect(Collectors.toList());
    		
    		request.setAttribute("candidateAnswers", filteredAnswers);
    		//request.setAttribute("candidate", c);
    		request.setAttribute("questionsList", listOfQuestions);
    		request.setAttribute("ehdokas", ehdokas);
    		RequestDispatcher rd=request.getRequestDispatcher("/jsp/CandidateInfo.jsp");
    		rd.forward(request, response);
	    	
    	}
    	
    	// If there is no id
    	else {
    		response.sendRedirect("/candidates");
    		System.out.println("Not connected to the database");
    		
    	}
    	
	    
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