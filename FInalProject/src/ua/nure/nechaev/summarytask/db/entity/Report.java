package ua.nure.nechaev.summarytask.db.entity;

public class Report {

	private int id;
	private int flightId;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
