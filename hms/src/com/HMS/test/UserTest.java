package com.HMS.test;

import com.HMS.models.Client;
import com.HMS.models.User;
import com.HMS.services.UserServices;
import com.HMS.commonUtills.Status;

public class UserTest {
	
	public static void main(String[] args) {
		

		UserServices userservices = new UserServices();
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
