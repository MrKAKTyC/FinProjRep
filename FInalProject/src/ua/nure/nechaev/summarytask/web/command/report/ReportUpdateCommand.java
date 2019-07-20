package ua.nure.nechaev.summarytask.web.command.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ReportDAO;
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.db.entity.ReportStatus;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class ReportUpdateCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		int status = Integer.parseInt(request.getParameter("status"));
		ReportDAO reportDAO = new ReportDAO();
		Report report = new Report();
		report.setId(reportId);
		if (status == 1) {
			report.setStatus(ReportStatus.completed.getIntVal());
		} else if (status == 0) {
			report.setStatus(ReportStatus.rejected.getIntVal());
		}
		reportDAO.updateStatus(report);
		return new PostRequest(Path.SHOW_REPORTS_LIST);
	}

}
