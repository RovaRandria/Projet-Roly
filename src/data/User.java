package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "pseudo", length = 50, nullable = false)
	private String pseudo;
	
	@Column(name = "password", nullable = false)
	private String password;
		
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Profile.class)
	@JoinColumn(name = "profile_id", updatable = false)
	private Profile profile;
	
	public User() {
	}
	
	public User(String pseudo, String password) {
		this.pseudo = pseudo;
		this.password = password;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
