package com.runMyErrand.logic;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateManager {
	
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

	    public static void main(String[] args) throws ParseException {

	        String date1 = "2014-11-30";
	        String updatedDate = recurring(date1, "Monthly");
	        System.out.println(updatedDate);
	    }

}
