package ua.nure.nechaev.summarytask.web.command.flight;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of deleting flight
 * 
 * @author Maks
 *
 */
public class FlightDeleteComand extends Command {
	private static final Logger LOG = Logger.getLogger(FlightAddNewCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		FlightsDAO flightDAO = new FlightsDAO();
		int flightNumb = 0;
		try {
			flightNumb = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			LOG.error("Wrong parameter value", e);
			throw new AppException("Illegal parameter", e);
		}
		LOG.trace("deleting flight with id:" + flightNumb);
		flightDAO.delete(flightNumb);
		return new PostRequest(Path.SHOW_FLIGHTS_LIST);
	}

}
