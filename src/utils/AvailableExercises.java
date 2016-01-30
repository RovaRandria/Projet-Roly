package utils;

import java.util.ArrayList;
import java.util.List;

import data.AreaOfEffect;
import data.Exercise;

public class AvailableExercises {
	public static List<String> getExercisesListString (){
		List<String> exercisesList = new ArrayList<String>();
		exercisesList.add("Pompes");
		exercisesList.add("Abdominaux");
		return exercisesList;
	}
}
