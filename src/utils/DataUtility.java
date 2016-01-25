package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
