package ua.nure.nechaev.summarytask.web.requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.nechaev.summarytask.exception.AppException;

public abstract class Request {
	private String path;
	
	public Request(String path) {
		this.path = path;
	}
	
	public abstract void resend(HttpServletRequest request, HttpServletResponse response) throws AppException;
	
	public String getPath() {
		return this.path;
	}
}
