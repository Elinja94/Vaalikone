package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Kysymykset;
import data.Vastaukset;

@Path("/questions")
public class Questions {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	
	@Path("/getall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Kysymykset> getQuestions() {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    List<Kysymykset> list=em.createQuery("select k from Kysymykset k").getResultList();
	    em.getTransaction().commit();
		return list;
	}
	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Kysymykset addQuestion(Kysymykset question) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();
		return question;
	}
}
