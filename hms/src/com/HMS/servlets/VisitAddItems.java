package com.HMS.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HMS.models.Bill;
import com.HMS.models.ServiceMapping;
import com.HMS.models.ServiceMaster;
import com.HMS.models.User;
import com.HMS.services.BillService;
import com.HMS.services.ServiceMappingService;
import com.HMS.services.ServiceMasterService;
import com.google.gson.Gson;

/**
 * Servlet implementation class VisitAddItems
 */
@WebServlet("/pages/VisitAddItems")
public class VisitAddItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitAddItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("this is visitadd items");
		ServiceMasterService servicemasterservice = new ServiceMasterService();
		ServiceMaster servicemaster = new ServiceMaster();
		BillService billservice = new BillService();
		Bill bill = new Bill();
		ServiceMappingService mappingservice = new ServiceMappingService();
		ServiceMapping servicemapping = new ServiceMapping();
		ArrayList<Long> list = new ArrayList<Long>();
		
		System.out.println(request.getParameter("visit id: "));
		String[] myJsonData = request.getParameterValues("json[]");
		long vid = Long.parseLong(request.getParameter("id"));
		
		
		servicemapping.setId(vid); // Setting mapping VISIT ID
		bill = billservice.get(vid);
		Long billid = bill.getId();
		double amount = bill.getAmount();
		
//		System.out.println("this is array"+myJsonData[0]);
//		servicemasterservice.get();
		int i = 0;
		
		try {
				for(String str : myJsonData)
				{
					list.add(Long.parseLong(myJsonData[i]));
					servicemaster.setId(Long.parseLong(myJsonData[i]));
					servicemapping.setService(servicemaster); //Setting SERVICE ID
					bill.setId(billid);
					servicemapping.setBill(bill);
					mappingservice.add(servicemapping);
					
					servicemaster = servicemasterservice.get(Long.parseLong(myJsonData[i]));
					amount += servicemaster.getAmount();
					System.out.println("your amount is : "+amount);
					
					System.out.println(myJsonData[i]);
					System.out.println(list);
					i++;
				}
				System.out.println("Now your total is : "+amount);
				bill.setAmount(amount);
				bill.setId(billid);
				billservice.update(bill);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		System.out.println("this is list: "+list);
//		String[] Stringarray = request.getParameterValues("json[]");
//		System.out.println("catch json : "+ request.getParameterValues("json[]"));
//		System.out.println("this is data:"+Stringarray);
//		 for(String stringValue : stringArray) {
//	            try {
//	                result.add(Integer.parseInt(stringValue));
//	            } catch(NumberFormatException nfe) {
//	                Log.w("NumberFormat", "Parsing failed! " + stringValue + " can not be an integer");
//	            } 
		String toast = new Gson().toJson("Successfully Data Added.");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");	
	    response.getWriter().write(toast);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
