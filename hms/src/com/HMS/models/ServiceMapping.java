package com.HMS.models;

public class ServiceMapping {

	private long id;
	private ServiceMaster service;
	private Bill bill;
	
	public ServiceMapping(long id, ServiceMaster service, Bill bill) {
		super();
		this.id = id;
		this.service = service;
		this.bill = bill;
	}
	public ServiceMapping() {
	}
	public long getId() {
		return id;
	}
	public ServiceMaster getService() {
		return service;
	}

	public void setService(ServiceMaster service) {
		this.service = service;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ServiceMapping [id=" + id + ", Service = " + service + ", Bill = " + bill + "]";
	}
	
}
