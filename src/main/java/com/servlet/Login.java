package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Student;
import com.service.StudentOperations;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname =request.getParameter("txtUname");
		String pwd = request.getParameter("txtPwd");
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(uname.equalsIgnoreCase("Admin") && pwd.equals("admin@123"))
		{
			session.setAttribute("user", "Administrator");
			response.sendRedirect("ShowAll");
		}
		
		else
		{
			StudentOperations so = new StudentOperations();
			Student std= so.CheckUser(uname, pwd);
			if(std!=null)
			{
				session.setAttribute("rollno", std.getRollno());
				response.sendRedirect("StudentInfo");
			}
			else
			{
				out.print("<p style='text-align:center'>Error...!! Please check the user name and password </p>");
				RequestDispatcher rd= request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
		}
	}

}
