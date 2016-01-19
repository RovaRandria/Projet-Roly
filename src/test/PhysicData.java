package test;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "physic_data")
public class PhysicData {
	
	private Date mesureDate;
	
	private float weight;
	
	private float waistSize;
	
	private float hipSize;
	
	public PhysicData(){}

	public Date getMesureDate() {
		return mesureDate;
	}

	public void setMesureDate(Date mesureDate) {
		this.mesureDate = mesureDate;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getWaistSize() {
		return waistSize;
	}

	public void setWaistSize(float waistSize) {
		this.waistSize = waistSize;
	}

	public float getHipSize() {
		return hipSize;
	}

	public void setHipSize(float hipSize) {
		this.hipSize = hipSize;
	}

	@Override
	public String toString() {
		return "PhysicData [mesureDate=" + mesureDate + ", weight=" + weight
				+ ", waistSize=" + waistSize + ", hipSize=" + hipSize + "]";
	}
	
}


