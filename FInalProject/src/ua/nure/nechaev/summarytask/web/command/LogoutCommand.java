package ua.nure.nechaev.summarytask.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;
/**
 * Class for responding to logout request
 * reset session attribute "user" to null
 * @author Maks
 *
 */
public class LogoutCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		request.getSession().removeAttribute("user");
		return new GetRequest(Path.SHOW_INDEX);
	}

}
