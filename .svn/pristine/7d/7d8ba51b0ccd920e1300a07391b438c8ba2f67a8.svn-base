package com.kudos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public final static SimpleDateFormat _YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat _DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
	//final static SimpleDateFormat _YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	//final static SimpleDateFormat _DD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static Date getDate(String dateStr , SimpleDateFormat sdf)
	{
		Date date = null;
		if(dateStr != null && dateStr.length()>8  )
		{
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				System.out.println("DateUtil.getDate() UNABLE TO PARSE DATE " +dateStr);
				e.printStackTrace();
			}
		}
		return date;
	}
	
	
	public static String getDateText(Date date , SimpleDateFormat sdf)
	{
		String dateStr = null;
		if(date != null )
		{
			dateStr = sdf.format(date);
		}
		return dateStr;
	}
	
	

}
