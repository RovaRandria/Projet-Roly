package data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Column(name = "first_name", length = 50)
	private String firstName;
	
	@Column(name = "registration_date")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = PhysicalData.class)
	@JoinColumn(name = "profil_id", nullable = false)
	private List<PhysicalData> physicalDataList = new ArrayList<PhysicalData>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Sport.class)
	@JoinTable(name = "profile_sport", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "sport_id"))
	private List<Sport> sportsList = new ArrayList<Sport>();
	
	@OneToMany(mappedBy="profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Practice.class)
	private List<Practice> practicesList = new ArrayList<Practice>();

	@OneToOne(mappedBy="profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class)
	private User user;
	
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
	
	public String displaySport() {
		String sportsStr = null;
		for(int i = 0; i< this.sportsList.size(); i++) {
			sportsStr += sportsList.get(i).toString()+ " ";
		}
		return sportsStr;
	}	

	public List<PhysicalData> getPhysicalDataList() {
		return physicalDataList;
	}
	public void setPhysicalDataList(ArrayList<PhysicalData> physicalDataList) {
		this.physicalDataList = physicalDataList;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", lastName=" + lastName + ", firstName="
				+ firstName + ", registrationDate=" + registrationDate
				+ ", gender=" + gender + ", birthdate=" + birthdate
				+ "sports=" + displaySport() + "]";
	}

	public List<Sport> getSportsList() {
		return sportsList;
	}

	public void setSportsList(List<Sport> sportsList) {
		this.sportsList = sportsList;
	}

	public List<Practice> getPracticesList() {
		return practicesList;
	}

	public void setPracticesList(List<Practice> practicesList) {
		this.practicesList = practicesList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPhysicalDataList(List<PhysicalData> physicalDataList) {
		this.physicalDataList = physicalDataList;
	}
}
