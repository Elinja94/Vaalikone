// Made by Sonja
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Way to print out later
		PrintWriter out = response.getWriter();
		
		// Sends information to check if they are correct
		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");

	    String user = request.getParameter("username");
	    String pass = request.getParameter("password");
	    String cryptedPass = "";
	    
	    // Makes sure that there is a password and username entered
	    if (!pass.isEmpty() && !user.isEmpty()) {
	    	
	    	// Encrypting the password
	    	cryptedPass = Dao.crypt(pass);
	    	
	    	// Makes sure that password is correct to the provided username
	    	if (Dao.checkLogin(user, cryptedPass) == true) {
	    		HttpSession session=request.getSession();
	    		session.setAttribute("uname",user);
	    		response.sendRedirect("/Admin");
				
			}
			
	    	// If either password or username is wrong
			else {
				out.println("<script type='text/javascript'>");
		    	out.println("alert('K‰ytt‰j‰tunnus tai salasana on v‰‰rin');");
		    	out.println("location='http://localhost:8080/';");
		    	out.println("</script>");
				
			}
	    }
	    
	    // If no password or username was entered
	    else {
	    	out.println("<script type='text/javascript'>");
	    	out.println("alert('K‰ytt‰j‰tunnusta tai salasanaa ei annettu');");
	    	out.println("location='http://localhost:8080/';");
	    	out.println("</script>");
	    	
	    }
	}
}
