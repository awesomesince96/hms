package com.HMS.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Status;
import com.HMS.models.ContryMaster;
import com.HMS.services.ContryMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ContryMasterAdd
 */
@WebServlet("/pages/ContryMasterAdd")
public class ContryMasterAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContryMasterAdd() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ContryMasterService contryMasterService = new ContryMasterService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = request.getParameter("id");
		int id = 0;
		if(temp != null){
			 id = CommonUtills.convertStringToInt(temp);
		}
		ContryMaster contryMaster = new ContryMaster();
		
		contryMaster.setName(request.getParameter("name"));
		String status = request.getParameter("status");
		if (status.equals("ACTIVE")) {
			contryMaster.setStatus(Status.ACTIVE);
		} else if (status.equals("INACTIVE")) {
			contryMaster.setStatus(Status.INACTIVE);
		}
		
		String json = null;
		
		if(id > 0){
			contryMaster.setId(id);
			contryMasterService.update(contryMaster);
			json = new Gson().toJson("Successfully Data Updated.");
		}else{
			contryMasterService.add(contryMaster);
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
