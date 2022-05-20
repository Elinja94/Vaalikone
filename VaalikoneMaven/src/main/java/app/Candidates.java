package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import javax.servlet.http.HttpSession;

import data.Ehdokkaat;

/**
 * Servlet implementation class Questions
 */
@WebServlet("/candidates")
public class Candidates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Candidates() {
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
		List<Ehdokkaat> candidatelist=em.createQuery("select e from Ehdokkaat e ORDER BY e.puolue").getResultList();
		em.getTransaction().commit();

		request.setAttribute("candidatelist", candidatelist);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/Candidates.jsp");		
		rd.forward(request, response);	

	}
    
    HashMap<Integer, Integer> sortByPuolue(HashMap<Integer, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<HashMap.Entry<Integer, Integer> > list =
               new LinkedList<HashMap.Entry<Integer, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<HashMap.Entry<Integer, Integer> >() {
            public int compare(HashMap.Entry<Integer, Integer> o1,
                               HashMap.Entry<Integer, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (HashMap.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String candidate = request.getParameter("1");
		
		PrintWriter out=response.getWriter();
		out.println(candidate);
	}

}
