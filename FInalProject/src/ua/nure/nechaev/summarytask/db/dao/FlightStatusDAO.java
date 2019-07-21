package ua.nure.nechaev.summarytask.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.db.DBManager;
import ua.nure.nechaev.summarytask.db.entity.FlightStatus;
import ua.nure.nechaev.summarytask.exception.DBException;

public class FlightStatusDAO {
	private static final Logger LOG = Logger.getLogger(FlightStatusDAO.class);
	private static String SELECT_FLIGHTSTATUSES = "SELECT * FROM flightstatusen";

	public List<FlightStatus> getAll() throws DBException {
		List<FlightStatus> statuses = new LinkedList<FlightStatus>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery(SELECT_FLIGHTSTATUSES);
				while (rs.next()) {
					statuses.add(FlightStatus.valueOf(rs.getString("statusName")));
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new DBException(e.getMessage(), e);
		}
		return statuses;
	}
}
