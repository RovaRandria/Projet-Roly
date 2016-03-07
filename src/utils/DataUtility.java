package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Includes display and conversion utilities
 * @author Rova
 *
 */
public class DataUtility {
	/**
	 * Create a date from the format 'dd/MM/yyyy'
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
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

	/**
	 * A list that contains all the sport names
	 * @return
	 */
	public static List<String> getSportsListString (){
		List<String> sportsList = new ArrayList<String>();
		sportsList.add("Escalade");
		sportsList.add("Jogging");
		sportsList.add("Ski");
		sportsList.add("Vélo");
		sportsList.add("Musculation");
		return sportsList;
	}

	/**
	 * convert the number of month to the corresponding strings
	 * @param month
	 * @return
	 */
	public static String convertMonth (int month){
		String[] monthName = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" };
		return monthName[month-1];
	}

	public static List<Integer> day() {
		List <Integer> day = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) {
			day.add(i);
		}
		return day;
	}

	public static List<Integer> month() {
		List <Integer> month = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			month.add(i);
		}
		return month;
	}

	public static List<Integer> year() {
		List <Integer> year = new ArrayList<Integer>();
		for (int i = 1900; i <= 2016; i++) {
			year.add(i);
		}
		return year;
	}

	/**
	 * Used to select the number of series for an exercise
	 * @return
	 */
	public static List<Integer> numberOfSeries() {
		List <Integer> numberOfSeries = new ArrayList<Integer>();
		for (int i = 0; i <= 100; i++) {
			numberOfSeries.add(i);
		}
		return numberOfSeries;
	}


}
