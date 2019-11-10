package project3;
/*
 * Josh Sample
 * CSCI 3381 Project 3
 * 11/18
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AceDataManager myData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        myData = new AceDataManager("./data.txt" , "./project3/data.txt");
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
				response.getWriter().append("<script type=\"text/javascript\">"
						+ "alert('The username or password is incorrect, please try again');"
						+ "</script>");
				rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
		}
		else if (request.getParameter("addPat") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/AddPat.html");   
			rd.forward(request, response);
		}
		else if (request.getParameter("viewPat") != null) {
			String pId = request.getParameter("id");
			if (myData.getPatient(pId) != null) {
				RequestDispatcher rd=request.getRequestDispatcher("/modPat.jsp");   
				rd.forward(request,response);  
			}
			else {
				response.getWriter().append("<script type=\"text/javascript\">"
						+ "alert('That ID does not exist, please try again');"
						+ "</script>");
				response.sendRedirect("using.html");
			}
		}
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
