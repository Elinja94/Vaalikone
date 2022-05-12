package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.Kysymykset;

/**
 * Servlet implementation class Questions
 */
@WebServlet("/questions")
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Questions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
		EntityManager em=emf.createEntityManager();
		
		out.println("<h2>Kysymykset</h2>");
		em.getTransaction().begin();
		List<Kysymykset> questionlist=em.createQuery("select k from Kysymykset k").getResultList();
		em.getTransaction().commit();
		out.println("<ol>");
		for (Kysymykset k:questionlist) {
			out.println("<li> "+ k.getQuestion());
			out.println("<ol>");
			out.println("</ol>");
		}
		out.println("</ol>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
