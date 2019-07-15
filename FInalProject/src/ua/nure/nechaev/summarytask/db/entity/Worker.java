package ua.nure.nechaev.summarytask.db.entity;

public class Worker {

	private int id;
	private String name;
	private int spec;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSpec() {
		return spec;
	}

	public void setSpec(int spec) {
		this.spec = spec;
	}

//	public void setSpec(int sprcVal) {
//		this.spec = WorkerSpecialization.getSpec(sprcVal);
//		
//	}
	
	@Override
	public String toString() {
		return id + " " + name + " " +spec;
	}

}
