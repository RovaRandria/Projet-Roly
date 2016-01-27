package utils;

import java.util.ArrayList;
import java.util.List;

import data.AreaOfEffect;
import data.Exercise;

public class AvailableExercises {
	public static List<Exercise> getExercisesList (){
		List<Exercise> exercisesList = new ArrayList<Exercise>();
		exercisesList.add(new Exercise("Pompes", "20 pompes", AreaOfEffect.PECTORALS));
		exercisesList.add(new Exercise("Abdominaux", "50 abdominaux", AreaOfEffect.ABDOMINALS));
		return exercisesList;
	}
	
	public static List<String> getExercisesListString (){
		List<String> exercisesList = new ArrayList<String>();
		exercisesList.add("Pompes");
		exercisesList.add("Abdominaux");
		return exercisesList;
	}
}
