package ua.nure.nechaev.summarytask.db.entity;

public class Manager {
	private int id;
	private String login;
	private String password;
	private AccessLevel level;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public AccessLevel getLevel() {
		return level;
	}

	public void setLevel(AccessLevel level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	 @Override
	public String toString() {
		return id + " " + login + " " + level.toString();
	}
	

}
