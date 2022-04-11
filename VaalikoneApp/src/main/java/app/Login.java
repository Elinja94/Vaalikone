// Made by Sonja
package app;

import java.io.IOException;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Sends information to check if they are correct
		response.setContentType("text/plain");
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
				response.sendRedirect("http://localhost:8080/");
				
			}
	    }
	    
	    // If no password or username was entered
	    else {
	    	response.sendRedirect("http://localhost:8080/");
	    	
	    }
	}
}
