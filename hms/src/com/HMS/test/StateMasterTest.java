package com.HMS.test;

import com.HMS.commonUtills.Status;
import com.HMS.models.StateMaster;
import com.HMS.services.ContryMasterService;
import com.HMS.services.StateMasterService;

public class StateMasterTest {
	public static void main(String[] args) {
		
		StateMasterService stateMasterService = new StateMasterService();
		ContryMasterService contryMasterService = new ContryMasterService();
		StateMaster stateMaster = new StateMaster();
//		stateMaster.setId(1);
		stateMaster.setName("london");
		stateMaster.setStatus(Status.ACTIVE);
		stateMaster.setContryMaster(contryMasterService.get(3));
		
		stateMasterService.add(stateMaster);
//		contryMasterService.update(contryMaster);
		
//		ContryMaster contryMaster3 = contryMasterService.get(2);
//		System.out.println("get :: >>>"+contryMaster3.toString());
		
//		contryMasterService.active(1);
//		contryMasterService.inactive(1);
		
//		List<ContryMaster> all = contryMasterService.getAll();

//		for (ContryMaster contryMaster2 : all) {
//			System.out.println("contryMaster2 :: >> "+contryMaster2);
//		}
	}

}
