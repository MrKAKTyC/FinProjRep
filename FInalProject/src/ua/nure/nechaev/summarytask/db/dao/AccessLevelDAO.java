package ua.nure.nechaev.summarytask.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.db.DBManager;
import ua.nure.nechaev.summarytask.db.entity.AccessLevel;
import ua.nure.nechaev.summarytask.exception.DBException;

public class AccessLevelDAO {
	private static final Logger LOG = Logger.getLogger(AccessLevelDAO.class);
	private static String SELECT_ACCESSLEVELS = "SELECT accessType FROM accesslevels";

	public List<AccessLevel> getAccessLevels() throws DBException {
		List<AccessLevel> levels = new LinkedList<AccessLevel>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery(SELECT_ACCESSLEVELS);
				while (rs.next()) {
					levels.add(AccessLevel.valueOf(rs.getString("accessType")));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new DBException(e.getMessage(), e);
		}
		return levels;
	}
}
