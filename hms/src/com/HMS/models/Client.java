package com.HMS.models;

public class Client {
	private long id;
	private String hname;
	private String address;
	private Long contact;
	
	public Client() {
	}
	
	
	public Client(String hname, String address, long contact) {
		super();
		this.hname = hname;
		this.address = address;
		this.contact = contact;
	}


	public Client(long id, String hname, String address, long contact) {
		super();
		this.id = id;
		this.hname = hname;
		this.address = address;
		this.contact = contact;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", hname=" + hname + ", address=" + address + ", contact=" + contact + "]";
	}
	
	
	
}