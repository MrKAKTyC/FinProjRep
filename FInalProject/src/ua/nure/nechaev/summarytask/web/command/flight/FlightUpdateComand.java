package ua.nure.nechaev.summarytask.web.command.flight;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.entity.Flight;
import ua.nure.nechaev.summarytask.db.entity.FlightStatus;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class FlightUpdateComand extends Command {
	private static final Logger LOG = Logger.getLogger(FlightUpdateComand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int flightNumb = Integer.parseInt(request.getParameter("id"));
		int fromId = Integer.parseInt(request.getParameter("dept_air"));
		int toId = Integer.parseInt(request.getParameter("ariv_air"));
		FlightStatus status = FlightStatus.getStatus(Integer.parseInt(request.getParameter("status")));
		String time = request.getParameter("time");
		String date = request.getParameter("date");
		String name = request.getParameter("name");
		Flight flight = new Flight();
		flight.setNumber(flightNumb);
		flight.setFromId(fromId);
		flight.setToId(toId);
		flight.setDepatureDate(date + " " + time);
		flight.setFlightName(name);
		flight.setStatus(status);
		FlightsDAO flightDAO = new FlightsDAO();
		LOG.trace("updating flight " + flight);
		flightDAO.update(flight);
		return new PostRequest(Path.SHOW_FLIGHTS_LIST);
	}

}
