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
import data.Candidate;

@WebServlet(
	    name = "EditCandidateForm",
	    urlPatterns = {"/muokkaaEhdokas"}
	)
public class EditCandidateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCandidateForm() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
				
		// Checking is there a current session
	    if (session != null && session.getAttribute("uname") != null) {
	    	String id = request.getParameter("id");
	    	Candidate c = null;
	    	
	    	// Making sure that there is an id so can get the information about question to the form
	    	if (id != null) {	
	    		c = Dao.readCandidate(id);
	    		
	    		request.setAttribute("candidate", c);
	    		RequestDispatcher rd=request.getRequestDispatcher("/jsp/EditCandidate.jsp");
	    		rd.forward(request, response);
		    	
	    	}
	    	
	    	// If there is no id
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