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
import com.to.Flights;

public class ShowFlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowFlightsServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();

			String selectQuery = "FROM Flights";
			Query query = session.createQuery(selectQuery);
			@SuppressWarnings("unchecked")
			List<Flights> results = query.list();

			session.close();
			PrintWriter out = response.getWriter();
			out.print("<h1> Flights Table </h1>");
			out.print("<style> table,td,th {" + "border:2px solid red;" + "padding: 10px; " + "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Flight Id</th>");
			out.print("<th> Source-Destination Id</th>");
			out.print("<th> Airline Id</th>");
			out.print("<th> Price</th>");
			out.print("<th> Departure</th>");
			out.print("<th> Arrival</th>");
			out.print("<th> Tickets available</th>");
			out.print("<th> Date</th>");
			out.print("</tr>");

			Iterator<Flights> iterator = results.iterator();
			while (iterator.hasNext()) {
				Flights obj = iterator.next();
				out.print("<tr>");
				out.print("<td>" + obj.getFlightid() + "</td>");
				out.print("<td>" + obj.getSdid().getSdid() + "</td>");
				out.print("<td>" + obj.getAirlineid().getAirlineid() + "</td>");
				out.print("<td>" + obj.getPrice() + "</td>");
				out.print("<td>" + obj.getDeparture() + "</td>");
				out.print("<td>" + obj.getArrival() + "</td>");
				out.print("<td>" + obj.getTicketsavailable() + "</td>");
				out.print("<td>" + obj.getFlightdate() + "</td>");
				out.print("</tr>");
			}
			out.println("</table> </body> </html>");
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
