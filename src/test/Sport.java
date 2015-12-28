package test;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Sport {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;
		
		@Column(name = "name", length = 50, nullable = false)
		private String name;
		
		@Column(name = "performances")
		private ArrayList<Performance> performances;
		
		@Column(name = "exercises")
		private ArrayList<Exercise> exercises;
		
		public Sport() {
			
		}
		public Sport(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Sport [id=" + id + ", name=" + name + "]";
		}

		public ArrayList<Performance> getPerformances() {
			return performances;
		}

		public void setPerformances(ArrayList<Performance> performances) {
			this.performances = performances;
		}

		public ArrayList<Exercise> getExercises() {
			return exercises;
		}

		public void setExercises(ArrayList<Exercise> exercises) {
			this.exercises = exercises;
		}
		
		public void addExercise(Exercise exercise) {
			this.exercises.add(exercise);
		}
		
		public void removeAllExercises() {
			this.exercises.removeAll(exercises);
		}
		
		public void removeExercise(String exerciseId) {
			 for(int i = 0; i < this.exercises.size(); i++) {
				 if(this.exercises.get(i).getId().equals(exerciseId))
					 this.exercises.remove(i);
		    }   
		}
		
		public void updateExercise(String exerciseId, String areaOfEffect, String description) {
			for(int i = 0; i < this.exercises.size(); i++) {
				 if(this.exercises.get(i).getId().equals(exerciseId)) {
					 this.exercises.get(i).setAreaOfEffect(areaOfEffect);
					 this.exercises.get(i).setDescription(description);
				 }
		    }
		}
		
		public void addPerformance(Performance performance) {
			this.performances.add(performance);
		}
		
		public void removeAllPerformances() {
			this.performances.removeAll(performances);
		}
		
		public void removeExercice(String resultId) {
			 for(int i = 0; i < this.performances.size(); i++) {
				 if(this.performances.get(i).getId().equals(resultId))
					 this.performances.remove(i);
		    }   
		}
		
		public void updateExercice(String resultId, String result) {
			for(int i = 0; i < this.performances.size(); i++) {
				 if(this.performances.get(i).getResult().equals(resultId)) {
					 this.performances.get(i).setResult(result);
				 }
		    }
		}
		
}
