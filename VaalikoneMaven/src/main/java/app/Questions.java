package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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
import data.Vastaukset;

/**
 * Servlet implementation class Questions
 */
@WebServlet("/vastaakysymyksiin")
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
	
    HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm)
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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
		EntityManager em=emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Kysymykset> questionlist=em.createQuery("select k from Kysymykset k").getResultList();
		List<Vastaukset> candidateAnswers=em.createQuery("select v from Vastaukset v").getResultList();
		em.getTransaction().commit();
		
		// Key: question id, value: answer
		HashMap<Integer, String> userAnswers = new HashMap<Integer, String>();
		// Key: candidate id, value: amount of same answers
		HashMap<Integer, Integer> sameAnswers = new HashMap<Integer, Integer>();
		
		// Add users answers to hashmap
		for (Kysymykset q : questionlist) {
			String answer = request.getParameter("" + q.getId());
			
			if (answer != null) {
				userAnswers.put(q.getId(), answer);
			}
		}
		
		// Add amount of same answers to hashmap
		for (Vastaukset a : candidateAnswers) {
			boolean isSameAnswer = userAnswers.get(a.getQuestionId()) != null && Integer.parseInt(a.getAnswer()) == Integer.parseInt(userAnswers.get(a.getQuestionId()));
			if (isSameAnswer) {
					int candidateId = a.getCandidateId();
					if (sameAnswers.get(candidateId) == null) {
						sameAnswers.put(candidateId, 1);
					}
					else {
						Integer sameAnswersCount = sameAnswers.get(a.getCandidateId()) + 1;
						sameAnswers.replace(candidateId, sameAnswersCount);
					}
				}
		}
		
		sameAnswers = sortByValue(sameAnswers);
		request.setAttribute("userAnswers", userAnswers);
		request.setAttribute("sameAnswers", sameAnswers);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/CompareAnswers.jsp");		
		rd.forward(request, response);	
	}

}
