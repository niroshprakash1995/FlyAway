package com.to;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "admin")
public class Admin {

	@Id
	private String adminid;
	private String pwd;
	

	public Admin() {
		super();
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Admin(String adminid, String pwd) {
		super();
		this.adminid = adminid;
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", pwd=" + pwd + "]";
	}

}
