package ua.nure.nechaev.summarytask.db.bean;

import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.dao.WorkerDAO;
import ua.nure.nechaev.summarytask.db.entity.FlightCrew;
import ua.nure.nechaev.summarytask.exception.DBException;

public class CrewBean {
	private int id;
	private FlightBean flight;
	private WorkerBean worker;

	public static CrewBean getInstance(FlightCrew crew) {
		CrewBean crewBean = new CrewBean();
		crewBean.id = crew.getCrewId();
		try {
			crewBean.flight = FlightBean.getInstance(new FlightsDAO().get(crew.getFlightId()));
			crewBean.worker = WorkerBean.getInstance(new WorkerDAO().get(crew.getWorkerId()));
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return crewBean;
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

	public WorkerBean getWorker() {
		return worker;
	}

	public void setWorker(WorkerBean worker) {
		this.worker = worker;
	}
	
	

}
