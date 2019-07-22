package ua.nure.nechaev.summarytask.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.command.CommandContainer;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

/**
 * Servlet implementation class FronController
 * receive all request, and execute corresponding command class
 */
@WebServlet(urlPatterns = "/Controller", name = "FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FrontController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		LOG.debug("Controller starts");
		LOG.info("session lang " + request.getSession().getAttribute("locale"));
		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		CommandContainer commandContainer = (CommandContainer) getServletContext().getAttribute("commands");
		Command command = commandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		Request forward = new GetRequest(Path.SHOW_INDEX);
		try {
			forward = command.execute(request, response);
		} catch (Exception ex) {
			if(request.getSession().getAttribute("user") == null) {
				// redirect guests to index page
				forward = new PostRequest(Path.SHOW_INDEX);
			} else {
				// redirect managers to flughts list
				forward = new PostRequest(Path.SHOW_FLIGHTS_LIST);
			}
		}
		if (forward != null) {
			LOG.trace("Forward address --> " + forward.getPath());
			try {
				forward.resend(request, response);
			} catch (AppException e) {
				LOG.error(e.getMessage(), e);
			}
		} 
	}

}
