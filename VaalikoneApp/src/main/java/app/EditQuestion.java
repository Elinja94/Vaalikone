package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import data.Question;

/**
 * Servlet implementation class EditQuestion
 */
@WebServlet(
	    name = "EditQuestion",
	    urlPatterns = {"/muokkaaKysymysVahvistus"}
	)
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditQuestion() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		String myName = (String)session.getAttribute("uname");
		
		// Checking is there a current session
	    if (myName != null) {
	    	String id = request.getParameter("id");
	    	String ques = request.getParameter("question");
	    	
	    	// Checking is there something in the question
	    	if (id != null && !ques.isEmpty()) {
	    		Question q = new Question(id, ques);
	    		ArrayList<Question> list = null;
	    		list = Dao.updateQuestion(q);
	    		response.sendRedirect("/kysymykset");
		    	
	    	}
	    	
	    	// If no question or id was given
	    	else {
	    		response.sendRedirect("/kysymykset");
	    		
	    	}
	    	
	    }
	    
	    // If there is no session
	    else {
	    	response.sendRedirect("http://localhost:8080/");
	    }
		
	}

}
