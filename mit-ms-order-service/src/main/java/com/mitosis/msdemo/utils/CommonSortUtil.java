package com.mitosis.msdemo.utils;

import java.util.Date;

public class CommonSortUtil {

	public static int sortByDateComparatorResult(Date aDate, Date bDate) {
		int result = 0;
		if(aDate != null && bDate != null){
			if(aDate.before(bDate))
				result = -1;
			else if(aDate.after(bDate))
				result = 1;
		}else if(aDate == null && bDate != null){
			result = -1;
		}else if(aDate != null && bDate == null){
			result = 1;
		}
		return result;
	}
	
	
}
