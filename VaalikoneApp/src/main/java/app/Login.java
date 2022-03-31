package app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Sends information to check if they are correct
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    String user = request.getParameter("username");
	    String pass = request.getParameter("password");
	    String cryptedPass = "";
	    
	    // Makes sure that there is a password entered
	    if (!pass.isEmpty()) {
	    	
	    	// Encrypting the password
	    	cryptedPass = Dao.crypt(pass);
	    	
	    	// Makes sure that password is correct to the provided username
	    	if (Dao.checkLogin(user, cryptedPass) == true) {
				response.getWriter().println("Login ok");
				
			}
			
	    	// If either password or username is wrong
			else {
				response.getWriter().println("Login not ok");
				
			}
	    }
	    
	    // If no password was entered
	    else {
	    	response.getWriter().println("Input password");
	    	
	    }
	}
}
