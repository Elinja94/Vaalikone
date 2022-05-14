// Made by Sonja
package app;

import java.io.IOException;
import java.io.PrintWriter;

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
	    urlPatterns = {"/admin/lisaaKysymysVahvistus"}
	)
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddQuestion() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Way to print out later
		PrintWriter out = response.getWriter();
		
		// Information needed to check session status
		response.setContentType("text/html");
		//HttpSession session=request.getSession(false);
				
		// Checking is there a current session
	    //if (session != null && session.getAttribute("uname") != null) {
	    	String question = request.getParameter("question");
	    	
	    	// Checking if where able to add question
	    	if (!question.isEmpty()) {
	    		Dao.addQuestion(question);
		    	out.println("<script type='text/javascript'>");
		    	out.println("alert('Kysymys lisätty');");
		    	out.println("location='/admin/kysymykset';");
		    	out.println("</script>");
	    		
	    	}
	    	
	    	// If not able to add question (just in case)
	    	else {
	    		out.println("<script type='text/javascript'>");
		    	out.println("alert('Kysymystä ei lisätty');");
		    	out.println("location='/admin/lisaaKysymys';");
		    	out.println("</script>");
		    	
	    	}
	    	
	    //}
	    
	    // If there is no session
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    }
	    
	}

}
