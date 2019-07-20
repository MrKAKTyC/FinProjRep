package ua.nure.nechaev.summarytask.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.AccessLevelDAO;
import ua.nure.nechaev.summarytask.db.dao.ManagerDAO;
import ua.nure.nechaev.summarytask.db.entity.Manager;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.exception.DBException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class ManagerEditCommand extends Command {

	private static final Logger LOG = Logger.getLogger(ManagerEditCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int managerId;
		try {
			managerId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			return new GetRequest(Path.PAGE_ERROR_PAGE);
		}
		LOG.trace("Searching manager with id "+managerId);
		Manager manager;
		try {
			ManagerDAO managerDAO = new ManagerDAO();
			manager = managerDAO.getManager(managerId);
		} catch (DBException e) {
			LOG.error(e);
			return new GetRequest(Path.PAGE_ERROR_PAGE);
		}
		LOG.trace("returning manager "+ manager.toString());
		request.setAttribute("manager", manager);
		AccessLevelDAO accessDAO = new AccessLevelDAO();
		request.setAttribute("levelsList", accessDAO.getAccessLevels());
		return new GetRequest(Path.PAGE_EDIT_MANAGER);
	}

}
