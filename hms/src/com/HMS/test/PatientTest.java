package com.HMS.test;

import java.nio.channels.NetworkChannel;

import com.HMS.commonUtills.Status;
import com.HMS.models.Client;
import com.HMS.models.User;
import com.HMS.services.PatientServices;
import com.HMS.services.UserServices;

public class PatientTest {
	
	public static void main(String[] args) {
		
		PatientServices patientservices = new PatientServices();
		User user = new User();
		Client client = new Client();
//		client.setId(1);
		client.setHname("hos2");
		client.setContact(9892);
		client.setAddress("add2");
		int clientid = userservices.addClient(client);
		client.setId(clientid);
		System.out.println(client.toString());
		user.setId(1);
		user.setUsername("user2");
		user.setPassword("pas2");
		user.setFname("fname2");
		user.setMname("mid2");
		user.setLname("lname2");
		user.setEmail("2@gmail.com");
		user.setGender("Male");
		user.setContact((long) 1232);
		user.setStatus(Status.ACTIVE);
		user.setClient(client);
		userservices.addUser(user);
		System.out.println("usertest :"+user.getUsername()+user.getPassword()+user.getFname()+user.getMname()+user.getLname()+user.getEmail()+user.getGender()+user.getContact()+user.getStatus()+user.getClient());
		System.out.println(user.toString());
		
	}
	
    

}

