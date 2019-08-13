package com.HMS.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.models.Bill;
import com.HMS.models.ServiceMapping;
import com.HMS.services.BillService;
import com.HMS.services.ServiceMappingService;
import com.google.gson.Gson;

/**
 * Servlet implementation class BillActiveData
 */
@WebServlet("/pages/BillActiveData")
public class BillActiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	BillService billservice = new BillService();
	ServiceMappingService mappingservice = new ServiceMappingService();
	
    public BillActiveData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("this is vid : "+ Long.parseLong(request.getParameter("id")) );
		long vid = Long.parseLong(request.getParameter("id"));
		long bid = billservice.getVisitId(vid);
		List<ServiceMapping> servicemapping = mappingservice.get(bid);
		String json = new Gson().toJson(servicemapping);
		System.out.println("json: "+json);
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
