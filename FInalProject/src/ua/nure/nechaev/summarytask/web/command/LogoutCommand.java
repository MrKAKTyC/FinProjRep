package ua.nure.nechaev.summarytask.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.flight.FlightsCommand;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class LogoutCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		request.getSession().removeAttribute("user");
		return new FlightsCommand().execute(request, response);
	}

}
