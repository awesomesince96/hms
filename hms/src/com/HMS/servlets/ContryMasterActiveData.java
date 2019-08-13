package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.models.ContryMaster;
import com.HMS.services.ContryMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ContryMasterActiveData
 */
@WebServlet("/pages/ContryMasterActiveData")
public class ContryMasterActiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContryMasterActiveData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ContryMasterService contryMasterService = new ContryMasterService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		contryMasterService.getCountryBystatus();
		List<ContryMaster> contryByStatus = contryMasterService.getCountryBystatus();
		String json = new Gson().toJson(contryByStatus);
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
