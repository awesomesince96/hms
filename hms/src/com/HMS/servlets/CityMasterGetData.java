package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.models.CityMaster;
import com.HMS.models.ContryMaster;
import com.HMS.services.CityMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class CityMasterGetData
 */
@WebServlet("/pages/CityMasterGetData")
public class CityMasterGetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityMasterGetData() {
        super();
        // TODO Auto-generated constructor stub
    }

    CityMasterService cityMasterService = new CityMasterService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(CommonUtills.isBlank(id)){
			List<CityMaster> all = cityMasterService.getAll();
			String json = new Gson().toJson(all);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}else{
			CityMaster cityMaster = cityMasterService.get(CommonUtills.convertStringToInt(id));
			String json = new Gson().toJson(cityMaster);
			System.out.println(json);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
