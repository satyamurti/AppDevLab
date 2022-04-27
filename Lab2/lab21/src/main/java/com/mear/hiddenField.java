package com.mear;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class hiddenField
 */
@WebServlet("/hiddenField")
public class hiddenField extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String userName=request.getParameter("uname").trim();
		String password=request.getParameter("pass").trim();
		
		if(userName==null || userName.equals("") || password==null || password.equals("")) {
			out.print("Please Enter both username and password correctly");
			
			RequestDispatcher rd=request.getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
			
			else if(userName.equals("MEAR") && password.equals("1234"))
			{
				out.println("Logged in successfully. </br>");
				out.println("Click on the bellow button to see the value of username and Password.</br>");
				out.print("<form action='GetHidenField' method='POST'>");
				out.print("<input type='hidden' name='userName' value='"+userName+"'>");
				out.print("<input type='hidden' name='passsword' value='"+password+"'>");
				out.print("<input type='submit' value='See values'>");
				out.print("</form>");
				out.close();
			}
			else {
				out.print("Wrong username or password <br></br>");
				RequestDispatcher rd=request.getRequestDispatcher("/login.html");
				rd.include(request, response);
				
				
			}
			
			
			
		}
		
		
//		doGet(request, response);
	}


