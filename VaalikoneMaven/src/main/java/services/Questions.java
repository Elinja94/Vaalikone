package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import data.Kysymykset;

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
	
	@GET
	@Path("/admin/muokkaaKysymys?id={id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Kysymykset readToUpdateQuestion(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    Kysymykset k = em.find(Kysymykset.class,id);
	    em.getTransaction().commit();
		return k;
		
	}
	
	@PUT
	@Path("/admin/muokkaaKysymysVahvistus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Kysymykset> updateQuestion(Kysymykset kysymys) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Kysymykset k = em.find(Kysymykset.class, kysymys.getId()); 
		if (k != null) {
			em.merge(kysymys);
			
		}
		em.getTransaction().commit();
		List<Kysymykset> list= getQuestions();		
		return list;
	}
	
}
