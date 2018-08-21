package com.HMS.models;

import com.HMS.commonUtills.Status;

public class ContryMaster {
	public long id;
	public String name;
	public Status status;

	public ContryMaster() {

	}

	public ContryMaster(long id, String name, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public ContryMaster(String name, Status status) {
		super();
		this.name = name;
		this.status = status;
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

	@Override
	public String toString() {
		return "ContryMaster [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
