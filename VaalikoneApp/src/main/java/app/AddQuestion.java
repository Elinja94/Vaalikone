// Made by Sonja
package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

@WebServlet(
	    name = "AddQuestion",
	    urlPatterns = {"/lisaaKysymysVahvistus"}
	)
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddQuestion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
				
		// Checking is there a current session
	    if (session != null && session.getAttribute("uname") != null) {
	    	String question = request.getParameter("question");
	    	
	    	// Checking if where able to add question
	    	if (Dao.addQuestion(question) != null) {
	    		response.sendRedirect("/kysymykset");
	    	}
	    	
	    	// If not able to add question (just in case)
	    	else {
	    		response.sendRedirect("/lisaaKysymys");
	    	}
	    	
	    }
	    
	    // If there is no session
	    else {
	    	response.sendRedirect("http://localhost:8080/");
	    }
	    
	}

}
