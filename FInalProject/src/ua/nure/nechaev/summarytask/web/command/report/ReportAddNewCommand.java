package ua.nure.nechaev.summarytask.web.command.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.FlightsDAO;
import ua.nure.nechaev.summarytask.db.dao.ReportDAO;
import ua.nure.nechaev.summarytask.db.entity.Flight;
import ua.nure.nechaev.summarytask.db.entity.FlightStatus;
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of adding new report
 * 
 * @author Maks
 *
 */
public class ReportAddNewCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int flightId;
		String reportReason;
		try {
			flightId = Integer.parseInt(request.getParameter("flightID"));
			reportReason = request.getParameter("reason");
		} catch (NumberFormatException e) {
			throw new AppException("Illegal parameter value");
		}
		if (flightId < 0 || reportReason == null || reportReason.isEmpty()) {
			throw new AppException("Illegal parameter value");
		}
		FlightsDAO flightsDAO = new FlightsDAO();
		ReportDAO reportDAO = new ReportDAO();
		Report report = new Report();
		// Adds new report to base
		report.setFlightId(flightId);
		report.setDescription(reportReason);
		reportDAO.insert(report);
		// Update flight status to Pending
		Flight flight = new Flight();
		flight.setNumber(flightId);
		flight.setStatus(FlightStatus.Pending);
		flightsDAO.updateStatus(flight);
		return new PostRequest(Path.SHOW_FLIGHTS_LIST);
	}

}
