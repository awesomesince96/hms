package com.HMS.models;

import com.HMS.commonUtills.Status;

public class User {

	private long id;
	private String username;
	private String password;
	private String fname;
	private String mname;
	private String lname;
	private String email;
	private String gender;
	private Status status;
	private Long contact;
	private Client client;
	

	public User(long id, String username, String password, String fname, String mname, String lname, String email,
			String gender, Status status, long l, Client client) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.email = email;
		this.gender = gender;
		this.status = status;
		this.contact = l;
		this.client = client;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fname=" + fname + ", mname="
				+ mname + ", lname=" + lname + ", email=" + email + ", gender=" + gender + ", status=" + status
				+ ", contact=" + contact + ", client=" + client + "]";
	}
	
}
