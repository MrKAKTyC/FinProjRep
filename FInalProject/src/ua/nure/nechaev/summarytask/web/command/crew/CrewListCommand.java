package ua.nure.nechaev.summarytask.web.command.crew;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.bean.CrewBean;
import ua.nure.nechaev.summarytask.db.bean.WorkerBean;
import ua.nure.nechaev.summarytask.db.dao.CrewDAO;
import ua.nure.nechaev.summarytask.db.dao.WorkerDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class CrewListCommand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int flightId = Integer.parseInt(request.getParameter("id"));
		CrewDAO crewDAO = new CrewDAO();
		WorkerDAO workerDAO = new WorkerDAO();
		try {
		List<CrewBean> assigned = crewDAO.getAssigned(flightId);
		List<WorkerBean> avaible = workerDAO.getAvaible(flightId);
		request.setAttribute("assigned", assigned);
		request.setAttribute("available", avaible);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new GetRequest(Path.PAGE_EDIT_CREW);
	}

}
