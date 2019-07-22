package ua.nure.nechaev.summarytask.web.command.worker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.WorkerDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of deleting worker
 * 
 * @author Maks
 *
 */
public class WorkerDeleteComand extends Command {
	private static final Logger LOG = Logger.getLogger(WorkerDeleteComand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int workerId;
		try {
			workerId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			throw new AppException("Illegal parametr value");
		}
		WorkerDAO workerDAO = new WorkerDAO();
		LOG.trace("deleting worker with id " + workerId);
		workerDAO.delete(workerId);
		return new PostRequest(Path.SHOW_WORKERS_LIST);
	}

}
