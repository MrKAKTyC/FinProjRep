package ua.nure.nechaev.summarytask.web.requests;

public abstract class Request {
	private String path;
	
	public Request(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
