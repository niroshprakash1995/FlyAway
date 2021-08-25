package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;

import com.services.HibernateUtil;
import com.to.Admin;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String pwd = request.getParameter("password");
		RequestDispatcher rd = null;

		try {
			org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();

			String selectQuery = "FROM Admin WHERE adminid = :adminID";
			Query query = session.createQuery(selectQuery);
			query.setParameter("adminID", username);

			@SuppressWarnings("unchecked")
			List<Admin> results = query.list();
			session.close();

			Iterator<Admin> iterator = results.iterator();
			if (iterator.hasNext()) {
				Admin obj = iterator.next();
				String returnPwd = obj.getPwd();

				if (returnPwd.equals(pwd)) {
					HttpSession httpSession = request.getSession();
					httpSession.setAttribute("username", username);
					response.sendRedirect("adminlanding.html");
				} else {
					rd = request.getRequestDispatcher("adminlogin.html");
					PrintWriter out = response.getWriter();
					rd.include(request, response);
					out.println("<span style='color:red'> Invalid credentials. </span>");
				}

			} else {
				rd = request.getRequestDispatcher("adminlogin.html");
				PrintWriter out = response.getWriter();
				rd.include(request, response);
				out.println("<span style='color:red'> Invalid credentials, please try again. </span>");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
