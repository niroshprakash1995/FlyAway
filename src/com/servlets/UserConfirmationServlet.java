package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserConfirmationServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Fname = request.getParameter("firstname");
		String Lname = request.getParameter("lastname");
		String Fid = request.getParameter("fid");

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("fname", Fname);
		httpSession.setAttribute("lname", Lname);
		httpSession.setAttribute("fid", Fid);

		RequestDispatcher rd = request.getRequestDispatcher("payment.html");
		rd.include(request, response);

	}

}
