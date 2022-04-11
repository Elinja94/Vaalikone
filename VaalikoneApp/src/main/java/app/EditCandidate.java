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
import data.Candidate;
import data.Question;


@WebServlet(
		name = "EditCandidate",
		urlPatterns = {"/muokkaaEhdokasVahvistus"}
		)

public class EditCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public EditCandidate() {
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
	    	String id = request.getParameter("id");
	    	String sukunimi = request.getParameter("sukunimi");
	    	String etunimi = request.getParameter("etunimi");
	    	String puolue = request.getParameter("puolue");
	    	String kotipaikkakunta = request.getParameter("kotipaikkakunta");
	    	String ika = request.getParameter("ika");
	    	String miksi_eduskuntaan = request.getParameter("miksi_eduskuntaan");
	    	String mita_asioita_haluat_edistaa = request.getParameter("mita_asioita_haluat_edistaa");
	    	String ammatti = request.getParameter("ammatti");
	    	
	    	// Checking if where able to add candidate
	    	if (id != null && !sukunimi.isEmpty() && !etunimi.isEmpty() && !puolue.isEmpty() && !kotipaikkakunta.isEmpty() && !ika.isEmpty() && !miksi_eduskuntaan.isEmpty() && !mita_asioita_haluat_edistaa.isEmpty() && !ammatti.isEmpty()) {
	    		Candidate c = new Candidate(id, sukunimi, etunimi, puolue, kotipaikkakunta, ika, miksi_eduskuntaan, mita_asioita_haluat_edistaa, ammatti);
	    		ArrayList<Candidate> list = null;
	    		list = Dao.updateCandidate(c);
	    		response.sendRedirect("/ehdokkaat");
	    	}
	    	
	    	// If not able to add candidate (just in case)
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