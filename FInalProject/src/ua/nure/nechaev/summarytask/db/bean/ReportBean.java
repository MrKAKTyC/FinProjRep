package ua.nure.nechaev.summarytask.db.bean;

import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.db.entity.ReportStatus;
import ua.nure.nechaev.summarytask.exception.DBException;

public class ReportBean {
	private int id;
	private FlightBean flight;
	private String description;
	private ReportStatus status;
	
	public static ReportBean getInstance(Report report) {
		ReportBean reportBean = new ReportBean();
		reportBean.id = report.getId();
		FlightsDAO flightDAO = new FlightsDAO();
		try {
			reportBean.flight = FlightBean.getInstance(flightDAO.get(report.getFlightId()));
		} catch (DBException e) {
			e.printStackTrace();
		}
		reportBean.description = report.getDescription();
		reportBean.status = ReportStatus.getStatus(report.getStatus());
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FlightBean getFlight() {
		return flight;
	}

	public void setFlight(FlightBean flight) {
		this.flight = flight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReportStatus getStatus() {
		return status;
	}

	public void setStatus(ReportStatus status) {
		this.status = status;
	}

}
