package ua.nure.nechaev.summarytask.web.command.worker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.WorkerDAO;
import ua.nure.nechaev.summarytask.db.entity.Worker;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of updating worker data
 * 
 * @author Maks
 *
 */
public class WorkerUpdateComand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int workerId;
		String workerName;
		int workerSpec;
		try {
			workerId = Integer.parseInt(request.getParameter("id"));
			workerSpec = Integer.parseInt(request.getParameter("spec"));
		} catch (NumberFormatException e) {
			throw new AppException("Illegal parameter value", e);
		}
		workerName = request.getParameter("name");
		if (workerName == null || workerName.isEmpty()) {
			throw new AppException("Illegal parameter value");
		}
		Worker worker = new Worker();
		worker.setId(workerId);
		worker.setName(workerName);
		worker.setSpec(workerSpec);
		WorkerDAO workerDAO = new WorkerDAO();
		workerDAO.update(worker);
		return new PostRequest(Path.SHOW_WORKERS_LIST);
	}

}
