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

public class CrewAddNewCommand extends Command {

	private static final Logger LOG = Logger.getLogger(CrewAddNewCommand.class);

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String referer = request.getHeader("referer");
		try {
			int flightId = Integer.parseInt(request.getParameter("flightId"));
			int workerId = Integer.parseInt(request.getParameter("workerId"));
			CrewDAO crewDAO = new CrewDAO();
			FlightCrew crewMember = new FlightCrew();
			crewMember.setFlightId(flightId);
			crewMember.setWorkerId(workerId);
			LOG.trace("adding worker" + workerId + "to flight " + flightId);
			crewDAO.addCrewMember(crewMember);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return new PostRequest(referer);
	}

}
