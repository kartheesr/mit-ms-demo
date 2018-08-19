package com.mitosis.msdemo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class DateUtils {

	public static long getDifferenceInDays(Date startDate, Date endDate) {

	    try {
	      // in milliseconds
	      long diff = endDate.getTime() - startDate.getTime();

	      long diffSeconds = diff / 1000 % 60;
	      long diffMinutes = diff / (60 * 1000) % 60;
	      long diffHours = diff / (60 * 60 * 1000) % 24;
	      long diffDays = diff / (24 * 60 * 60 * 1000);

	      long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);

	      System.out.print(diffDays + " days, ");
	      System.out.print(diffHours + " hours, ");
	      System.out.print(diffMinutes + " minutes, ");
	      System.out.print(diffSeconds + " seconds.");
	      return diffDays;

	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return 0;

	 }

	  
	  public static long getDifferenceInMinutes(String startDateString, String endDateString,
		      String dateFormat) {
		    SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		    Date d1 = null;
		    Date d2 = null;

		    try {
		      d1 = format.parse(startDateString);
		      d2 = format.parse(endDateString);

		      // in milliseconds
		      long diff = d2.getTime() - d1.getTime();

		      long diffSeconds = diff / 1000 % 60;
		      long diffMinutes = diff / (60 * 1000) % 60;
		      long diffHours = diff / (60 * 60 * 1000) % 24;
		      long diffDays = diff / (24 * 60 * 60 * 1000);

		      long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);

		      System.out.print(diffDays + " days, ");
		      System.out.print(diffHours + " hours, ");
		      System.out.print(diffMinutes + " minutes, ");
		      System.out.print(diffSeconds + " seconds.");
		      return diffInMinutes;

		    } catch (Exception e) {
		      e.printStackTrace();
		    }

		    return 0;

		  }
	  
	  public static long getDifferenceInMinutes(Date d1, Date d2) {

		    try {
		      // in milliseconds
		      long diff = d2.getTime() - d1.getTime();

		      long diffSeconds = diff / 1000 % 60;
		      long diffMinutes = diff / (60 * 1000) % 60;
		      long diffHours = diff / (60 * 60 * 1000) % 24;
		      long diffDays = diff / (24 * 60 * 60 * 1000);

		      long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);

		      System.out.print(diffDays + " days, ");
		      System.out.print(diffHours + " hours, ");
		      System.out.print(diffMinutes + " minutes, ");
		      System.out.print(diffSeconds + " seconds.");
		      return diffInMinutes;

		    } catch (Exception e) {
		      e.printStackTrace();
		    }

		    return 0;

		  }
	  
	  public static long getDifferenceInHours(Date startDate, Date endDate) {

		    try {

		    	// in milliseconds
		      long diff = endDate.getTime() - startDate.getTime();

		      long diffSeconds = diff / 1000 % 60;
		      long diffMinutes = diff / (60 * 1000) % 60;
		      long diffHours = diff / (60 * 60 * 1000) % 24;
		      long diffDays = diff / (24 * 60 * 60 * 1000);

		      //long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		      long diffInHours = TimeUnit.MILLISECONDS.toHours(diff);
		      System.out.print(diffDays + " days, ");
		      System.out.print(diffHours + " hours, ");
		      System.out.print(diffMinutes + " minutes, ");
		      System.out.print(diffSeconds + " seconds.");
		      
		      return diffInHours;

		    } catch (Exception e) {
		      e.printStackTrace();
		    }

		    return 0;

		  }
	  
	  
	  public static Date addHours(Date date, int hours){
	  	Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.HOUR_OF_DAY, hours); // adds hour
	    return cal.getTime(); // returns new date object
	  }
	  
	  public static Date addDays(Date date, int days){
	  	Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(date); // sets calendar time/date
	    cal.add(Calendar.DATE, days); // adds days
	    return cal.getTime(); // returns new date object
	  }
	  
	  public static Date addSeconds(Date date, int seconds){
		  	Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(date); // sets calendar time/date
		    cal.add(Calendar.SECOND, seconds); // adds seconds
		    return cal.getTime(); // returns new date object
	  }

	  public static Date addMins(Date date, int mins){
		  	Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(date); // sets calendar time/date
		    cal.add(Calendar.MINUTE, mins); // adds mins
		    return cal.getTime(); // returns new date object
	  }
	  
	  public static Date getFirstDateOfWeek(Date date){
		  clearTime(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
		  return cal.getTime();
	  }
	  
	  public static Date getLastDateOfWeek(Date date){
		  clearTime(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
		  return cal.getTime();
	  }
	  
	  public static Date getFirstDateOfMonth(Date date){
		  clearTime(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		  return cal.getTime();
	  }
	  
	  public static Date getLastDateOfMonth(Date date){
		  clearTime(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		  return cal.getTime();
	  }
	  
	  public static Date getFirstDateOfYear(Date date){
		  clearTime(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));    
		  return cal.getTime();
	  }
	  
	  public static Date getLastDateOfYear(Date date){
		  clearTime(date);
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH)); 
		  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		  return cal.getTime();
	  }
	  
	  public static Date clearTime(Date date){
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date); 
		  cal.set(Calendar.HOUR_OF_DAY, 0);
		  cal.set(Calendar.MINUTE,0);
		  cal.set(Calendar.SECOND, 0);
		  return cal.getTime();
	  }
	  public static Date convertDateWithFormat(Date date, String format) {
		    SimpleDateFormat f = new SimpleDateFormat(format);
		    String dateString = f.format(date);
		    Date newDate = null;
		    try {
		    	newDate = f.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    return newDate;
		  }
	  
	  public static Date convertStringToDateFormat(String dateString, String format) {
	        SimpleDateFormat formatter = new SimpleDateFormat(format);
	        Date date=null;
	        try {
	            date = formatter.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		return date;  
	  }
	  
	  public static Date convertStringToDateWithFormat(String dateString, String format) {
		   SimpleDateFormat f = new SimpleDateFormat(format);
		   Date date = null;
		   try {
		     date = f.parse(dateString);
		   } catch (ParseException e) {
		     e.printStackTrace();
		   }
		   return date;
		 }
		
}
