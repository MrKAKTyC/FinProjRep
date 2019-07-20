package ua.nure.nechaev.summarytask.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ManagerDAO;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.db.entity.Manager;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.exception.DBException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class ManagerAddNewCommand extends Command {

	private static final Logger LOG = Logger.getLogger(ManagerAddNewCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		int intLevel;
		try {
			intLevel = Integer.parseInt(request.getParameter("level"));
		} catch (NumberFormatException e) {
			return new GetRequest(Path.PAGE_ERROR_PAGE);
		}
		if (login == null || login.isEmpty() || password == null || password.isEmpty() || intLevel < 0) {
			return new GetRequest(Path.PAGE_ERROR_PAGE);
		}
		Manager manager = new Manager();
		manager.setLogin(login);
		manager.setPassword(password);
		AccessLevel level = null;
		if (intLevel == AccessLevel.Administrator.getIntValue()) {
			level = AccessLevel.Administrator;
		} else if (intLevel == AccessLevel.Dispatcher.getIntValue()) {
			level = AccessLevel.Dispatcher;
		}
		manager.setLevel(level);
		LOG.info("Next manager will be added " + manager.toString());
		try {
			ManagerDAO managerDAO = new ManagerDAO();
			managerDAO.addManager(manager);
		} catch (DBException e) {
			LOG.error(e.getMessage(), e);
		}
		return new PostRequest(Path.SHOW_MANAGERS_LIST);
	}

}
