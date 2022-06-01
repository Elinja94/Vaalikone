package services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;

import dao.Dao;
import data.Ehdokkaat;



@Path("/candidates")
public class Candidates {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
	@Path("/getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ehdokkaat> getCandidates() {

		EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    List<Ehdokkaat> list=em.createQuery("select e from Ehdokkaat e").getResultList();
	    em.getTransaction().commit();
		return list;
		
	}
	
	
	@GET
	@Path("/deletecandidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCandidate(@QueryParam("id") int id) {
		System.out.println("ollaan deletecandidatessa");
		System.out.println(id);
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Ehdokkaat e=em.find(Ehdokkaat.class, id);
		if (e!=null) {
			em.remove(e);//The actual delete line
		}
		em.getTransaction().commit();
		
		List<Ehdokkaat> list=getCandidates();		

		request.setAttribute("candidatesList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/CandidatesList.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
	

    
}





