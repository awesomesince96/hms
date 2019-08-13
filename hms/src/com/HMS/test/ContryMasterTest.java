package com.HMS.test;

import java.util.List;

import com.HMS.commonUtills.Status;
import com.HMS.models.ContryMaster;
import com.HMS.services.ContryMasterService;

public class ContryMasterTest {
	public static void main(String[] args) {
		
		ContryMasterService contryMasterService = new ContryMasterService();
		ContryMaster contryMaster = new ContryMaster();
		contryMaster.setId(2);
		contryMaster.setName("London2");
		contryMaster.setStatus(Status.ACTIVE);
		contryMasterService.add(contryMaster);
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
