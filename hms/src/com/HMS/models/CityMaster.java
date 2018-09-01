package com.HMS.models;

import com.HMS.commonUtills.Status;

public class CityMaster {
	private long id;
	private String name;
	private Status status;
	private StateMaster stateMaster;

	public CityMaster() {
	}

	public CityMaster(String name, Status status, StateMaster stateMaster) {
		super();
		this.name = name;
		this.status = status;
		this.stateMaster = stateMaster;
	}

	public CityMaster(long id, String name, Status status, StateMaster stateMaster) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.stateMaster = stateMaster;
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

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

}
