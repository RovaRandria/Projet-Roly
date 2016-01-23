package test;

import java.util.ArrayList;
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
import javax.persistence.Table;

@Entity
@Table(name = "exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "description", length = 300)
	private String description;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "area_of_effect")
	private AreaOfEffect areaOfEffect;
	
	
	public Exercise() {
		
	}	
	
	public Exercise(String name, AreaOfEffect areaOfEffect,
		String description) {
		this.name = name;
		this.areaOfEffect = areaOfEffect;
		this.description = description;
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


	public AreaOfEffect getAreaOfEffect() {
		return areaOfEffect;
	}


	public void setAreaOfEffect(AreaOfEffect areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Exercise [id=" + id + ", name=" + name + ", areaOfEffect="
				+ areaOfEffect + ", description=" + description + "]";
	}
	
	
}
