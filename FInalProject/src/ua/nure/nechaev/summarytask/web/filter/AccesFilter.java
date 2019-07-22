package ua.nure.nechaev.summarytask.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.db.entity.Manager;

/**
 * Filter class which filter requests and depending on requested page and user
 * role grant or deny access. Also set encoding of incoming and outcoming data
 * to UTF-8
 * 
 * @author Maks
 *
 */
public class AccesFilter implements Filter {

	private static final Logger LOG = Logger.getLogger(AccesFilter.class);

	// commands access
	private Map<AccessLevel, List<String>> accessMap = new HashMap<AccessLevel, List<String>>();
	private List<String> commons = new ArrayList<String>();
	private List<String> outOfControl = new ArrayList<String>();

	public void destroy() {
		LOG.debug("Filter destruction starts");
		LOG.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		LOG.debug("Filter starts");
		if (accessAllowed(request)) {
			String requestEncoding = request.getCharacterEncoding();
			LOG.debug("input enc ->" + requestEncoding);
			chain.doFilter(request, response);

		} else {
			String errorMessasge = "You do not have permission to access the requested resource";

			request.setAttribute("errorMessage", errorMessasge);
			LOG.trace("Set the request attribute: errorMessage --> " + errorMessasge);

			((HttpServletResponse) response).sendRedirect(Path.PAGE_LOGIN);
		}
		LOG.debug("Filter finished");
	}

	private boolean accessAllowed(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String commandName = request.getParameter("command");
		LOG.trace("received params: ");
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			LOG.trace(entry.getKey() + " --> " + Arrays.toString(entry.getValue()));
		}

		if (commandName == null || commandName.isEmpty()) {
			return false;
		}

		if (outOfControl.contains(commandName)) {
			return true;
		}

		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			return false;
		}
		Manager worker = (Manager) session.getAttribute("user");
		if (worker == null) {
			return false;
		}

		return accessMap.get(worker.getLevel()).contains(commandName) || commons.contains(commandName);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter initialization starts");

		// roles
		accessMap.put(AccessLevel.Administrator, asList(fConfig.getInitParameter("Administrator")));
		accessMap.put(AccessLevel.Dispatcher, asList(fConfig.getInitParameter("Dispatcher")));
		LOG.trace("Access map --> " + accessMap);

		// out of control
		outOfControl = asList(fConfig.getInitParameter("out-of-control"));
		LOG.trace("Out of control commands --> " + outOfControl);

		LOG.debug("Filter initialization finished");
	}

	/**
	 * Extracts parameter values from string.
	 * 
	 * @param str parameter values string.
	 * @return list of parameter values.
	 */
	private List<String> asList(String str) {
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

}
