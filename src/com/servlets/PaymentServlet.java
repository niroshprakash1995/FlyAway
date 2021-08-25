package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.services.HibernateUtil;
import com.to.Bookings;

public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		try {
			String fname = (String) httpSession.getAttribute("fname");
			String lname = (String) httpSession.getAttribute("lname");
			String fid = (String) httpSession.getAttribute("fid");

			Date bookingDate = new Date();
			String dateString = bookingDate.toString();

			org.hibernate.SessionFactory sf = HibernateUtil.buildSessionFactory();
			Session session = sf.openSession();

			Bookings b = new Bookings();
			b.setFlightid(fid);
			b.setFname(fname);
			b.setLname(lname);
			b.setBookingtime(dateString);

			Transaction tx = session.beginTransaction();
			session.save(b);
			tx.commit();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.write("<html><body>");
			out.write("<h3 style='color:red'>Thank you " + fname.toUpperCase() + " " + lname.toUpperCase() + "!</h3>");
			out.write("<h3 style='color:red'>You have successfully booked flight with id " + fid + " at " + dateString
					+ ".<h3>");
			out.write("<h3>You are successfully logged out.<h3>");
			out.write("</body></html>");
			out.close();
			session.close();

		} catch (Exception e) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.write("<h3 style='color:red'>Oh Snap! Something went wrong. Please try again<h3>");
			System.out.println(e.getMessage());
		}

		httpSession.invalidate();
	}
}
