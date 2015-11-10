package jdbc;

public class Practice {
	private int id;
	private Sport sport;
	private String date;
	private String place;
	private float duration;
	
	public Practice() {
		
	}
	
	public Practice(int id, Sport sport, String date, String place,
			float duration) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
