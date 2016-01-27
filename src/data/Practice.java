package data;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "practice")
public class Practice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "performance")
	private String performance;
	
	@Column(name = "duration")
	private float duration;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Sport.class)
    @JoinColumn(name="sport_id", nullable = false)
	private Sport sport;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Profile.class)
	@JoinColumn(name = "profile_id")
	private Profile profile;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Exercise.class)
	@JoinTable(name = "practice_exercise", joinColumns = @JoinColumn(name = "practice_id"), inverseJoinColumns = @JoinColumn(name = "exercise_id"))
	private List<Exercise> exercisesList = new ArrayList<Exercise>();
	
	public Practice() {
		
	}

	public Practice(Sport sport, Date date, String place,
			float duration, String performance) {
		this.sport = sport;
		this.date = date;
		this.place = place;
		this.duration = duration;
		this.performance=performance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}	
	
	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public List<Exercise> getExercisesList() {
		return exercisesList;
	}

	public void setExercisesList(List<Exercise> exercisesList) {
		this.exercisesList = exercisesList;
	}

	public void setExercises(List<Exercise> exercisesList) {
		this.exercisesList = exercisesList;
	}

	public List<Exercise> getExercises() {
		return exercisesList;
	}
	public String displayExercise() {
		String exercisesStr = null;
		for(int i = 0; i< this.exercisesList.size(); i++) {
			exercisesStr += exercisesList.get(i).toString()+ " ";
		}
		return exercisesStr;
	}
	
	@Override
	public String toString() {
		return "Practice [id=" + id + ", date=" + date
				+ ", place=" + place + ", duration=" + duration + "exercices" + displayExercise() + "]";
	}
	
	/*public void addExercice(int exerciseId, String name, String description, ArrayList<AreaOfEffect> aoe) {
		Exercise exercise = new Exercise(name, description, aoe);
			 this.exercisesList.add(exercise);
	}
	
	public void updateExercice(int exerciseId, String name, String description,ArrayList<AreaOfEffect> aoe) {
		for(int i = 0; i < this.exercisesList.size(); i++) {
			 if(this.exercisesList.get(i).getId().equals(exerciseId)) {
				 this.exercisesList.get(i).setName(name);
				 this.exercisesList.get(i).setDescription(description);
				 this.exercisesList.get(i).setAreasOfEffect(aoe);
			 }
		}
	}
	
	public void removeExercice(int exerciseId) {
		 for(int i = 0; i < this.exercisesList.size(); i++) {
			 if(this.exercisesList.get(i).getId().equals(exerciseId))
				 this.exercisesList.remove(i);
	    }   
	}*/

	public void removeAllExercises() {
		this.exercisesList.removeAll(exercisesList);
	}	

}
