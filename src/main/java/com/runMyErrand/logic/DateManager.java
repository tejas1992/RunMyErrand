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
}
