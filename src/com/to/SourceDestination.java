package com.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "sourcedestination")
public class SourceDestination {

	@Id
	private int sdid;
	private String source;
	private String destination;

	public SourceDestination() {
		super();
	}

	public int getSdid() {
		return sdid;
	}

	public void setSdid(int sdid) {
		this.sdid = sdid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public SourceDestination(int sdid, String source, String destination) {
		super();
		this.sdid = sdid;
		this.source = source;
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "Sourcedestination [sdid=" + sdid + ", source=" + source + ", destination=" + destination + "]";
	}

}
