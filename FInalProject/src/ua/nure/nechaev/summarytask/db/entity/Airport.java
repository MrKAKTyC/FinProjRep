package ua.nure.nechaev.summarytask.db.entity;

public class Airport {
	private int Id;
	private String country;
	private String city;
	private String airportName;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

}
