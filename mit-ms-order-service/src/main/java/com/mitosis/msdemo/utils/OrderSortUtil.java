package com.mitosis.msdemo.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.mitosis.msdemo.order.model.Order;

/**
 * @author Kartheeswaran
 *
 */
public class OrderSortUtil {
	
	private final static String SORT_DIRECTION = "DESC";

	
	public static void sort(List<Order> list, String field, String type) {
		boolean validList = CommonUtils.validateList(list);
		boolean validListSize = validList?list.size()>1:validList;
		if(validList && validListSize) {
			if(CommonUtils.validateString(field))
				field = "CREATEON";
			
			if(!CommonUtils.validateString(type) || !(type.equalsIgnoreCase("ASC") || type.equalsIgnoreCase("DESC")))
				type = "DESC";
			
			switch(field.toUpperCase()){
				case "CREATEON":
					sortByCreateOn(list, type);
					break;
			}
			
		}
	}

	private static void sortByCreateOn(List<Order> results, String type) {
		Comparator<Order> comp = (Order a, Order b) -> {
			int result = 0;
			Date aDate = a.getCreateOn();
			Date bDate = b.getCreateOn();
			try{
				result = CommonSortUtil.sortByDateComparatorResult(aDate, bDate);
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
		};
		
		Collections.sort(results, comp);
		
		if(CommonUtils.validateString(type) && type.equalsIgnoreCase(SORT_DIRECTION)){
			Collections.reverse(results);
		}
		
	}
	
}
