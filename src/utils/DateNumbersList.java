package utils;

import java.util.ArrayList;
import java.util.List;

public class DateNumbersList {
	
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

}
