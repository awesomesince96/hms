package com.HMS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Status;
import com.HMS.models.CityMaster;
import com.HMS.services.CityMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class CityMasterAdd
 */
@WebServlet("/pages/CityMasterAdd")
public class CityMasterAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityMasterAdd() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    CityMasterService cityMasterService = new CityMasterService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = request.getParameter("id");
		int id = 0;
		if(temp != null){
			 id = CommonUtills.convertStringToInt(temp);
		}
		
		CityMaster cityMaster = new CityMaster();
		
		cityMaster.setName(request.getParameter("name"));
		String status = request.getParameter("status");
		if (status.equals("ACTIVE")) {
			cityMaster.setStatus(Status.ACTIVE);
		} else if (status.equals("INACTIVE")) {
			cityMaster.setStatus(Status.INACTIVE);
		}
		
		String json = null;
		
		if(id > 0){
			cityMaster.setId(id);
			cityMasterService.update(cityMaster);
			json = new Gson().toJson("Successfully Data Updated.");
		}else{
			cityMasterService.add(cityMaster);
			json = new Gson().toJson("Successfully Data Added.");
		}
		
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
