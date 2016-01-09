package test;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_info")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "lastname", length = 50, nullable = false)
	private String lastName;
	
	@Column(name = "firstname", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@Column(name = "friends")
	private ArrayList<Profile> friends;
	
	@Column(name = "sports")
	private ArrayList<Sport> sports;
	
	@Column(name = "practices")
	private ArrayList<Practice> practices;
	
	@Column(name = "weight")
	private float weight;
	
	public Profile() {
		
	}
	
	public Profile(String lastName, String firstName,
			Date registrationDate, Gender gender, Date birthdate, float weight) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.registrationDate = registrationDate;
		this.gender = gender;
		this.birthdate = birthdate;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public ArrayList<Profile> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Profile> friends) {
		this.friends = friends;
	}

	public ArrayList<Sport> getSports() {
		return sports;
	}

	public void setSports(ArrayList<Sport> sports) {
		this.sports = sports;
	}

	public ArrayList<Practice> getPractices() {
		return practices;
	}

	public void setPractices(ArrayList<Practice> practices) {
		this.practices = practices;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", registrationDate=" + registrationDate
				+ ", gender=" + gender + ", birthdate=" + birthdate
				+ ", friends=" + friends + ", sports=" + sports
				+ ", practices=" + practices + ", weight=" + weight + "]";
	}
	
	public void addPractice(Practice practice) {
		this.practices.add(practice);
	}
	
	public void removeAllPractices() {
		this.practices.removeAll(practices);
	}
	
	public void removePractice(String practiceId) {
		 for(int i = 0; i < this.practices.size(); i++) {
			 if(this.practices.get(i).getId().equals(practiceId))
				 this.practices.remove(i);
	    }   
	}
	
	public void updatePractice(String practiceId, Sport sport, Date date, String place,
			float duration) {
		for(int i = 0; i < this.practices.size(); i++) {
			 if(this.practices.get(i).getId().equals(practiceId)) {
				 this.practices.get(i).setSport(sport);
				 this.practices.get(i).setDate(date);
				 this.practices.get(i).setPlace(place);
				 this.practices.get(i).setDuration(duration);
			 }
	    }
	}
	
	
}
