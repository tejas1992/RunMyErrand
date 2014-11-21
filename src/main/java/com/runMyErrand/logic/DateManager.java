package com.runMyErrand.logic;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateManager {
		
	//Manages the recuuring dates
	 public static String recurring(String date, String recurringType) throws ParseException {

	        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	        Date d = dateformat.parse(date);
	        Calendar c = Calendar.getInstance();
	        c.setTime(d);
	        if(recurringType.equalsIgnoreCase("Weekly"))
	            c.add(Calendar.DATE, 7); 
	        if(recurringType.equalsIgnoreCase("Monthly"))
	            c.add(Calendar.MONTH, 1); 
	        String output = dateformat.format(c.getTime());
	        return output;
	    }
	 
	 public static int dateDifference(String date1, String date2){

		 long diffDays =0;
		 try{
			 DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			 Date d1 = dateformat.parse(date1);
			 Date d2 = dateformat.parse(date2);
			 long diff = d2.getTime() - d1.getTime();
			 diffDays = diff / (24 * 60 * 60 * 1000);
		 }
		 catch(Exception e){}
         return (int) diffDays;
     }
}
