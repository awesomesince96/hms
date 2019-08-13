package com.HMS.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.HMS.commonUtills.CommonUtills;
import com.HMS.commonUtills.Status;
import com.HMS.commonUtills.vStatus;
import com.HMS.models.Bill;
import com.HMS.models.ContryMaster;
import com.HMS.models.PatientMaster;
import com.HMS.models.User;
import com.HMS.models.VisitMaster;
import com.HMS.services.BillService;
import com.HMS.services.VisitService;
import com.google.gson.Gson;

/**
 * Servlet implementation class VisitAdd
 */
@WebServlet("/pages/VisitAdd")
public class VisitAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VisitService visitservice = new VisitService();
		long pid = Long.parseLong(request.getParameter("pid"));
		 List<VisitMaster> checkopen = visitservice.checkopen(pid);
//		 if(checkopen.isEmpty())
//		 {
//			 System.out.println("it is empty");
//		 for (VisitMaster visitMaster : checkopen) {
//			System.out.println("visitMaster :: >> "+visitMaster);
//		}
//		 }else{
//			 System.out.println("not empty");
//		 }
//		 System.out.println(checkopen);
		if(checkopen.isEmpty())
		{
			try {
				System.out.println("this is servlet");
				VisitMaster visitmaster = new VisitMaster();
				Bill bill = new Bill();
				PatientMaster patientmaster = new PatientMaster();
				User user = new User();
				BillService billservice = new BillService();
				
				HttpSession session = request.getSession();
				Calendar calendar = Calendar.getInstance();
				Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
				visitmaster.setDov(timestamp);
				String status = request.getParameter("status");
				if (status.equals("OPEN")) {
					visitmaster.setVstatus(vStatus.OPEN);
					} else if (status.equals("CLOSE")) {
					visitmaster.setVstatus(vStatus.CLOSE);
				}
				long uid = Long.parseLong(request.getParameter("userid"));
			    user.setId(uid);
			    visitmaster.setUser(user);
				patientmaster.setPid((Long.parseLong(request.getParameter("pid"))));
				visitmaster.setPatient(patientmaster);
				System.out.println(patientmaster.toString());
				int vid = visitservice.add(visitmaster);
				visitmaster.setVid((long) vid);
				user.setId(uid);
				bill.setVisit(visitmaster);
				bill.setUser(user);
				bill.setAmount(0);
				billservice.add(bill);
//				String toast = null;
				String toast = new Gson().toJson("Successfully Data Added.");
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");	
			    response.getWriter().write(toast);
				}
			    catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else{

			response.setStatus(600);
     		String toast = new Gson().toJson("Visit Already Exists");
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");	
		    response.getWriter().write(toast);
//			throw new IOException("Already exist");
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
