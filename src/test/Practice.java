package test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Practice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "sport", nullable = false)
	private Sport sport;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
	
	@Column(name = "place", nullable = false)
	private String place;
	
	@Column(name = "duration", nullable = false)
	private float duration;
	
	public Practice() {
		
	}
	
	public Practice(Sport sport, Date date, String place,
			float duration) {
		this.sport = sport;
		this.date = date;
		this.place = place;
		this.duration = duration;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
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

	@Override
	public String toString() {
		return "Practice [id=" + id + ", sport=" + sport + ", date=" + date
				+ ", place=" + place + ", duration=" + duration + "]";
	}
	
	
	
	
	

}
