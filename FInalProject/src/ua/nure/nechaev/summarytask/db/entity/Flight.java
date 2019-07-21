package ua.nure.nechaev.summarytask.db.entity;

import java.util.Formatter;

public class Flight {
	private int number;
	private int fromId;
	private int toId;
	private String depatureDate;
	private String flightName;
	private FlightStatus status;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public String getDepatureDate() {
		return depatureDate;
	}

	public void setDepatureDate(String depatureDate) {
		this.depatureDate = depatureDate;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		try (Formatter f = new Formatter()) {
			f.format("%d %d %d %s %s %s", number, fromId, toId, depatureDate, flightName, status.toString());
			return f.toString();
		}
	}
}
