package jdbc;

public abstract class Performance {
	private int id;
	private String result;

	public Performance() {
		
	}
	public String getResult() {
		return result;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
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
