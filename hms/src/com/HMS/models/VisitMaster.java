package com.HMS.models;

import java.sql.Timestamp;

import com.HMS.commonUtills.Status;
import com.HMS.commonUtills.vStatus;

public class VisitMaster {

	private Long vid;
	private vStatus vstatus;
	private Timestamp dov;
	private User user;
	private PatientMaster patient;
	
	
	public VisitMaster(Long vid, vStatus vstatus, Timestamp dov, User user, PatientMaster patient) {
		super();
		this.vid = vid;
		this.vstatus = vstatus;
		this.dov = dov;
		this.user = user;
		this.patient = patient;
	}
	
	public VisitMaster(vStatus vstatus, Timestamp dov, User user, PatientMaster patient) {
		super();
		this.vstatus = vstatus;
		this.dov = dov;
		this.user = user;
		this.patient = patient;
	}

	public VisitMaster() {
		// TODO Auto-generated constructor stub
	}

	public Long getVid() {
		return vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
	}
	public vStatus getVstatus() {
		return vstatus;
	}
	public void setVstatus(vStatus vstatus) {
		this.vstatus = vstatus;
	}
	public Timestamp getDov() {
		return dov;
	}
	public void setDov(Timestamp dov) {
		this.dov = dov;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public PatientMaster getPatient() {
		return patient;
	}
	public void setPatient(PatientMaster patient) {
		this.patient = patient;
	}
	
	@Override
	public String toString() {
		return "VisitMaster [vid=" + vid + ", vstatus=" + vstatus + ", dov=" + dov + ", user=" + user + ", patient="
				+ patient + "]";
	}	
}
