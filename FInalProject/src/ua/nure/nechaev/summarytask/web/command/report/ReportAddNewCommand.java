package ua.nure.nechaev.summarytask.web.command.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ReportDAO;
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class ReportAddNewCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int flightId = Integer.parseInt(request.getParameter("flightID"));
		String reportReason = request.getParameter("reason");
		Report report = new Report();
		report.setFlightId(flightId);
		report.setDescription(reportReason);
		ReportDAO reportDAO = new ReportDAO();
		reportDAO.insert(report);
		return new PostRequest(Path.SHOW_FLIGHTS_LIST);
	}

}
