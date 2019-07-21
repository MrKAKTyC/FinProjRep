package ua.nure.nechaev.summarytask.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.db.DBManager;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.db.entity.Manager;
import ua.nure.nechaev.summarytask.exception.DBException;

public class ManagerDAO {
	private static final Logger LOG = Logger.getLogger(ManagerDAO.class);

	private static String SELECT_MANAGER_BY_LOGIN_AND_PASS = "SELECT * FROM managment JOIN accesslevels ON managerId=accessId WHERE login = ? AND password = ?";
	private static String SELECT_MANAGER_BY_ID = "SELECT * FROM managment JOIN accesslevels ON accesLevel=accessId WHERE managerId = ?";
	private static String SELECT_MANAGERS = "SELECT * FROM finaltask.managment INNER JOIN accesslevels ON (accesLevel = accessId)";
	private static String INSERT_MANAGER = "INSERT INTO managment (login, password, accesLevel) VALUES (?, ?, ?)";
	private static String DELETE_MANAGER = "DELETE FROM managment WHERE managerId = ?";
	private static String UPDATE_MANAGER = "UPDATE managment SET login = ?, accesLevel = ? WHERE managerId = ? ";

	public Manager getManager(String login, String password) throws DBException {
		Manager manager = null;
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_MANAGER_BY_LOGIN_AND_PASS)) {
				pstmt.setString(1, login);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					manager = new Manager();
					manager.setLogin(login);
					manager.setLevel(AccessLevel.valueOf(rs.getString("accessType")));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getSQLState(), e);
			throw new DBException(e.getMessage(), e);
		}
		return manager;
	}

	public Manager getManager(int managerId) throws DBException {
		Manager manager = null;
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_MANAGER_BY_ID)) {
				pstmt.setInt(1, managerId);
				LOG.trace("executing " + pstmt.toString());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					manager = new Manager();
					manager.setId(managerId);
					manager.setLogin(rs.getString("login"));
					manager.setLevel(AccessLevel.valueOf(rs.getString("accessType")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			throw new DBException(e.getMessage(), e);
		}
		return manager;
	}

	public List<Manager> getManagers() throws DBException {
		LinkedList<Manager> managers = new LinkedList<Manager>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery(SELECT_MANAGERS);
				while (rs.next()) {
					Manager manager = new Manager();
					manager.setId(rs.getInt("managerId"));
					manager.setLogin(rs.getString("login"));
					manager.setLevel(AccessLevel.valueOf(rs.getString("accessType")));
					managers.add(manager);
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getSQLState(), e);
			throw new DBException(e.getMessage(), e);
		}
		return managers;
	}

	public void addManager(Manager manager) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(INSERT_MANAGER)) {
				pstmt.setString(1, manager.getLogin());
				pstmt.setString(2, manager.getPassword());
				pstmt.setInt(3, manager.getLevel().getIntValue());
				pstmt.executeUpdate();
				con.commit();
				LOG.trace("New Manager inserted");
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new DBException(e.getMessage(), e);
		}
	}

	public void deleteManager(int managerId) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(DELETE_MANAGER)) {
				pstmt.setInt(1, managerId);
				pstmt.executeUpdate();
				con.commit();
				LOG.trace("manager with id " + managerId + " was deleted");
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new DBException(e.getMessage(), e);
		}

	}

	public void updateManager(int managerId, String login, int accessLevel) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(UPDATE_MANAGER)) {
				pstmt.setString(1, login);
				pstmt.setInt(2, accessLevel);
				pstmt.setInt(3, managerId);
				pstmt.executeUpdate();
				con.commit();
				LOG.trace("manager updated");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
