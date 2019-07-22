package ua.nure.nechaev.summarytask.web.command.worker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.WorkerDAO;
import ua.nure.nechaev.summarytask.db.dao.WorkerSpecializationDAO;
import ua.nure.nechaev.summarytask.db.entity.Worker;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of obtaining worker data and redirect on
 * edit page
 * 
 * @author Maks
 *
 */
public class WorkerEditComand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int workerId = 0;
		try {
			workerId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			throw new AppException("illegal parameter value", e);
		}
		WorkerDAO workerDAO = new WorkerDAO();
		Worker worker = workerDAO.get(workerId);
		if (worker == null) {
			throw new AppException("No such worker");
		} else {
			request.setAttribute("worker", worker);
			WorkerSpecializationDAO specDAO = new WorkerSpecializationDAO();
			request.setAttribute("specs", specDAO.getList());
		}
		return new GetRequest(Path.PAGE_EDIT_WORKER);
	}

}
