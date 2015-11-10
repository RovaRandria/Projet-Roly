package jdbc;

import java.util.ArrayList;

public class Profile {
	private int id;
	private String lastName;
	private String firstName;
	private String subcriptionDate;
	private String gender;
	private String birthday;
	private ArrayList<Profile> friends;
	private ArrayList<Sport> sports;
	private ArrayList<Practice> practices;
	private float weight;
	
	public Profile() {
		
	}
	
	public Profile(int id, String lastName, String firstName,
			String subcriptionDate, String gender, String birthday,
			ArrayList<Profile> friends, ArrayList<Sport> sports,
			ArrayList<Practice> practices, float weight) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.subcriptionDate = subcriptionDate;
		this.gender = gender;
		this.birthday = birthday;
		this.friends = friends;
		this.sports = sports;
		this.practices = practices;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getSubcriptionDate() {
		return subcriptionDate;
	}

	public void setSubcriptionDate(String subcriptionDate) {
		this.subcriptionDate = subcriptionDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
				+ firstName + ", subcriptionDate=" + subcriptionDate
				+ ", gender=" + gender + ", birthday=" + birthday
				+ ", friends=" + friends + ", sports=" + sports
				+ ", practices=" + practices + ", weight=" + weight + "]";
	}
	
	
	
	
}
