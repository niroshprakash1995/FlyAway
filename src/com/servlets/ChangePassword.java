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
import org.hibernate.Transaction;

import com.services.HibernateUtil;
import com.to.Admin;

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangePassword() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String username = (String) httpSession.getAttribute("username");
		RequestDispatcher rd = null;

		String oldPwd = request.getParameter("oldPsw");
		String newPwd = request.getParameter("newPsw");

		if (oldPwd.equalsIgnoreCase(newPwd)) {
			rd = request.getRequestDispatcher("changepwd.html");
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			out.println("<span style='color:red'>Two passwords cannot be the same. Try again.</span>");
		} else {
			try {
				org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
				Session session = sf.openSession();

				String selectQuery = "FROM Admin WHERE adminid = :adminID";
				Query query = session.createQuery(selectQuery);
				query.setParameter("adminID", username);

				@SuppressWarnings("unchecked")
				List<Admin> results = query.list();

				Iterator<Admin> iterator = results.iterator();

				if (iterator.hasNext()) {

					Admin obj = iterator.next();
					String oldPassword = obj.getPwd();
					if (oldPassword.equals(oldPwd)) {
						Transaction tx = session.beginTransaction();
						String updateQuery = "update Admin set pwd = :updatePwd where adminid = :adminID";
						query = session.createQuery(updateQuery);
						query.setParameter("updatePwd", newPwd);
						query.setParameter("adminID", username);
						query.executeUpdate();
						tx.commit();
						PrintWriter out = response.getWriter();
						out.write("Password changed successfully!!");
						out.close();
					} else {
						rd = request.getRequestDispatcher("changepwd.html");
						PrintWriter out = response.getWriter();
						rd.include(request, response);
						out.println("<span style='color:red'>Old password is incorrect. Please try again.</span>");
						out.close();
					}

				} else {
					rd = request.getRequestDispatcher("changepwd.html");
					PrintWriter out = response.getWriter();
					rd.include(request, response);
					out.println("<span style='color:red'> Something went wrong. Try again.</span>");
					out.close();
				}
				session.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
