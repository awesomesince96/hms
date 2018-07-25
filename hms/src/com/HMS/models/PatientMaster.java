package com.HMS.models;

import java.sql.Date;
import java.sql.Timestamp;

public class PatientMaster {

	 private Long pid;
	 private String fname;
	 private String mname;
	 private String lname;
	 private String gender;
	 private Date dob;
	 private String bloodgroup;
	 private Long phone;
	 private String address;
	 private CityMaster city;
	 private StateMaster state;
	 private ContryMaster country;
	 private User user;
	 private String email;
	 private Date dor;
	 
	 public PatientMaster(){
	 }
	
	 public PatientMaster(Long pid, String fname, String mname, String lname, String gender, Date dob, String bloodgroup,
			Long phone, String address, CityMaster city, StateMaster state, ContryMaster country, User user,
			String email, Date dor) {
		super();
		this.pid = pid;
		this.fname = fname;  
		this.mname = mname;
		this.lname = lname;
		this.gender = gender;
		this.dob = dob;
		this.bloodgroup = bloodgroup;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.user = user;
		this.email = email;
		this.dor = dor;
	}	
	
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CityMaster getCity() {
		return city;
	}
	public void setCity(CityMaster city) {
		this.city = city;
	}
	public StateMaster getState() {
		return state;
	}
	public void setState(StateMaster state) {
		this.state = state;
	}
	public ContryMaster getCountry() {
		return country;
	}
	public void setCountry(ContryMaster country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDor() {
		return dor;
	}
	public void setDor(Date dor) {
		this.dor = dor;
	}

	@Override
	public String toString() {
		return "PatientMaster [pid=" + pid + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", gender="
				+ gender + ", dob=" + dob + ", bloodgroup=" + bloodgroup + ", phone=" + phone + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", user=" + user + ", email=" + email
				+ ", dor=" + dor + "]";
	}
	
	
}
