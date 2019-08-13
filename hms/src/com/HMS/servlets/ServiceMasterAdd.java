package com.HMS.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Status;
import com.HMS.models.ServiceMaster;
import com.HMS.services.ServiceMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ServiceMasterAdd
 */
@WebServlet("/pages/ServiceMasterAdd")
public class ServiceMasterAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	ServiceMasterService servicemasterservice = new ServiceMasterService();
	
    public ServiceMasterAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = request.getParameter("id");
		int id = 0;
		if(temp != null){
			 id = CommonUtills.convertStringToInt(temp);
		}
		ServiceMaster serviceMaster = new ServiceMaster();
//		long id = Long.parseLong(request.getParameter("id"));
		serviceMaster.setName(request.getParameter("name"));
		serviceMaster.setAmount(Double.parseDouble(request.getParameter("amount")));
		String status = request.getParameter("status");
		if (status.equals("ACTIVE")) {
			serviceMaster.setStatus(Status.ACTIVE);
		} else if (status.equals("INACTIVE")) {
			serviceMaster.setStatus(Status.INACTIVE);
		}
		
		String json = null;
		
		if(id > 0){
			serviceMaster.setId((long) id);
			servicemasterservice.update(serviceMaster);
			json = new Gson().toJson("Successfully Data Updated.");
		}else{
			try {
				servicemasterservice.add(serviceMaster);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
