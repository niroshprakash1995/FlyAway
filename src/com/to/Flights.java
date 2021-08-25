
package com.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "flights")
public class Flights {

	@Id
	private int flightid;
	@OneToOne
	@JoinColumn(name = "sdid")
	private SourceDestination sdid;
	@OneToOne
	@JoinColumn(name = "airlineid")
	private Airline airlineid;
	private int price;
	private String departure;
	private String arrival;
	private int ticketsavailable;
	private String flightdate;

	public Flights() {
		super();
	}

	public int getFlightid() {
		return flightid;
	}

	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}

	public SourceDestination getSdid() {
		return sdid;
	}

	public void setSdid(SourceDestination sdid) {
		this.sdid = sdid;
	}

	public Airline getAirlineid() {
		return airlineid;
	}

	public void setAirlineid(Airline airlineid) {
		this.airlineid = airlineid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getTicketsavailable() {
		return ticketsavailable;
	}

	public void setTicketsavailable(int ticketsavailable) {
		this.ticketsavailable = ticketsavailable;
	}

	public String getFlightdate() {
		return flightdate;
	}

	public void setFlightdate(String flightdate) {
		this.flightdate = flightdate;
	}

	public Flights(SourceDestination sdid, Airline airlineid, int price, String departure, String arrival,
			int ticketsavailable, String flightdate) {
		super();
		this.sdid = sdid;
		this.airlineid = airlineid;
		this.price = price;
		this.departure = departure;
		this.arrival = arrival;
		this.ticketsavailable = ticketsavailable;
		this.flightdate = flightdate;
	}

	@Override
	public String toString() {
		return "Flights [flightid=" + flightid + ", sdid=" + sdid + ", airlineid=" + airlineid + ", price=" + price
				+ ", departure=" + departure + ", arrival=" + arrival + ", ticketsavailable=" + ticketsavailable
				+ ", flightdate=" + flightdate + "]";
	}

}
