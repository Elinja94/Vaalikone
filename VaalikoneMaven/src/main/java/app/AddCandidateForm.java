package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(
	    name = "AddCandidateForm",
	    urlPatterns = {"/admin/lisaaEhdokas"}
	)
public class AddCandidateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddCandidateForm() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		//HttpSession session=request.getSession(false);
				
		// Checking is there a current session and then displays the form
	    //if (session != null && session.getAttribute("uname") != null) {
	    	RequestDispatcher rd = request.getRequestDispatcher("/jsp/AddCandidate.jsp");
			rd.forward(request, response);
			
	    //}
	    
	    // If there is no session
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    	
//	    }
	    
	}

}