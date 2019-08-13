package com.HMS.test;

import java.util.List;

import com.HMS.commonUtills.Status;
import com.HMS.models.CityMaster;
import com.HMS.models.StateMaster;
import com.HMS.services.CityMasterService;
import com.HMS.services.ContryMasterService;
import com.HMS.services.StateMasterService;

public class CityMasterTest {
	public static void main(String[] args) {

		StateMasterService stateMasterService = new StateMasterService();
		ContryMasterService contryMasterService = new ContryMasterService();
		StateMaster stateMaster = new StateMaster();
		CityMasterService service = new CityMasterService();
		List<CityMaster> list = service.getCityBystatus();
		System.out.println(list);
//		stateMaster.setName("gujarat");
//		stateMaster.setStatus(Status.ACTIVE);
//		stateMaster.setContryMaster(contryMasterService.get(1));
//		stateMasterService.add(stateMaster);
		
//		stateMasterService.inactive(1);
//		List<StateMaster> all = stateMasterService.getAll();
//		for (StateMaster stateMaster2 : all) {
//			System.out.println(""+stateMaster2.toString());
//		}
//		
		
		
	}
}
