package project3;
/*
 * Josh Sample
 * CSCI 3381 Project 3
 * 11/18
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project1.*;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AceDataManager myData = new AceDataManager("./data.txt", "./project1/data.txt");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This if checks login info
		if (request.getParameter("doLogin") != null) {
			RequestDispatcher rd;
			String name = request.getParameter("username"); 
			String password = request.getParameter("psw");
			// This checks the username and password
			// In reality this should probably be a hashmap or database type thing
			// For simplicity, I'm leaving it hardcoded as md and pw
			if (name.equals("md") && password.equals("pw")) {
				rd = request.getRequestDispatcher("/using.html");
				rd.forward(request, response); 
			}
			else {
				// Redirect to login page
				response.getWriter().append("<meta http-equiv='refresh' content='3;URL=index.html'>"
						+ "<p style='color:red;'>User name or pass word is incorrect, try agian</p>");
			}
		}
		// Redirects to AddPat.html if add patient button is selected
		else if (request.getParameter("addPat") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/AddPat.html");   
			rd.forward(request, response);
		}
		// Redirects to modPat.jsp
		else if (request.getParameter("viewPat") != null) {
			String pId = request.getParameter("id");
			// check if ID exists
			if (myData.getPatient(pId) != null) {
				PatientADT p = myData.getPatient(pId);
				request.setAttribute("id",p.getId()); 
				request.setAttribute("name", p.getName());
				request.setAttribute("risk", myData.getRiskFactors(p.getACEs()));
				RequestDispatcher rd=request.getRequestDispatcher("/modPat.jsp");   
				rd.forward(request,response);  
			}
			else {
				// Redirect to using if ID is wrong or does not exist
				response.getWriter().append("<meta http-equiv='refresh' content='3;URL=using.html'>"
						+ "<p style='color:red;'>ID is incorrect, try again</p>");
			}
		}
		// logout redirects to login page
		else if (request.getParameter("doLogout") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");   
			rd.forward(request, response);
		}
		// Add Patient
		else if (request.getParameter("doAdd") != null) {
			Patient p;
			String name = request.getParameter("addName"); 
			String id = request.getParameter("addID");
			p = new Patient(id, name);
			if (name == null || id == null) {
				response.getWriter().append("<meta http-equiv='refresh' content='3;URL=AddPat.html'>"
						+ "<p style='color:red;'>Please enter a valid name or ID</p>");
			}
			else if (myData.getPatient(id) != null) {
				response.getWriter().append("<meta http-equiv='refresh' content='3;URL=AddPat.html'>"
						+ "<p style='color:red;'>Patient ID exists, enter another one.</p>");
			}
			else {
				myData.addPatient(p);
				myData.writeToFile("./data.txt");
				RequestDispatcher rd = request.getRequestDispatcher("/AddPat.html");   
				rd.forward(request, response);
			}
		}
		// goes back a page
		else if (request.getParameter("back") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/using.html");   
			rd.forward(request, response);
		}
		// add aces to a patient
		else if (request.getParameter("aceButton") != null) {
			
		}
		// default to login
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");   
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
