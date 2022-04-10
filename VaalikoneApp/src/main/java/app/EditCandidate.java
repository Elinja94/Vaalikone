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
import data.Candidate;


@WebServlet(
	    name = "EditCandidate",
	    urlPatterns = {"/muokkaaEhdokasVahvistus"}
	)
public class EditCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCandidate() {
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
	    	String cand = request.getParameter("candidate");
	    	
	    	// Checking is there something in the question
	    	if (id != null && !cand.isEmpty()) {
	    		Candidate c = new Candidate(id, cand);
	    		ArrayList<Candidate> list = null;
	    		list = Dao.updateCandidate(c);
	    		response.sendRedirect("/ehdokkaat");
		    	
	    	}
	    	
	    	// If no question or id was given
	    	else {
	    		response.sendRedirect("/ehdokkaat");
	    		
	    	}
	    	
	    }
	    
	    // If there is no session
	    else {
	    	response.sendRedirect("http://localhost:8080/");
	    }
		
	}

}