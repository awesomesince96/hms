package com.HMS.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.services.CityMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class CityMasterStatus
 */
@WebServlet("/pages/CityMasterStatus")
public class CityMasterStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityMasterStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

    CityMasterService cityMasterService = new CityMasterService();	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		if (status.equals("ACTIVE")) {
			cityMasterService.active(CommonUtills.convertStringToInt(request.getParameter("id")));

		} else if (status.equals("INACTIVE")) {
			cityMasterService.inactive(CommonUtills.convertStringToInt(request.getParameter("id")));
		}
		
		String json = new Gson().toJson("Successfully Data Updated.");
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
