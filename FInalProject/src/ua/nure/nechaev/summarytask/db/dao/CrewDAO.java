package ua.nure.nechaev.summarytask.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.db.DBManager;
import ua.nure.nechaev.summarytask.db.bean.CrewBean;
import ua.nure.nechaev.summarytask.db.entity.FlightCrew;
import ua.nure.nechaev.summarytask.exception.DBException;

public class CrewDAO {

	private static final Logger LOG = Logger.getLogger(CrewDAO.class);

	private static String SELECT_ALL_FOR_FLIGHT = "SELECT * FROM flightcrew WHERE flightID = ?";
	private static String INSERT = "INSERT INTO flightcrew (flightID, workerID) VALUES (?, ?)";
	private static String REMOVE = "DELETE FROM flightcrew WHERE flightcrew.crewID = ?";

	public List<CrewBean> getAssigned(int flightId) throws DBException {
		List<CrewBean> crewMembers = new LinkedList<CrewBean>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_FOR_FLIGHT)) {
				pstmt.setInt(1, flightId);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					FlightCrew crew = new FlightCrew();
					crew.setCrewId(rs.getInt("crewID"));
					crew.setFlightId(rs.getInt("flightID"));
					crew.setWorkerId(rs.getInt("workerID"));
					crewMembers.add(CrewBean.getInstance(crew));
				}
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException(e.getMessage(), e);
		}
		return crewMembers;
	}

	public void addCrewMember(FlightCrew crewMember) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(INSERT)) {
				pstmt.setInt(1, crewMember.getFlightId());
				pstmt.setInt(2, crewMember.getWorkerId());
				pstmt.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException(e.getMessage(), e);
		}
	}

	public void removeCrewMember(FlightCrew crewMember) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(REMOVE)) {
				pstmt.setInt(1, crewMember.getCrewId());
				pstmt.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException(e.getMessage(), e);
		}
	}

}
