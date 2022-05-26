// Made by Sonja
package app;

import java.io.IOException;
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
import data.Kysymykset;

@WebServlet(
	    name = "QuestionsList",
	    urlPatterns = {"/admin/kysymykset"}
	)
public class QuestionsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuestionsList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Information needed to check session status
		response.setContentType("text/html");
		//HttpSession session=request.getSession(false);
				
		// Checking is there a current session
	    //if (session != null && session.getAttribute("uname") != null) {
			//ArrayList<Question> listOfQuestions = null;
			
			// Getting information of the questions
			if (Dao.getConnection() == true) {
//				listOfQuestions = Dao.listOfQuestions();
//				request.setAttribute("questionsList", listOfQuestions);
				EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	    		EntityManager em = emf.createEntityManager();
	    		
				em.getTransaction().begin();
				List<Kysymykset> questionsList=em.createQuery("select k from Kysymykset k").getResultList();
				em.getTransaction().commit();

				request.setAttribute("questionsList", questionsList);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/QuestionsList.jsp");
				rd.forward(request, response);
				
			}
			
			// Can't connect to database
			else {
				System.out.println("Not connected to the database");
				
			}
			
	    //}
	    
	    // If there is no session
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    }
	}

}
