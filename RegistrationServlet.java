package com.edubridge.servlet.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String pass = request.getParameter("pass");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_form","root","root");
			
			PreparedStatement pst = con.prepareStatement("INSERT INTO student VALUES(?,?,?,?)");
			
			pst.setString(1,name);
			pst.setString(2,email);
			pst.setString(3,mobile);
			pst.setString(4,pass);
			
			int i = pst.executeUpdate();
			
			if(i>0) {
				out.println("Congratulations for your successfull Registration");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
			}
			
			pst.close();
			
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
