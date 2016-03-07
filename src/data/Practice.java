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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Includes the sport practices'fields of a user
 * @author Rova
 *
 */
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
	private Float performance;
	
	@Column(name = "duration")
	private float duration;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Sport.class)
    @JoinColumn(name="sport_id", nullable = false)
	private Sport sport;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Exercise.class)
	@JoinColumn(name = "practice_id", nullable = true)
	private List<Exercise> exercisesList = new ArrayList<Exercise>();
	
	public Practice() {
		
	}

	/**
	 * builds a practice with a runtime performance (for Jogging and Cycling)
	 * @param sport
	 * @param date
	 * @param place
	 * @param duration
	 * @param performance
	 * @param profile
	 */
	public Practice(Sport sport, Date date, String place,
			float duration, Float performance, Profile profile) {
		this.sport = sport;
		this.date = date;
		this.place = place;
		this.duration = duration;
		this.performance = performance;
	}
	
	/**
	 * builds a practice with an exerciseList (for Ski, Bodybuilding and Climbing)
	 * @param sport
	 * @param date
	 * @param place
	 * @param duration
	 * @param exercisesList
	 * @param profile
	 */
	public Practice(Sport sport, Date date, String place,
			float duration, List<Exercise> exercisesList, Profile profile) {
		this.sport = sport;
		this.date = date;
		this.place = place;
		this.duration = duration;
		this.exercisesList = exercisesList;
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
	
	public Float getPerformance() {
		return performance;
	}

	public void setPerformance(Float performance) {
		this.performance = performance;
	}

	public List<Exercise> getExercisesList() {
		return exercisesList;
	}

	public void setExercisesList(List<Exercise> exercisesList) {
		this.exercisesList = exercisesList;
	}
	
	/**
	 * Displays the different exercises of a sport practice
	 * @return the method ToString for each exercise of a sport practice
	 */
	public String displayExercise() {
		String exercisesStr = "";
		for(int i = 0; i< this.exercisesList.size(); i++) {
			exercisesStr += exercisesList.get(i).toString()+ " ";
		}
		return exercisesStr;
	}
	
	@Override
	public String toString() {
		return "Le : " + date
				+ "à :" + place + " durée : " + duration + displayExercise();
	}

	/**
	 * Remove all exercises from a sport practice
	 */
	public void removeAllExercises() {
		this.exercisesList.removeAll(exercisesList);
	}	

}
