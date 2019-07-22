package ua.nure.nechaev.summarytask.web.command.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ReportDAO;
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.exception.DBException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of geting all reports
 * 
 * @author Maks
 *
 */
public class ReportListCommand extends Command {
	private static final Logger LOG = Logger.getLogger(ReportListCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		try {
			ReportDAO reportDAO = new ReportDAO();
			List<Report> reports = reportDAO.getAllPending();
			request.setAttribute("reports", reports);
		} catch (DBException e) {
			LOG.error("Problem with obtaining data from DB", e);
			throw new AppException("Problem with obtaining data from DB");
		}
		return new GetRequest(Path.PAGE_LIST_REPORTS);
	}

}
