// Made by Sonja
package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Simple way to end a session but not the best
		response.setContentType("text/html");
		HttpSession session=request.getSession(false);
		session.removeAttribute("uname");
		session.invalidate();
		response.sendRedirect("http://localhost:8080/");
		
	}

}
