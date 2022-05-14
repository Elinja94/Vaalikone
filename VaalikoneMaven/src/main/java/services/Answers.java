package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Kysymykset;

@Path("/answers")
public class Answers {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	
	@Path("/getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Kysymykset> getQuestions() {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    List<Kysymykset> list=em.createQuery("select v from Vastaukset v").getResultList();
	    em.getTransaction().commit();
		return list;
	}
}
