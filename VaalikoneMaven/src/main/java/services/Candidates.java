package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Ehdokkaat;

@Path("/candidates")
public class Candidates {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	
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
	
}
