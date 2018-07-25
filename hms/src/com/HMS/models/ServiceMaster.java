package com.HMS.models;

import com.HMS.commonUtills.Status;

public class ServiceMaster {

	private Long id;
	private String name;
	private double amount;
	private Status status;
	
	public ServiceMaster(Long id, String name, double amount, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.status = status;
	}
	public ServiceMaster() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ServiceMaster [id=" + id + ", name=" + name + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}
