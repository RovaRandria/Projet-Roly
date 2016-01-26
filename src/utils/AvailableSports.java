package utils;

import java.util.ArrayList;
import java.util.List;

import data.Sport;

public class AvailableSports {
	public static List<Sport> getSportsList (){
		List<Sport> sportsList = new ArrayList<Sport>();
		sportsList.add(new Sport("Escalade"));
		sportsList.add(new Sport("Course"));
		sportsList.add(new Sport("Danse"));
		sportsList.add(new Sport("Yoga"));
		sportsList.add(new Sport("Cyclisme"));
		sportsList.add(new Sport("Escrime"));
		return sportsList;
	}
	
	public static List<String> getSportsListString (){
		List<String> sportsList = new ArrayList<String>();
		sportsList.add("Escalade");
		sportsList.add("Course");
		sportsList.add("Danse");
		sportsList.add("Yoga");
		sportsList.add("Cyclisme");
		sportsList.add("Escrime");
		return sportsList;
	}
	
	
}
