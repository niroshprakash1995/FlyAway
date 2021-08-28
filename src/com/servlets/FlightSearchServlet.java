package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import com.services.HibernateUtil;

public class FlightSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightSearchServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sourceInput = request.getParameter("source");
		String destinationInput = request.getParameter("destination");
		String dateInput = request.getParameter("date");
		String noOfPersonsInput = request.getParameter("noofpersons");

		try {
			org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();

			String selectQuery = "SELECT fl.flightid, fl.price, fl.departure, fl.arrival FROM Flights fl, SourceDestination sd"
					+ " WHERE fl.sdid = sd.sdid and fl.ticketsavailable > :personsInput and sd.source = :sourceInput and sd.destination = :destinationInput and fl.flightdate = :flightDateInput";
			Query query = session.createQuery(selectQuery);
			int personsInputInteger = Integer.parseInt(noOfPersonsInput);
			query.setParameter("personsInput", personsInputInteger);
			query.setParameter("sourceInput", sourceInput);
			query.setParameter("destinationInput", destinationInput);
			query.setParameter("flightDateInput", dateInput);

			@SuppressWarnings("unchecked")
			List<Object[]> results = query.list();
			session.close();
			if (results.size() > 0) {

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("<h3> Flights available from " + sourceInput.toUpperCase() + " to "
						+ destinationInput.toUpperCase() + ": </h3>");
				out.print("<style> table,td,th {" + "border:2px solid green;" + "padding: 10px; " + "}</style>");
				out.print("<table >");
				out.print("<tr>");
				out.print("<th> Flight Id</th>");
				out.print("<th> Price</th>");
				out.print("<th> Departure time</th>");
				out.print("<th> Arrival time</th>");
				out.print("</tr>");

				for (Object[] iterator : results) {
					out.print("<tr>");
					out.print("<td>" + iterator[0] + "</td>");
					out.print("<td>" + iterator[1] + "</td>");
					out.print("<td>" + iterator[2] + "</td>");
					out.print("<td>" + iterator[3] + "</td>");
					out.print("<td><input type=\"button\" id=" + iterator[0]
							+ " value=\"Select\" onClick=\"populateFid(this.id);\"></td>");
					out.print("</tr>");
				}
				out.println("</table>");
				out.println("</body></html>");
				request.getRequestDispatcher("booking.html").include(request, response);
				out.close();
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("flightsearch.html");
				PrintWriter out = response.getWriter();
				rd.include(request, response);
				out.println("<span style='color:red'> No flights found.</span>");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
