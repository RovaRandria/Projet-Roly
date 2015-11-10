package jdbc;

public class Exercice {
	private int id;
	private String name;
	private String areaOfEffect;
	private String description;
	
	public Exercice() {
		
	}
	
	public Exercice(int id, String name, String areaOfEffect, String description) {
		this.id = id;
		this.name = name;
		this.areaOfEffect = areaOfEffect;
		this.description = description;
	}

	
	public String getName() {
		return name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAreaOfEffect() {
		return areaOfEffect;
	}


	public void setAreaOfEffect(String areaOfEffect) {
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
		return "Exercice [id=" + id + ", name=" + name + ", areaOfEffect="
				+ areaOfEffect + ", description=" + description + "]";
	}
	
	
}
