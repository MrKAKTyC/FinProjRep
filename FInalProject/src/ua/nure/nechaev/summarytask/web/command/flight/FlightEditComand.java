package ua.nure.nechaev.summarytask.web.command.flight;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.bean.FlightBean;
import ua.nure.nechaev.summarytask.db.dao.AirportDAO;
import ua.nure.nechaev.summarytask.db.dao.FlightStatusDAO;
import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of deleting flight
 * 
 * @author Maks
 *
 */
public class FlightEditComand extends Command {
	private static final Logger LOG = Logger.getLogger(FlightEditComand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		int flightNumb = 0;
		try {
			flightNumb = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			LOG.error("Wrong param", e);
			throw new AppException("Wrong param", e);
		}
		FlightsDAO flightsDAO = new FlightsDAO();
		FlightStatusDAO statusesDAO = new FlightStatusDAO();
		AirportDAO airportsDAO = new AirportDAO();
		FlightBean flight = flightsDAO.get(flightNumb);
		request.setAttribute("flightBean", flight);
		request.setAttribute("flight", flight);
		request.setAttribute("airports", airportsDAO.getAll());
		request.setAttribute("statuses", statusesDAO.getAll());
		return new GetRequest(Path.Page_EDIT_FLIGHT);
	}

}
