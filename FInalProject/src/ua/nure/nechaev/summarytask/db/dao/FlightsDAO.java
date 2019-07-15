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

	public List<Flight> getAll() throws DBException {
		List<Flight> flights = new LinkedList<Flight>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_FLIGHTS);
			while (rs.next()) {
				Flight flight = new Flight();
				flight.setNumber(rs.getInt("flightNumb"));
				flight.setFromId(rs.getInt("fromId"));
				flight.setToId(rs.getInt("toId"));
				flight.setDepatureDate(rs.getString("depatureDate"));
				flight.setFlightName(rs.getString("flightName"));
				flight.setStatus(FlightStatus.getStatus(rs.getInt("statusId")));
				flights.add(flight);
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
		return flights;
	}

	public Flight get(int numb) throws DBException {
		Flight flight = null;
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
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException();
		}
		return flight;
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
		try(Connection con = DBManager.getInstance().getConnection()){
			PreparedStatement pstmt = con.prepareStatement(DELETE_FLIGHT);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			LOG.error(e);
			throw new DBException();
		}
	}

}
