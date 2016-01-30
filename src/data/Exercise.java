package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exercise")
public class Exercise {
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;*/
	
	@Id
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "area_of_effect")
	@Enumerated(EnumType.ORDINAL)
	//private ArrayList<AreaOfEffect> areasOfEffect = new ArrayList<AreaOfEffect>();
	private AreaOfEffect areaOfEffect;

	
	@Column(name = "description", length = 300, nullable = false)
	private String description;
		
	public Exercise() {
		
	}	
	
	public Exercise(String name, String description, AreaOfEffect areaOfEffect) {
	this.name = name;
	this.areaOfEffect = areaOfEffect;
	this.description = description;
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

	@Override
	public String toString() {
		return "Exercise [name=" + name + ", areaOfEffect="
				+ areaOfEffect + ", description=" + description + "]";
	}
	
	
}
