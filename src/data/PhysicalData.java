package data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "physical_data")
public class PhysicalData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "measure_date", nullable = false)
	private Date measureDate;	
	
	@Column(name = "weight")
	private float weight;

	@Column(name = "waist_size")
	private float waistSize;
	
	@Column(name = "hip_size")
	private float hipSize;
	
	public PhysicalData(){}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getMeasureDate() {
		return measureDate;
	}

	public void setMeasureDate(Date measureDate) {
		this.measureDate = measureDate;
	}

	public String convertMonth (int month){
		String[] monthName = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" };
	    return monthName[month-1];
	}
	
	@Override
	public String toString() {
		return "PhysicData [measureDate=" + measureDate + ", weight=" + weight
				+ ", waistSize=" + waistSize + ", hipSize=" + hipSize + "]";
	}
	
}


