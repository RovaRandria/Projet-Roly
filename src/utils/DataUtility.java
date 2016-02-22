package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtility {
	public static Date createDate(int day, int month, int year) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			StringBuffer dateString = new StringBuffer();
			dateString.append(String.valueOf(day));
			dateString.append("/");
			dateString.append(String.valueOf(month));
			dateString.append("/");
			dateString.append(String.valueOf(year));
			return format.parse(dateString.toString());
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("null")
	public static int[] parseDate(Calendar cal) {
		int[] parsedDate = null;	
		parsedDate[0] = cal.get(Calendar.DATE);
		parsedDate[1] = cal.get(Calendar.MONTH);
		parsedDate[2] = cal.get(Calendar.YEAR);
		return parsedDate;
	}
	
	public static String convertMonth (int month){
		String[] monthName = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" };
	    return monthName[month-1];
	}
	
	

}
