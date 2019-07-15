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

public class WorkerUpdateComand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		WorkerDAO workerDAO = new WorkerDAO();
		int workerId = Integer.parseInt(request.getParameter("id"));
		String workerName = request.getParameter("name");
		int workerSpec = Integer.parseInt(request.getParameter("spec"));
		Worker worker = new Worker();
		worker.setId(workerId);
		worker.setName(workerName);
		worker.setSpec(workerSpec);
		workerDAO.update(worker);
		return new PostRequest(Path.SHOW_ADMIN_MENU);
	}

}