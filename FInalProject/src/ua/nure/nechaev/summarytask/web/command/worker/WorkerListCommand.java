package ua.nure.nechaev.summarytask.web.command.worker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.WorkerDAO;
import ua.nure.nechaev.summarytask.db.dao.WorkerSpecializationDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class WorkerListCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		WorkerDAO workerDAO = new WorkerDAO();
		WorkerSpecializationDAO specDAO = new WorkerSpecializationDAO();
		request.setAttribute("specs", specDAO.getList());
		request.setAttribute("workers", workerDAO.getAll());
		return new GetRequest(Path.PAGE_LIST_WORKERS);
	}

}
