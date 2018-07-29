package com.HMS.models;

public class Bill {

	private long id;
	private VisitMaster visit;
	private User user;
	private double amount;
	public long getId() {
		return id;
	}

	public Bill() {
		super();
	}
	
	public Bill(long id, VisitMaster visit, User user, double amount) {
		super();
		this.id = id;
		this.visit = visit;
		this.user = user;
		this.amount = amount;
	}

	public VisitMaster getVisit() {
		return visit;
	}
	public void setVisit(VisitMaster visit) {
		this.visit = visit;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", visit = " + visit + ", user = " + user + ", amount=" + amount + "]";
	}
}	
	
