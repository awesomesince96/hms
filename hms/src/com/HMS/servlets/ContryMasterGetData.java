package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.models.ContryMaster;
import com.HMS.services.ContryMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class ContryMasterGetData
 */
@WebServlet("/pages/ContryMasterGetData")
public class ContryMasterGetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContryMasterGetData() {
        super();
        // TODO Auto-generated constructor stub
    }

    ContryMasterService contryMasterService= new ContryMasterService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(CommonUtills.isBlank(id)){
			List<ContryMaster> all = contryMasterService.getAll();
			String json = new Gson().toJson(all);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}else{
			ContryMaster contryMaster = contryMasterService.get(CommonUtills.convertStringToInt(id));
			String json = new Gson().toJson(contryMaster);
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
