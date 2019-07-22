package ua.nure.nechaev.summarytask.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ManagerDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.GetRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;
/**
 * Command class for serving request of showing all available managers
 * 
 * @author Maks
 *
 */
public class ManagerListCommand extends Command{

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		ManagerDAO managerDAO = new ManagerDAO();
		request.setAttribute("managers", managerDAO.getManagers());
		return new GetRequest(Path.PAGE_LIST_MANAGERS);
	}
	
}
