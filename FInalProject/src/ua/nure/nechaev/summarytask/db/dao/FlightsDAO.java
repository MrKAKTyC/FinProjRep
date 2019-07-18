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
import ua.nure.nechaev.summarytask.db.bean.FlightBean;
import ua.nure.nechaev.summarytask.db.entity.Airport;
import ua.nure.nechaev.summarytask.db.entity.Flight;
import ua.nure.nechaev.summarytask.db.entity.FlightStatus;
import ua.nure.nechaev.summarytask.exception.DBException;

public class FlightsDAO {
	private static final Logger LOG = Logger.getLogger(FlightsDAO.class);
	private static String SELECT_ALL_FLIGHTS = "SELECT * FROM flight";
	private static String SELECT_FLIGHT = "SELECT * FROM flight WHERE flightNumb = ?";
	private static String INSERT_FLIGHT = "INSERT INTO flight (fromId, toId, depatureDate, flightName, statusId) VALUES (?, ?, ?, ?, ?);";
	private static String UPDATE_FLIGHT = "UPDATE flight SET fromId = ?, toId = ?, depatureDate = ?, flightName = ?, statusId = ? WHERE flightNumb = ? ";
	private static String DELETE_FLIGHT = "DELETE FROM flight WHERE flightNumb = ?";
	// fromCity fromCountry toCity toCountry date
	private static String FIND_BY_DATE_AND_ENDPOINTS = "SELECT DISTINCT FROM_AP.* FROM (SELECT * FROM flight JOIN airports ON flight.fromId = airports.airportID) AS FROM_AP"
			+ " JOIN (SELECT * FROM flight JOIN airports ON flight.toId = airports.airportID) AS TO_AP ON FROM_AP.flightNumb = TO_AP.flightNumb "
			+ "WHERE FROM_AP.airportCity = ? AND FROM_AP.airportCountry = ? AND TO_AP.airportCity = ? AND TO_AP.airportCountry = ? AND DATE(FROM_AP.depatureDate) = ? ";
	private static String SORT_BY_NAME = "SELECT * FROM flight ORDER BY flightName ASC";
//	private static String SORT_BY_NAME = "SELECT * FROM flight ORDER BY ? ? ";
	private static String SORT_BY_NUMBER = "SELECT * FROM flight ORDER BY flightNumb ASC";
//	private static String SORT_BY_NUMBER = "SELECT * FROM flight ORDER BY ? ? ";

	public List<FlightBean> getSorted(String field) throws DBException {
		List<FlightBean> flights = new LinkedList<FlightBean>();
		String sort = null;
		if(field.equalsIgnoreCase("flightNumb")) {
			sort = SORT_BY_NUMBER;
		} else {
			sort = SORT_BY_NAME;
		}
		try (Connection con = DBManager.getInstance().getConnection()) {
			Statement pstmt = con.createStatement();
			LOG.trace(pstmt);
			ResultSet rs = pstmt.executeQuery(sort);
			flights = getFromQuerry(rs);
		} catch (DBException | SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new DBException(e.getMessage(), e);
		}
		return flights;
	}

	public List<FlightBean> Search(Airport from, Airport to, String date) throws DBException {
		List<FlightBean> flights = new LinkedList<FlightBean>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(FIND_BY_DATE_AND_ENDPOINTS);
			
			pstmt.setString(1, from.getCity());
			pstmt.setString(2, from.getCountry());
			pstmt.setString(3, to.getCity());
			pstmt.setString(4, to.getCountry());
			pstmt.setString(5, date);
			LOG.trace(pstmt);
			ResultSet rs = pstmt.executeQuery();
			flights = getFromQuerry(rs);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
		return flights;
	}

	public List<FlightBean> getAll() throws DBException {
		List<FlightBean> flights = new LinkedList<FlightBean>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_FLIGHTS);
			flights = getFromQuerry(rs);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
		return flights;
	}

	public FlightBean get(int numb) throws DBException {
		Flight flight = null;
		FlightBean flightBean = null;
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(SELECT_FLIGHT);
			pstmt.setInt(1, numb);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				flight = new Flight();
				flight.setDepatureDate(rs.getString("depatureDate"));
				flight.setFlightName(rs.getString("flightName"));
				flight.setFromId(rs.getInt("fromId"));
				flight.setToId(rs.getInt("toId"));
				flight.setNumber(rs.getInt("flightNumb"));
				flight.setStatus(FlightStatus.getStatus(rs.getInt("statusId")));
				flightBean = FlightBean.getInstance(flight);
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException();
		}
		return flightBean;
	}

	public void create(Flight flight) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(INSERT_FLIGHT);
			// fromId, toId, depatureDate, flightName, statusId
			pstmt.setInt(1, flight.getFromId());
			pstmt.setInt(2, flight.getToId());
			pstmt.setString(3, flight.getDepatureDate());
			pstmt.setString(4, flight.getFlightName());
			pstmt.setInt(5, flight.getStatus().getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException();
		}

	}

	public void update(Flight flight) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_FLIGHT);
			pstmt.setInt(1, flight.getFromId());
			pstmt.setInt(2, flight.getToId());
			pstmt.setString(3, flight.getDepatureDate());
			pstmt.setString(4, flight.getFlightName());
			pstmt.setInt(5, flight.getStatus().getId());
			pstmt.setInt(6, flight.getNumber());
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException();
		}

	}

	public void delete(int id) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(DELETE_FLIGHT);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException();
		}
	}

	private List<FlightBean> getFromQuerry(ResultSet rs) throws DBException {
		List<FlightBean> flights = new LinkedList<FlightBean>();
		try {
			while (rs.next()) {
				Flight flight = new Flight();
				flight.setNumber(rs.getInt("flightNumb"));
				flight.setFromId(rs.getInt("fromId"));
				flight.setToId(rs.getInt("toId"));
				flight.setDepatureDate(rs.getString("depatureDate"));
				flight.setFlightName(rs.getString("flightName"));
				flight.setStatus(FlightStatus.getStatus(rs.getInt("statusId")));
				flights.add(FlightBean.getInstance(flight));
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException(e.getMessage(), e);
		}
		return flights;
	}

}
