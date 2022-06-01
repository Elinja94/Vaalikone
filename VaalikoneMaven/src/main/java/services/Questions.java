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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.Ehdokkaat;
import data.Kysymykset;
import data.Vastaukset;

@Path("/questions")
public class Questions {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
	
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
	
	@GET
	@Path("/deletequestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteQuestion(@QueryParam("id") int id) {
		System.out.println("ollaan deletequestionissa");
		System.out.println(id);
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Kysymykset k=em.find(Kysymykset.class, id);
		if (k!=null) {
			em.remove(k);//The actual delete line
		}
		em.getTransaction().commit();
		
		List<Kysymykset> list=getQuestions();		

		request.setAttribute("questionsList", list);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/QuestionsList.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
}
