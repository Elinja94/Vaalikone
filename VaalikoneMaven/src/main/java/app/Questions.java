/* package app;

import java.io.*;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.*;


@WebServlet("/ReadFishAndLure")
public class ReadFishAndLure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadFishAndLure() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpafishbylure");
		EntityManager em=emf.createEntityManager();
		
		out.println("<h2>Read all the Fishbreeds</h2>");
		em.getTransaction().begin();
		List<Fishbreed> fblist=em.createQuery("select f from Fishbreed f").getResultList();
		em.getTransaction().commit();
		out.println("<ol>");
		for (Fishbreed fb:fblist) {
			out.println("<li>Fish: "+fb);
			out.println("<ol>");
			for (Luretype lt : fb.getLuretypes()) {
				out.println("<li>Lure: "+lt);
			}
			out.println("</ol>");
		}
		out.println("</ol>");
		out.println("<h2>Read all the Luretypes</h2>");
		em.getTransaction().begin();
		List<Luretype> ltlist=em.createQuery("select l from Luretype l").getResultList();
		em.getTransaction().commit();
		out.println("<ol>");
		for (Luretype lt:ltlist) {
			out.println("<li>Lure: "+lt);
			out.println("<ol>");
			for (Fishbreed fb : lt.getFishbreeds()) {
				out.println("<li>Lure: "+fb);
			}
			out.println("</ol>");
		}
		out.println("</ol>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

} */




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
