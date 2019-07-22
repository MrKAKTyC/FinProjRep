package ua.nure.nechaev.summarytask.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ManagerDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Command class for serving request of redirecting for manager edit
 * 
 * @author Maks
 *
 */
public class ManagerUpdateComand extends Command {

	private static final Logger LOG = Logger.getLogger(ManagerUpdateComand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int managerId = 0;
		int accessLevel = 0;
		try {
			managerId = Integer.parseInt(request.getParameter("id"));
			accessLevel = Integer.parseInt(request.getParameter("level"));
		} catch (NumberFormatException e) {
			LOG.error("Illegal parameter", e);
			throw new AppException("Illegal parameter value", e);
		}
		String login = request.getParameter("login");
		if (managerId < 0 || accessLevel < 0 || login == null || login.isEmpty()) {
			throw new AppException("Illegal parameter value");
		}
		LOG.trace("Update manager with id " + managerId + " seting login: " + login + " and AL " + accessLevel);
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.updateManager(managerId, login, accessLevel);
		return new PostRequest(Path.SHOW_MANAGERS_LIST);
	}

}
