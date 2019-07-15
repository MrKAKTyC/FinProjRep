package ua.nure.nechaev.summarytask.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class AdminMenuCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		return new GetRequest(Path.PAGE_ADMIN_MENU);
	}

}
