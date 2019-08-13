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
import com.HMS.models.StateMaster;
import com.HMS.services.ContryMasterService;
import com.HMS.services.StateMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class StateMasterAdd
 */
@WebServlet("/pages/StateMasterAdd")
public class StateMasterAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StateMasterAdd() {
        super();
        // TODO Auto-generated constructor stub
    }
    StateMasterService stateMasterService = new StateMasterService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temp = request.getParameter("id");
		int id = 0;
		if(temp != null){
			 id = CommonUtills.convertStringToInt(temp);
		}
		
		StateMaster stateMaster = new StateMaster();
		ContryMasterService contryMasterService = new ContryMasterService();
		
		stateMaster.setName(request.getParameter("name"));
		String status = request.getParameter("status");
		if (status.equals("ACTIVE")) {
			stateMaster.setStatus(Status.ACTIVE);
		} else if (status.equals("INACTIVE")) {
			stateMaster.setStatus(Status.INACTIVE);
		}
		int country = CommonUtills.convertStringToInt(request.getParameter("contry"));
		stateMaster.setContryMaster(contryMasterService.get(country));
		
		String json = null;
		
		if(id > 0){
			stateMaster.setId(id);
			stateMasterService.update(stateMaster);
			json = new Gson().toJson("Successfully Data Updated.");
		}else{
			stateMasterService.add(stateMaster);
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
