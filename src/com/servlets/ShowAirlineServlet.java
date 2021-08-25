package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.services.HibernateUtil;
import com.to.Airline;

public class ShowAirlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAirlineServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();

			String selectQuery = "FROM Airline";
			Query query = session.createQuery(selectQuery);
			@SuppressWarnings("unchecked")
			List<Airline> results = query.list();

			session.close();
			PrintWriter out = response.getWriter();
			out.print("<h1> Airline Table </h1>");
			out.print("<style> table,td,th {" + "border:2px solid red;" + "padding: 10px; " + "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Airline Id</th>");
			out.print("<th> Airline name</th>");
			out.print("</tr>");

			Iterator<Airline> iterator = results.iterator();
			while (iterator.hasNext()) {
				Airline obj = iterator.next();
				out.print("<tr>");
				out.print("<td>" + obj.getAirlineid() + "</td>");
				out.print("<td>" + obj.getAirlinename() + "</td>");
				out.print("</tr>");
			}
			out.println("</table> </body> </html>");
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
