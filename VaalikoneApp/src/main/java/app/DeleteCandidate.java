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

@WebServlet(
	    name = "DeleteCandidate",
	    urlPatterns = {"/poistaEhdokas"}
	)
public class DeleteCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteCandidate() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		String myName = (String)session.getAttribute("uname");
		
		// Checking is there a current session
	    if (myName != null) {
	    	String id = request.getParameter("id");
	    	ArrayList<Candidate> list = null;
	    	
	    	// Checking that there is given an id so can delete
	    	if (id != null) {
	    		list = Dao.deleteCandidate(id);
		    	response.sendRedirect("/ehdokkaat");
		    	
	    	}
	    	
	    	// If no id was given
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
