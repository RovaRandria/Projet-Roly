package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;
		
		@Column(name = "name", length = 50, nullable = false)
		private String name;		
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Exercise.class)
		@JoinTable(name = "sport_exercise", joinColumns = @JoinColumn(name = "exercise_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
		private List<Exercise> exercisesList = new ArrayList<Exercise>();
		
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

		public List<Exercise> getExercisesList() {
			return exercisesList;
		}

		public void setExercisesList(List<Exercise> exercisesList) {
			this.exercisesList = exercisesList;
		}
		
		@Override
		public String toString() {
			return "Sport [id=" + id + ", name=" + name + "]";
		}
//		
//		public void addExercise(Exercise exercise) {
//			this.exercisesList.add(exercise);
//		}
//		
//		public void removeAllExercises() {
//			this.exercisesList.removeAll(exercisesList);
//		}
//		
//		public void removeExercise(String exerciseId) {
//			 for(int i = 0; i < this.exercisesList.size(); i++) {
//				 if(this.exercisesList.get(i).getId().equals(exerciseId))
//					 this.exercisesList.remove(i);
//		    }   
//		}
//		
//		public void updateExercise(String exerciseId, AreaOfEffect areaOfEffect, String description) {
//			for(int i = 0; i < this.exercisesList.size(); i++) {
//				 if(this.exercisesList.get(i).getId().equals(exerciseId)) {
//					 this.exercisesList.get(i).setAreaOfEffect(areaOfEffect);
//					 this.exercisesList.get(i).setDescription(description);
//				 }
//		    }
//		}
		
//		public void addPerformance(Performance performance) {
//			this.performances.add(performance);
//		}
//		
//		public void removeAllPerformances() {
//			this.performances.removeAll(performances);
//		}
//		
//		public void updateExercice(String resultId, String result) {
//			for(int i = 0; i < this.exercisesList.size(); i++) {
//				 if(this.exercisesList.get(i).getResult().equals(resultId)) {
//					 this.exercisesList.get(i).setResult(result);
//				 }
//		    }
//		}
		
//		public void removeExercice(String exerciseId) {
//			 for(int i = 0; i < this.exercisesList.size(); i++) {
//				 if(this.exercisesList.get(i).getId().equals(exerciseId))
//					 this.exercisesList.remove(i);
//		    }   
//		}		
}
