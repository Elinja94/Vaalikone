// Made by Sonja
package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Kysymykset;
import data.Question;

/**
 * Servlet implementation class EditQuestion
 */
@WebServlet(
	    name = "EditQuestion",
	    urlPatterns = {"/admin/muokkaaKysymysVahvistus"}
	)
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditQuestion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Way to print out later
		PrintWriter out = response.getWriter();
		
		// Information needed to check session status
		response.setContentType("text/html");
		
		out.println("<script type='text/javascript'>");
    	out.println("alert('Kysymys muokattu');");
    	out.println("location='/admin/kysymykset';");
    	out.println("</script>");
		//HttpSession session=request.getSession(false);
				
		// Checking is there a current session
	    //if (session != null && session.getAttribute("uname") != null) {
//	    	String id = request.getParameter("id");
//	    	String ques = request.getParameter("question");
//	    	
//	    	// Checking is there something in the question
//	    	if (id != null && ques != null) {
//	    		
//	    		//Question q = new Question(id, ques);
//	    		//ArrayList<Question> list = null;
//	    		//list = Dao.updateQuestion(q);
//	    		out.println("<script type='text/javascript'>");
//		    	out.println("alert('Kysymys muokattu');");
//		    	out.println("location='/admin/kysymykset';");
//		    	out.println("</script>");
//		    	
//	    	}
//	    	
//	    	// If no question or id was given
//	    	else {
//	    		out.println("<script type='text/javascript'>");
//		    	out.println("alert('Kysymystä ei muokattu');");
//		    	out.println("location='/admin/kysymykset';");
//		    	out.println("</script>");
//	    		
//	    	}
	    	
	    //}
	    
	    // If there is no session
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    }
		
	}

}
