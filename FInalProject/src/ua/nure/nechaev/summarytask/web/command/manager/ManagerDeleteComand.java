package ua.nure.nechaev.summarytask.web.command.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.Path;
import ua.nure.nechaev.summarytask.db.dao.ManagerDAO;
import ua.nure.nechaev.summarytask.exception.AppException;
import ua.nure.nechaev.summarytask.web.command.Command;
import ua.nure.nechaev.summarytask.web.requests.PostRequest;
import ua.nure.nechaev.summarytask.web.requests.Request;

public class ManagerDeleteComand extends Command {

	@Override
	public Request execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		int managerId = Integer.parseInt(request.getParameter("id"));
		ManagerDAO managerDAO = new ManagerDAO();
		managerDAO.deleteManager(managerId);
		return new PostRequest(Path.SHOW_ADMIN_MENU);
	}

}