package com.dumu.housego.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeTurnDate {
	public static String getStandardTime(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" , Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        Date date = new Date(string);
        sdf.format(date);
        return sdf.format(date);
}
	
	 public static String getStringDate(String ab) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());  
	        long times =Long.valueOf(ab);  
//	        System.out.println(times);  
	        Date date = new Date(times);  
	        String tim = sdf.format(date);
			return tim;  
		 }
	 
	 public static String getStringDate(long times) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ",Locale.getDefault());  
	        Date date = new Date(times);  
	        String tim = sdf.format(date);
			return tim;  
		 }
	
}