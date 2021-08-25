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
import com.to.SourceDestination;

public class ShowSourceDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowSourceDestinationServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();

			String selectQuery = "FROM SourceDestination";
			Query query = session.createQuery(selectQuery);
			@SuppressWarnings("unchecked")
			List<SourceDestination> results = query.list();

			session.close();
			PrintWriter out = response.getWriter();
			out.print("<h1> Source-Destination Table </h1>");
			out.print("<style> table,td,th {" + "border:2px solid red;" + "padding: 10px; " + "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Source-Destination Id</th>");
			out.print("<th> Source City</th>");
			out.print("<th> Destination City</th>");
			out.print("</tr>");

			Iterator<SourceDestination> iterator = results.iterator();
			while (iterator.hasNext()) {
				SourceDestination obj = iterator.next();
				out.print("<tr>");
				out.print("<td>" + obj.getSdid() + "</td>");
				out.print("<td>" + obj.getSource() + "</td>");
				out.print("<td>" + obj.getDestination() + "</td>");
				out.print("</tr>");
			}
			out.println("</table> </body> </html>");
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
