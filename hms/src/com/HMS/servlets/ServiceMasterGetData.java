package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.models.ServiceMaster;
import com.HMS.services.ServiceMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ServiceMasterGetData
 */
@WebServlet("/pages/ServiceMasterGetData")
public class ServiceMasterGetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ServiceMasterService serviceMasterService= new ServiceMasterService();
	
    public ServiceMasterGetData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if(CommonUtills.isBlank(id)){
			List<ServiceMaster> all = serviceMasterService.getAll();
			String json = new Gson().toJson(all);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}else{
			ServiceMaster serviceMaster = serviceMasterService.get(CommonUtills.convertStringToInt(id));
			String json = new Gson().toJson(serviceMaster);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
