/**
 * 
 */
package com.mitosis.msdemo.utils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author mitosis
 *
 */
public class CommonUtils 
{
	
	public static boolean validateString(String value){
		if(value==null || value.trim().equals("")){
			return false;
		}
		return true;
	}
	
	public static boolean validateList(List<?> list){
		if(list==null || list.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static int getRandomNumber(int minimum, int maximum){
		Random rn = new Random();
		int n = maximum - minimum + 1;
		int i = rn.nextInt() % n;
		return minimum + i;
	}
		
		
	public static String generateRandomInteger(int length){
	    List<Integer> numbers = new ArrayList<>();
	    for(int i = 0; i < 10; i++){
	        numbers.add(i);
	    }

	    Collections.shuffle(numbers);

	    String result = "";
	    for(int i = 0; i < length; i++){
	        result += numbers.get(i).toString();
	    }
		    
		return result;
	}
	
	public static String currentDate() {
//	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E M d HH:mm:ss z yyyy");// yyyy-MM-dd
//	  //Sat Jan 28 17:54:12 IST 2017
//	  LocalDate localDate = LocalDate.now();
//	  return dtf.format(localDate); 
/* 
	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
	  Calendar cal = Calendar.getInstance();
	  return dateFormat.format(cal);*/
      SimpleDateFormat sd = new SimpleDateFormat("E M d HH:mm:ss z yyyy");
      Date date = new Date();
      sd.setTimeZone(TimeZone.getTimeZone("IST"));
	  return sd.format(date);
	}
	
	public static String passwordToHash(String password) {
		if(!CommonUtils.validateString(password))
			password = "fmn@123";
	    String passwordToHash = password;
	    String generatedPassword = null;
	    try {
	      // Create MessageDigest instance for MD5
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      // Add password bytes to digest
	      md.update(passwordToHash.getBytes());
	      // Get the hash's bytes
	      byte[] bytes = md.digest();
	      // This bytes[] has bytes in decimal format;
	      // Convert it to hexadecimal format
	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < bytes.length; i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	      }
	      // Get complete hashed password in hex format
	      generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    //System.out.println(generatedPassword);
	    return generatedPassword;
	  }
	
	public static String convertDateToStringWithFormat(Date date, String format) {
		String dateString = null;
		try {
		    SimpleDateFormat f = new SimpleDateFormat(format);
		    dateString = f.format(date);
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return dateString;
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
	
	public static List<String> convertDateListToStringListWithFormat(List<Date> requestedDateList, String format) {
		List<String> dateStringList = new ArrayList<String>();
		if(validateList(requestedDateList)){
			for(Date date:requestedDateList){
				if(date != null){
					String dateString = convertDateToStringWithFormat(date, format);
					if(validateString(dateString))
						dateStringList.add(dateString);
				}
			}
		}
		return dateStringList;
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

	  public static String getCurrentDateTimeStr(String dateString, String format) {
	    TimeZone tz = TimeZone.getTimeZone("UTC");
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
	    df.setTimeZone(tz);
	    String nowAsISO = df.format(new Date());        
	    System.out.println("nowAsISO  = "+nowAsISO);	     
	    return nowAsISO;
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
	  
	  public  boolean equalLists(List<String> one, List<String> two){     
		    if (one == null && two == null){
		        return true;
		    }

		    if((one == null && two != null) 
		      || one != null && two == null
		      || one.size() != two.size()){
		        return false;
		    }

		    //to avoid messing the order of the lists we will use a copy
		    //as noted in comments by A. R. S.
		    one = new ArrayList<String>(one); 
		    two = new ArrayList<String>(two);   

		    Collections.sort(one);
		    Collections.sort(two);      
		    return one.equals(two);
		}

	  
	  public static  String getFile(String fileName) {

			StringBuilder result = new StringBuilder("");

			//Get file from resources folder
	         ClassLoader classLoader = CommonUtils.class.getClassLoader();

			
			//HubCustomerOrderServiceImpl.class
			File file = new File(classLoader.getResource(fileName).getFile());
			System.out.println("File path: "+file.toString());
			try (Scanner scanner = new Scanner(file)) {

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}

				scanner.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
				
			return result.toString();

		  }

	    @SuppressWarnings("unchecked")
		public static Object setAuditColumnInfo(String fullyQualifiedName,String userId)
				   throws Exception {
				  Class cls = Class.forName(fullyQualifiedName);
				  Object obj = cls.newInstance();
				  Class[] paramString = new Class[1];
				  paramString[0] = String.class;
				  // TODO:Need to get user from session and set here
				  Method method = cls.getDeclaredMethod("setCreatedby", paramString);
				  if(userId==null){
				   method.invoke(obj, "123");
				  }else{
				   method.invoke(obj,userId);
				  }
				  method = cls.getDeclaredMethod("setUpdatedby", paramString);
				  if(userId==null){
				   method.invoke(obj, "123");
				  }else{
				   method.invoke(obj,userId); 
				  }
				  Class[] paramString1 = new Class[1];
				  paramString1[0] = Date.class;
				  method = cls.getDeclaredMethod("setUpdated", paramString1);
				  method.invoke(obj, new Date());
				  method = cls.getDeclaredMethod("setCreated", paramString1);
				  method.invoke(obj, new Date());
				  /*Class[] paramString2 = new Class[1];
				  paramString2[0] = Character.class;
				  method = cls.getDeclaredMethod("setIsactive", paramString2);
				  method.invoke(obj, 'Y');*/
				  return obj;
		}

	    
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
		    return cal.getTime(); // returns new date object, one hour in the future
		  }
		  
		  public static Date addDays(Date date, int days){
		  	Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(date); // sets calendar time/date
		    cal.add(Calendar.DATE, days); // adds days
		    return cal.getTime(); // returns new date object, one hour in the future
		  }
		  
		  public static Date addSeconds(Date date, int seconds){
			  	Calendar cal = Calendar.getInstance(); // creates calendar
			    cal.setTime(date); // sets calendar time/date
			    cal.add(Calendar.SECOND, seconds); // adds days
			    return cal.getTime(); // returns new date object, one hour in the future
		  }

		  /*public static Date convertString2Date(String datestr, String format ){
		   		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
	    		DateTime dt = formatter.parseDateTime(datestr);
	    				
	    		return new Date(dt.getMillis());
	 

		  }*/
		  
		  public static Date addMins(Date date, int mins){
			  	Calendar cal = Calendar.getInstance(); // creates calendar
			    cal.setTime(date); // sets calendar time/date
			    cal.add(Calendar.MINUTE, mins); // adds mins
			    return cal.getTime(); // returns new date object, one hour in the future
		  }
		  
		  public static Date getFirstDateOfWeek(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
			  return clearTime(cal.getTime());
		  }
		  
		  public static Date getLastDateOfWeek(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
			  return clearTime(cal.getTime());
		  }
		  
		  public static Date getFirstDateOfMonth(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			  return clearTime(cal.getTime());
		  }
		  
		  public static Date getLastDateOfMonth(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			  return clearTime(cal.getTime());
		  }
		  
		  public static Date getFirstDateOfYear(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));    
			  return clearTime(cal.getTime());
		  }
		  
		  public static Date getLastDateOfYear(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH)); 
			  cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			  return clearTime(cal.getTime());
		  }
		  
		  public static Date clearTime(Date date){
			  Calendar cal = Calendar.getInstance();
			  cal.setTime(date); 
			  cal.set(Calendar.HOUR_OF_DAY, 0);
			  cal.set(Calendar.MINUTE,0);
			  cal.set(Calendar.SECOND, 0);
			  cal.set(Calendar.MILLISECOND, 0);
			  return cal.getTime();
		  }
		  
		  
		  public static String formatDouble(double value){
			  	DecimalFormat df = new DecimalFormat("#.00");
			    String angleFormated = df.format(value);
			    if(angleFormated.indexOf(".")==0)
			    	angleFormated = "0" + angleFormated;
			    else if(angleFormated.indexOf("-")==0 && angleFormated.indexOf(".")==1)
			    	angleFormated = angleFormated.replace("-", "-0");
			    return angleFormated;
		  }
			
			public static String joinString(String delimeter, List<String> values) {
				String result = "";
				for(String value:values){
					result = result + delimeter + value;
				}
				return result;
			}

			public static String joinInteger(String delimeter, List<Integer> values) {
				String result = "";
				for(Integer value:values){
					result = result + delimeter + value;
				}
				return result;
			}
			
			public static String joinDouble(String delimeter, List<Double> values) {
				String result = "";
				for(Double value:values){
					result = result + delimeter + value;
				}
				return result;
			}
			
			public static double roundDouble(double value, int places) {
			    if (places < 0) throw new IllegalArgumentException();

			    long factor = (long) Math.pow(10, places);
			    value = value * factor;
			    long tmp = Math.round(value);
			    return (double) tmp / factor;
			}
			
			public static boolean convertStringToBoolean(String text) {
				boolean result = false;
				if(text != null && (text.equalsIgnoreCase("yes") || text.equalsIgnoreCase("y") ||text.equalsIgnoreCase("true") || text.equalsIgnoreCase("t")))
					result = true;
			    return result;
			}
			

			/**
			 * 
			 * @author Anbukkani G
			 * @param strSelectedDates
			 * @param selectedDateList
			 * @param format 
			 * @return
			 */
			public static List<Date> convertStringListToDateList( List<String> strSelectedDates, String format) {
				List<Date> selectedDateList= new ArrayList<Date>();
				if(strSelectedDates!=null){
				for(String strSelectedDate:strSelectedDates){
					Date date = convertStringToDateWithFormat(strSelectedDate,format);
					selectedDateList.add(date);
				}
				}
				return selectedDateList;
			}
			
			/**
			 * 
			 * @author Anbukkani G
			 * @param selectedDateList
			 * @param strSelectedDates
			 * @return
			 */
			public static List<String> convertDateListToStringList(List<Date> selectedDateList,String format) {
				 List<String> strSelectedDates= new ArrayList<String>();
				if(strSelectedDates!=null){
				for(Date selectedDate:selectedDateList){
					
					String dateStr = convertDateToStringWithFormat(selectedDate, format);
					strSelectedDates.add(dateStr);
				}
				}
				return strSelectedDates;
			}
			

			public static boolean containsStringInList(List<String> list, String text) {
				boolean result = false;
				if(validateList(list) && validateString(text)){
				    for (String p : list) {
				        if (p.contains(text)) {
				            return true;
				        }
				    }
				}
				    
			    return result;
			}

			public static Date setDefaultYear(Date date) {
				String format = "dd MM";
				String dateStr = convertDateToStringWithFormat(date, format);
				date = convertStringToDateFormat(dateStr, format);
				return date;
			}
			

			public static String html2text(String html) {
				if(validateString(html))
					html = html.replaceAll("\\<.*?\\>", "");
				 return html;
			}

}
