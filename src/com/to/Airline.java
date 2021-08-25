package com.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "airline")
public class Airline {

	@Id
	private int airlineid;
	private String airlinename;

	public Airline() {
		super();
	}

	public int getAirlineid() {
		return airlineid;
	}

	public void setAirlineid(int airlineid) {
		this.airlineid = airlineid;
	}

	public String getAirlinename() {
		return airlinename;
	}

	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}

	public Airline(int airlineid, String airlinename) {
		super();
		this.airlineid = airlineid;
		this.airlinename = airlinename;
	}

	@Override
	public String toString() {
		return "Airline [airlineid=" + airlineid + ", airlinename=" + airlinename + "]";
	}

}
