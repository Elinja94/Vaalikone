// Made by Sonja
package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

@WebServlet("/dbtest")
public class DBtest extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DBtest() {
        super();
    }

    // This is only a test for db not to be used at all!
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Dao.getConnection() == true) {
			
			System.out.println("Connected");
			
		}
		
		else {
			
			System.out.println("No connection to database");
			
		}
	}
}
