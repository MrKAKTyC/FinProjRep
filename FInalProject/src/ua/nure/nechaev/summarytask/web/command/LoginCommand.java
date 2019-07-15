package ua.nure.nechaev.summarytask.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ManagerDAO;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.db.entity.Manager;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class LoginCommand extends Command {

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException("Login/password cannot be empty");
		}
		ManagerDAO managerDAO = new ManagerDAO();
		Manager manager = managerDAO.getManager(login, password);
		LOG.trace("Found in DB: user --> " + manager);

		if (manager == null) {
			throw new AppException("Cannot find user with such login/password");
		}

		AccessLevel userRole = manager.getLevel();
		LOG.trace("userRole --> " + userRole);

		Request forward = new GetRequest(Path.PAGE_ERROR_PAGE);

		if (userRole == AccessLevel.Administrator) {
			forward = new PostRequest(Path.SHOW_ADMIN_MENU);
		}

		if (userRole == AccessLevel.Dispatcher) {
			forward = new PostRequest(Path.SHOW_FLIGHTS_LIST);
		}

		session.setAttribute("user", manager);
		LOG.trace("Set the session attribute: user --> " + manager);

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		LOG.info("User " + manager + " logged as " + userRole.toString().toLowerCase());

		LOG.debug("Command finished");
		return forward;
	}

}