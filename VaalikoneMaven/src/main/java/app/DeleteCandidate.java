package app;

import java.io.*;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.Dao;
import data.Candidate;
import data.Ehdokkaat;


@WebServlet(
	    name = "DeleteCandidate",
	    urlPatterns = {"/admin/poistaEhdokasVahvistus"}
	)
public class DeleteCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteCandidate() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Information needed to check session status
		response.setContentType("text/html");
		//HttpSession session=request.getSession(false);
				
		// Checking is there a current session
	    //if (session != null && session.getAttribute("uname") != null) {
	    	String id = request.getParameter("id");
	    //	ArrayList<Candidate> list = null;
	    	
	    	// Checking that there is given an id so can delete
	    	if (id != null) {
	    	//	list = Dao.deleteCandidate(id);
		    	response.sendRedirect("/admin/ehdokkaat");
		    	
	    		
		    
				EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
				EntityManager em=emf.createEntityManager();
				
				em.getTransaction().begin();
				em.remove(id);
				em.getTransaction().commit();
	    	
	    	}
	    	
	    	// If no id was given
	    	else {
	    		response.sendRedirect("/admin/ehdokkaat");
	    		
	    	}
	    	
	    //}
	    
	    // If there is no session
//	    else {
//	    	response.sendRedirect("http://localhost:8080/");
//	    }
		
	}
	


}

