package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.models.CityMaster;
import com.HMS.models.StateMaster;
import com.HMS.services.CityMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class CitryMasterActiveDate
 */
@WebServlet("/pages/CityMasterActiveData")
public class CityMasterActiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityMasterActiveData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    CityMasterService cityservice = new CityMasterService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//cityservice.getCityBystatus();
		long stateid = Long.parseLong(request.getParameter("stateId"));
		
		CityMasterService service = new CityMasterService();
		List<CityMaster> list = service.getCityBystatus(stateid);
		
		String json = new Gson().toJson(list);
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
