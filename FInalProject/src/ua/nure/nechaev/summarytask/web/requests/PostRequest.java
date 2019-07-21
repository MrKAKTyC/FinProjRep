package ua.nure.nechaev.summarytask.web.requests;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.exception.AppException;

public class PostRequest extends Request {

	public PostRequest(String path) {
		super(path);
	}

	@Override
	public void resend(HttpServletRequest request, HttpServletResponse response) throws AppException {
		try {
			response.sendRedirect(this.getPath());
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException("error in resending request", e);
		}

	}

}
