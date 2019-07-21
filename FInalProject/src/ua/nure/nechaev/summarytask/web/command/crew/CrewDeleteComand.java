package ua.nure.nechaev.summarytask.web.command.crew;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.db.dao.CrewDAO;
import ua.nure.nechaev.summarytask.db.entity.FlightCrew;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class CrewDeleteComand extends Command {
	
	private static final Logger LOG = Logger.getLogger(CrewDeleteComand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String referer = request.getHeader("referer");
		int crewId = Integer.parseInt(request.getParameter("id"));
		CrewDAO crewDAO = new CrewDAO();
		FlightCrew crewMember = new FlightCrew();
		crewMember.setCrewId(crewId);
		LOG.trace("removing crew member "+crewId);
		crewDAO.removeCrewMember(crewMember );
		return new PostRequest(referer);
	}

}
