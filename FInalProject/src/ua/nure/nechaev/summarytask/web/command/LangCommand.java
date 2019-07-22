package ua.nure.nechaev.summarytask.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;
/**
 * Command class for serving language change
 * @author Maks
 *
 */
public class LangCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LangCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		String referer = request.getHeader("referer");
		LOG.info("prev locale "+ request.getSession().getAttribute("locale"));
		String newLocale = request.getParameter("locale");
		LOG.info("seting locale "+ newLocale);
		request.getSession().setAttribute("locale", newLocale);
		LOG.info("new locale "+ request.getSession().getAttribute("locale"));
		return new PostRequest(referer);
	}

}
