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
		urlPatterns = {"/lisaaEhdokasVahvistus"}
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
				
		// Checking is there a current session
	    if (session != null && session.getAttribute("uname") != null) {
	    	String sukunimi = request.getParameter("sukunimi");
	    	String etunimi = request.getParameter("etunimi");
	    	String puolue = request.getParameter("puolue");
	    	String kotipaikkakunta = request.getParameter("kotipaikkakunta");
	    	String ika = request.getParameter("ika");
	    	String miksi_eduskuntaan = request.getParameter("miksi_eduskuntaan");
	    	String mita_asioita_haluat_edistaa = request.getParameter("mita_asioita_haluat_edistaa");
	    	String ammatti = request.getParameter("ammatti");
	    	
	    	// Checking if where able to add candidate
	    	if (Dao.addCandidate(sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan, mita_asioita_haluat_edistaa, ammatti) != null) {
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