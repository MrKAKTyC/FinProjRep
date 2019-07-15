package ua.nure.nechaev.summarytask.web.command.crew;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.command.CommandContainer;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class CrewDeleteComand extends Command {
	
	private static final Logger LOG = Logger.getLogger(CrewDeleteComand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
