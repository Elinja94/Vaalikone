// Made by Sonja
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
import data.Question;

@WebServlet(
	    name = "DeleteQuestion",
	    urlPatterns = {"/admin/poistaKysymys"}
	)
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteQuestion() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		//HttpSession session=request.getSession(false);
		
		// Checking is there a current session
	    //if (session != null && session.getAttribute("uname") != null) {
	    	String id = request.getParameter("id");
	    	ArrayList<Question> list = null;
	    	
	    	// Checking that there is given an id so can delete
	    	if (id != null) {
	    		list = Dao.deleteQuestion(id);
		    	response.sendRedirect("/admin/kysymykset");
		    	
	    	}
	    	
	    	// If no id was given
	    	else {
	    		response.sendRedirect("/admin/kysymykset");
	    		
	    	}
	    	
	    //}
	    
	    // If there is no session
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    }
		
	}

}
