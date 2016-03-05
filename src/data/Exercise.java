package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "area_of_effect", nullable = true)
	@Enumerated(EnumType.ORDINAL)
	//private ArrayList<AreaOfEffect> areasOfEffect = new ArrayList<AreaOfEffect>();
	private AreaOfEffect areaOfEffect;
	
	@Column(name = "number_of_series")
	private int numberOfSeries;
	
	@Column(name = "description", length = 300, nullable = true)
	private String description;
		
	public Exercise() {
		
	}
	
	public Exercise(String name, int numberOfSeries) {
		super();
		this.name = name;
		this.numberOfSeries = numberOfSeries;
	}
	
	public Exercise(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Exercise(String name, String description, AreaOfEffect areaOfEffect) {
		super();
		this.name = name;
		this.areaOfEffect = areaOfEffect;
		this.description = description;
	}

	public Exercise(String name, String description, AreaOfEffect areaOfEffect, int numberOfSeries) {
	this.name = name;
	this.areaOfEffect = areaOfEffect;
	this.description = description;
	this.numberOfSeries = numberOfSeries;
	}
	
	/*public Exercise(String name, String description, ArrayList<AreaOfEffect> areasOfEffect) {
		this.name = name;
		this.areasOfEffect = areasOfEffect;
		this.description = description;
	}*/

	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public AreaOfEffect getAreaOfEffect() {
		return areaOfEffect;
	}

	public void setAreaOfEffect(AreaOfEffect areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumberOfSeries() {
		return numberOfSeries;
	}

	public void setNumberOfSeries(int numberOfSeries) {
		this.numberOfSeries = numberOfSeries;
	}

	@Override
	public String toString() {
		return numberOfSeries + " série(s) de " + name;
	}

	
	
}
