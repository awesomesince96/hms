package com.HMS.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Status;
import com.HMS.models.Client;
import com.HMS.models.User;
import com.HMS.services.UserServices;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/pages/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	User user = new User();
	Client client = new Client();
	System.out.println("gvhvhvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
	UserServices userclient = new UserServices();
	client.setHname(request.getParameter("hname"));
	client.setAddress(request.getParameter("address"));
	client.setContact(Long.parseLong(request.getParameter("contact")));
	System.out.println(client.toString());
	int clientid = userclient.addClient(client);
	user.setUsername(request.getParameter("username"));
	user.setPassword(request.getParameter("password"));
	user.setFname(request.getParameter("firstname"));
	user.setMname(request.getParameter("middlename"));
	user.setLname(request.getParameter("lastname"));
	user.setEmail(request.getParameter("email"));
	user.setGender(request.getParameter("gender"));
	user.setContact(Long.parseLong(request.getParameter("phoneno")));
	String status = request.getParameter("status");
	if (status.equals("ACTIVE")) {
		user.setStatus(Status.ACTIVE);
	} else if (status.equals("INACTIVE")) {
		user.setStatus(Status.INACTIVE);
	}
	client.setId(clientid);
	user.setClient(client);
	System.out.println(user.toString());
	userclient.addUser(user);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
