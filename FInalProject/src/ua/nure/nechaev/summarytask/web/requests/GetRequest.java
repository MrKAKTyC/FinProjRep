package ua.nure.nechaev.summarytask.web.requests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.exception.AppException;

public class GetRequest extends Request {

	public GetRequest(String path) {
		super(path);
	}

	@Override
	public void resend(HttpServletRequest request, HttpServletResponse response) throws AppException {
		try {
			request.getRequestDispatcher(getPath()).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			throw new AppException("error in resending request", e);
		}
	}

}
