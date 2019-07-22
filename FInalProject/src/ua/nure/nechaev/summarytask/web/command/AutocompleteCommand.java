package ua.nure.nechaev.summarytask.web.command;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.db.dao.AirportDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.util.JsonUtil;
import ua.nure.nechaev.summarytask.web.requests.Request;


/**
 * Class command for responding to auto complete requests
 * @author Maks
 *
 */
public class AutocompleteCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		// parameter for indicating database column
		String field = request.getParameter("field");
		// parameter for find and complite
		String adding = request.getParameter("term"); 
		AirportDAO airportsDAO = new AirportDAO();
		List<String> places = new LinkedList<String>();
		switch (field) {
		case "country":
			places = airportsDAO.getAllCountry(adding);
			break;
		case "city":
			places = airportsDAO.getAllCities(adding);
			break;
		case "airport":
			places = airportsDAO.getAllAirports(adding);
			break;
		}
		response.setContentType("application/json");
		response.getWriter().append(JsonUtil.toJSON(places));
		return null;
	}

}
