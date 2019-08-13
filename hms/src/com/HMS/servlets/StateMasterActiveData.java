package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.models.ContryMaster;
import com.HMS.models.StateMaster;
import com.HMS.services.StateMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class StateMasterActiveData
 */
@WebServlet("/pages/StateMasterActiveData")
public class StateMasterActiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StateMasterActiveData() {
        super();
        // TODO Auto-generated constructor stub
    }

    StateMasterService stateMasterService = new StateMasterService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("countryID: "+Long.parseLong(request.getParameter("countryId")));
		long countryid = Long.parseLong(request.getParameter("countryId"));
		List<StateMaster> stateByStatus = stateMasterService.getStateBystatus(countryid);
		String json = new Gson().toJson(stateByStatus);
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
