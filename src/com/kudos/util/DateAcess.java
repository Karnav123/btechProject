package com.kudos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAcess {
	Date textDate = new Date();
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	String mysqlDateString = formatter.format(textDate);
	//System.out.println("Java's Default Date Format: "+ textDate);
    //System.out.println("Mysql's Default Date Format: " + mysqlDateString);



	public String simpleDateFormat(String pattern) {
		
		return pattern;
		
	}

}
