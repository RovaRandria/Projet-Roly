package jdbc;

import java.util.ArrayList;

public class Sport {
		private int id;
		private String name;
		private ArrayList<Performance> performance;
		private ArrayList<Exercise> exercises;
		
		public Sport() {
			
		}
		public Sport(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
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

		public ArrayList<Performance> getPerformance() {
			return performance;
		}

		public void setPerformance(ArrayList<Performance> performance) {
			this.performance = performance;
		}

		public ArrayList<Exercise> getexercises() {
			return exercises;
		}

		public void setExercises(ArrayList<Exercise> exercises) {
			this.exercises = exercises;
		}
		
		
 
		
}
