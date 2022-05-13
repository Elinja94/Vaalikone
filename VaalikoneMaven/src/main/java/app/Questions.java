package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Kysymykset> questionlist=em.createQuery("select k from Kysymykset k").getResultList();
		em.getTransaction().commit();

		request.setAttribute("questionlist", questionlist);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/QuestionForm.jsp");		
		rd.forward(request, response);	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answer = request.getParameter("1");
		
		PrintWriter out=response.getWriter();
		out.println(answer);
	}

}
