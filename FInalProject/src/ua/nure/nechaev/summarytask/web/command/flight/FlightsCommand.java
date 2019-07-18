package ua.nure.nechaev.summarytask.web.command.flight;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.bean.FlightBean;
import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.entity.Airport;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class FlightsCommand extends Command {
	private static final Logger LOG = Logger.getLogger(FlightsDAO.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "noAction";
		}
		FlightsDAO flightDAO = new FlightsDAO();
		List<FlightBean> flights = null;
		try {
			switch (action) {
			case "searchNumber":
				flights = new LinkedList<FlightBean>();
				int numberToFind = Integer.parseInt(request.getParameter("number"));
				LOG.trace("Searching by number " + numberToFind);
				flights.add(flightDAO.get(numberToFind));
				break;
			case "searchFull":
				String countryFrom = request.getParameter("CountryFrom");
				String cityFrom = request.getParameter("CityFrom");
				String countryTo = request.getParameter("CountryTo");
				String cityTo = request.getParameter("CityTo");
				String date = request.getParameter("date");
				Airport airportFrom = new Airport();
				airportFrom.setCity(cityFrom);
				airportFrom.setCountry(countryFrom);
				Airport airportTo = new Airport();
				airportTo.setCity(cityTo);
				airportTo.setCountry(countryTo);
				LOG.trace("Complex search");
				flights = flightDAO.Search(airportFrom, airportTo, date);
				break;
			case "sort":
				String fieldToSort = request.getParameter("field");
				LOG.trace("Do sorting by field "+fieldToSort);
				flights = flightDAO.getSorted(fieldToSort);
				break;
			default:
				flights = flightDAO.getAll();
				break;
			}
			request.setAttribute("flights", flights);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
			throw new AppException(e.getMessage(), e);
		}
		return new GetRequest(Path.INDEX_PAGE);
	}

}
