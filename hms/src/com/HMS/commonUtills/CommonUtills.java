package com.HMS.commonUtills;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CommonUtills {
	
	public static String convertIntToString(int i) {
        try {
            return Integer.toString(i);
        } catch (Exception e) {
            return null;
        }
    }

	public static int convertStringToInt(String parameter) {
		return Integer.parseInt(parameter);
	}
	
	public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

	public static Date convertStringtoDate(String SDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date date1 = null;
        try {
        		Date date2 = (Date) formatter.parse(SDate);
        		date1 = new java.sql.Date(date2.getTime());
//        		System.out.println("date came string:"+Date);
//	        	SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//	        	java.util.Date date = sdf.parse(Date);
//	        	System.out.println("parse date:"+date);
//	        	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//	        	System.out.println("converted date:"+sqlDate);
//	        	
        } catch (Exception e) {
            return null;
        }
        return date1;
    }
}
