package ua.nure.nechaev.summarytask.web.command.flight;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.entity.Flight;
import ua.nure.nechaev.summarytask.db.entity.FlightStatus;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of adding new flight member
 * 
 * @author Maks
 *
 */
public class FlightAddNewCommand extends Command {

	private static final Logger LOG = Logger.getLogger(FlightAddNewCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int fromId = 0;
		int toId = 0;
		String date;
		String time;
		String name;
		FlightStatus status = null;
		try {
			fromId = Integer.parseInt(request.getParameter("dept_air"));
			toId = Integer.parseInt(request.getParameter("ariv_air"));
			status = FlightStatus.getStatus(Integer.parseInt(request.getParameter("status")));
		} catch (NumberFormatException e) {
			LOG.error("Problem with parsing parameter", e);
			throw new AppException();
		}
		date = request.getParameter("date");
		time = request.getParameter("time");
		name = request.getParameter("name");
		if (date == null || time == null || name == null) {
			throw new AppException();
		}
		String dateTime = date + " " + time;
		Flight flight = new Flight();
		flight.setFromId(fromId);
		flight.setToId(toId);
		flight.setDepatureDate(dateTime);
		flight.setFlightName(name);
		flight.setStatus(status);
		FlightsDAO flightDAO = new FlightsDAO();
		LOG.trace("adding new flight " + flight);
		long flightID = flightDAO.create(flight);
		return new PostRequest("./Controller?command=crewList&id=" + flightID);
	}

}
