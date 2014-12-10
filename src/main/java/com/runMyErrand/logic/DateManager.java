package com.runMyErrand.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateManager {
	
	private static final Logger logger = Logger.getLogger(DateManager.class);
	private static DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date convertStringDate(String date){
		Date d =null;
		try{
			d = dateformat.parse(date);
		}
		catch(Exception e){}
        return d;
	}
	
	public static String convertDateString(Date d){
		String date=null;
		try{
			date = dateformat.format(d);
		}
		catch(Exception e){}
		
		return date;
	}
	//Manages the recuuring dates
	 public static String recurring(String date, int days){
		 	
		 	String output = "";
		 	try{
		 		Date d = DateManager.convertStringDate(date);
		 		Calendar c = Calendar.getInstance();
		 		c.setTime(d);
		 		c.add(Calendar.DATE, days); 
		 		output = DateManager.convertDateString(c.getTime());
		 	}
		 	catch(Exception e){
		 		logger.debug(e);
		 	}
	        return output;
	    }
	 
	 public static int dateDifference(String date1, String date2){

		 long diffDays =0;
		 try{
			 Date d1 = DateManager.convertStringDate(date1);
			 Date d2 = DateManager.convertStringDate(date1);
			 long diff = d2.getTime() - d1.getTime();
			 diffDays = diff / (24 * 60 * 60 * 1000);
		 }
		 catch(Exception e){}
    
		 return (int) diffDays;
     }
}
