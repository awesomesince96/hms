package com.HMS.models;

import com.HMS.commonUtills.Status;

public class StateMaster {
	private long id;
	private String name;
	private Status status;
	private ContryMaster contryMaster;

	public StateMaster() {
		// TODO Auto-generated constructor stub
	}

	public StateMaster(String name, Status status, ContryMaster contryMaster) {
		super();
		this.name = name;
		this.status = status;
		this.contryMaster = contryMaster;
	}

	public StateMaster(long id, String name, Status status, ContryMaster contryMaster) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.contryMaster = contryMaster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ContryMaster getContryMaster() {
		return contryMaster;
	}

	public void setContryMaster(ContryMaster contryMaster) {
		this.contryMaster = contryMaster;
	}

	@Override
	public String toString() {
		return "StateMaster [id=" + id + ", name=" + name + ", status=" + status + ", contryMaster=" + contryMaster
				+ "]";
	}

}
