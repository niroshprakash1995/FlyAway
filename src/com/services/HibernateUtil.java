package com.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	static SessionFactory sessionFactory = null;

	public static SessionFactory buildSessionFactory() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(com.to.Admin.class).addAnnotatedClass(com.to.Airline.class)
				.addAnnotatedClass(com.to.SourceDestination.class).addAnnotatedClass(com.to.Flights.class)
				.addAnnotatedClass(com.to.Bookings.class).buildSessionFactory();
		return sessionFactory;
	}

}
