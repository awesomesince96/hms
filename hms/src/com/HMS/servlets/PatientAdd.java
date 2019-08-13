package com.HMS.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Status;
import com.HMS.models.CityMaster;
import com.HMS.models.Client;
import com.HMS.models.ContryMaster;
import com.HMS.models.PatientMaster;
import com.HMS.models.StateMaster;
import com.HMS.models.User;
import com.HMS.services.PatientServices;
import com.HMS.services.UserServices;
import com.google.gson.Gson;

/**
 * Servlet implementation class PatientAdd
 */
@WebServlet("/pages/PatientAdd")
public class PatientAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	
		
		PatientMaster patient = new PatientMaster();
		PatientServices patientservice = new PatientServices();
		User user = new User();
		CityMaster city = new CityMaster();
		StateMaster state = new StateMaster();
		ContryMaster country = new ContryMaster();
		patient.setFname(request.getParameter("fname"));
		patient.setMname(request.getParameter("mname"));
		patient.setLname(request.getParameter("lname"));
		patient.setGender(request.getParameter("gender"));
		java.sql.Date date = (Date) CommonUtills.convertStringtoDate(request.getParameter("dob"));
		String date1  = request.getParameter("dob");
		patient.setDob(date);
		patient.setBloodgroup(request.getParameter("bloodgroup"));
		patient.setPhone(Long.parseLong(request.getParameter("phone")));
		patient.setAddress(request.getParameter("address"));
		city.setId(Long.parseLong(request.getParameter("cityId")));
		patient.setCity(city);
		state.setId(Long.parseLong(request.getParameter("stateId")));
		patient.setState(state);
		country.setId(Long.parseLong(request.getParameter("contryId")));
		patient.setCountry(country);
		user.setId(Long.parseLong(request.getParameter("userId")));
		patient.setUser(user);
		patient.setEmail(request.getParameter("email"));
		patientservice.add(patient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = null;
		json = new Gson().toJson("Successfully Data Added.");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
