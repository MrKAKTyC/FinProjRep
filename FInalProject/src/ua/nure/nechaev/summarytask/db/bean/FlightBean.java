package ua.nure.nechaev.summarytask.db.bean;

import java.io.Serializable;
import java.util.Formatter;

import ua.nure.nechaev.summarytask.db.dao.AirportDAO;
import ua.nure.nechaev.summarytask.db.entity.Airport;
import ua.nure.nechaev.summarytask.db.entity.Flight;
import ua.nure.nechaev.summarytask.db.entity.FlightStatus;
import ua.nure.nechaev.summarytask.exception.DBException;

public class FlightBean implements Serializable {
	private static final long serialVersionUID = -2846204916949613697L;
	private int id;
	private Airport from;
	private Airport to;
	private String date;
	private String time;
	private String name;
	private FlightStatus status;

	public static FlightBean getInstance(Flight flight) {
		FlightBean flightBean = new FlightBean();
		flightBean.id = flight.getNumber();
		try {
			AirportDAO airportDAO = new AirportDAO();
			flightBean.from = airportDAO.get(flight.getFromId());
			flightBean.to = airportDAO.get(flight.getToId());
			String[] dateTime = flight.getDepatureDate().split(" ");
			flightBean.date = dateTime[0];
			flightBean.time = dateTime[1];
			flightBean.name = flight.getFlightName();
			flightBean.status = flight.getStatus();
		} catch (DBException e) {
			e.printStackTrace();
		}
		return flightBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Airport getFrom() {
		return from;
	}

	public void setFrom(Airport from) {
		this.from = from;
	}

	public Airport getTo() {
		return to;
	}

	public void setTo(Airport to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String result = null;
		String formatingString = "%d %s,%s(%s) %s%s(%s) %s %s %s %s";
		try(Formatter f = new Formatter()){
			f.format(formatingString, id, this.from.getCity(), this.from.getCountry(), this.from.getAirportName(), this.to.getCity(), this.to.getCountry(), this.to.getAirportName(), this.date, this.time, this.name, this.status);
			result = f.toString();
		}
		return result;
	}

}
