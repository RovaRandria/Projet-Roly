package test;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class Performance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@Column(name = "result", nullable = false)
	private String result;

	public Performance() {
		
	}
	
	public Performance(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public String toString() {
		return "Performance [id=" + id + ", result=" + result + "]";
	}
	
	
}
