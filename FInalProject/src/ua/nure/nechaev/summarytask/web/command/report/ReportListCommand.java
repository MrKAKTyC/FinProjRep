package ua.nure.nechaev.summarytask.web.command.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ReportDAO;
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class ReportListCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		ReportDAO reportDAO = new ReportDAO();
		List<Report> reports = reportDAO.getAllPending();
		request.setAttribute("reports", reports);
		return new GetRequest(Path.PAGE_LIST_REPORTS);
	}

}
