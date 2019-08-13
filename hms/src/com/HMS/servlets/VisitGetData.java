package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.models.VisitMaster;
import com.HMS.services.VisitService;
import com.HMS.models.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class VisitGetData
 */
@WebServlet("/pages/VisitGetData")
public class VisitGetData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	VisitService visitservice = new VisitService();
	
    public VisitGetData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("vid");
		if(CommonUtills.isBlank(id)){
			List<VisitMaster> all = visitservice.getAll();
			String json = new Gson().toJson(all);
//			System.out.println(json.toString());
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}else{
			User user = visitservice.get(CommonUtills.convertStringToInt(id));
			String json = new Gson().toJson(user);
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
