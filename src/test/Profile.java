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
@Table(name = "profile")
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
	
	private ArrayList<PhysicData> physicData;
	
	@Column(name = "sports")
	private ArrayList<Sport> sports;
	
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
		
	public ArrayList<Sport> getSports() {
		return sports;
	}

	public void setSports(ArrayList<Sport> sports) {
		this.sports = sports;
	}
	
	public String displaySport() {
		String sportsStr = null;
		for(int i = 0; i< this.sports.size(); i++) {
			sportsStr += sports.get(i).toString()+ " ";
		}
		return sportsStr;
	}	
	
	public ArrayList<PhysicData> getPhysicData() {
		return physicData;
	}

	public void setPhysicData(ArrayList<PhysicData> physicData) {
		this.physicData = physicData;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", registrationDate=" + registrationDate
				+ ", gender=" + gender + ", birthdate=" + birthdate
				+ "sports=" + displaySport() + "]";
	}
	
}
