package ua.nure.nechaev.summarytask.web.command.flight;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.AirportDAO;
import ua.nure.nechaev.summarytask.db.dao.FlightStatusDAO;
import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.entity.Flight;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class FlightListCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		FlightsDAO flightDAO = new FlightsDAO();
		FlightStatusDAO statusesDAO = new FlightStatusDAO();
		AirportDAO airportsDAO = new AirportDAO();
		
		request.setAttribute("airports", airportsDAO.getAll());
		request.setAttribute("statuses", statusesDAO.getAll());
		List<Flight> flights = flightDAO.getAll();
		System.out.println(flights);
		request.setAttribute("flights", flights);
		return new GetRequest(Path.PAGE_LIST_FLIGHTS);
	}

}
