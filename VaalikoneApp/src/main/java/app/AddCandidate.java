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
		name = "AddCandidate",
		urlPatterns = {"/addcandidate"}
		)

public class AddCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public AddCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		String myName = (String)session.getAttribute("uname");
		
		// Checking is there a current session
	    if (myName != null) {
	    	String question = request.getParameter("candidate");
	    	
	    	// Checking if where able to add candidate
	    	if (Dao.addCandidate(candidate) != null) {
	    		response.sendRedirect("/ehdokkaat");
	    	}
	    	
	    	// If not able to add candidate (just in case)
	    	else {
	    		response.sendRedirect("/lisaaEhdokas");
	    	}
	    	
	    }
	    
	    // If there is no session
	    else {
	    	response.sendRedirect("http://localhost:8080/");
	    }
	    
	}

}