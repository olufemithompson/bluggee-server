package com.bluggee.utils;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static List<Long> returnIdsFromString(String searchItem){
		List<Long> ids = new ArrayList<Long>();
		if(searchItem != null && !searchItem.trim().isEmpty()){
			 String[] items = searchItem.trim().split("-");
			 try{
				 for(String item : items){
					 ids.add(Long.parseLong(item));
				 }
			 }catch(Exception e){
				 e.printStackTrace();
				 ids.clear();
			 }
		}
		return ids;	 
	}
}
