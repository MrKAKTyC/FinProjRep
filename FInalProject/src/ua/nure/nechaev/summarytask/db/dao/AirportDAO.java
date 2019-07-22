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
import ua.nure.nechaev.summarytask.db.entity.Airport;
import ua.nure.nechaev.summarytask.exception.DBException;

/**
 * DAO class for getting Airports from data base
 * @author Maks
 *
 */
public class AirportDAO {
	private static final Logger LOG = Logger.getLogger(AirportDAO.class);

	private static String SELECT_ALL_AIRPORTS = "SELECT * FROM airports";
	private static String SELECT_ALL_COUNTRY = "SELECT DISTINCT airportCountry FROM airports WHERE airportCountry LIKE ?";
	private static String SELECT_ALL_CITI = "SELECT DISTINCT airportCity FROM airports WHERE airportCountry = ?";
	private static String SELECT_ALL_AIRPORT = "SELECT airportName FROM airports WHERE airportCity = ?";
	private static String SELECT_AIRPORT = "SELECT * FROM airports WHERE airportID = ?";

	public List<Airport> getAll() {
		List<Airport> airports = new LinkedList<Airport>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery(SELECT_ALL_AIRPORTS);
				while (rs.next()) {
					Airport airport = new Airport();
					airport.setId(rs.getInt("airportID"));
					airport.setCountry(rs.getString("airportCountry"));
					airport.setCity(rs.getString("airportCity"));
					airport.setAirportName(rs.getString("airportName"));
					airports.add(airport);
				}
			}
		} catch (DBException | SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		return airports;
	}
	
	public Airport get(int id) throws DBException {
		Airport airport = null;
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_AIRPORT)) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					airport = new Airport();
					airport.setId(id);
					airport.setCountry(rs.getString("airportCountry"));
					airport.setCity(rs.getString("airportCity"));
					airport.setAirportName(rs.getString("airportName"));
				}
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DBException(e.getMessage(), e);
		}
		return airport;
	}
	public List<String> getAllCountry(String firstLeters) {
		List<String> places = new LinkedList<String>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_COUNTRY)) {
				pstmt.setString(1, firstLeters+"%");
				LOG.trace(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					places.add(rs.getString("airportCountry"));
				}
			}
		} catch (DBException | SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		LOG.trace(places);
		return places;
	}
	public List<String> getAllCities(String countryName) {
		List<String> places = new LinkedList<String>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_CITI)) {
				pstmt.setString(1, countryName);
				LOG.trace(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					places.add(rs.getString("airportCity"));
				}
			}
		} catch (DBException | SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		LOG.trace(places);
		return places;
	}
	public List<String> getAllAirports(String citiName) {
		List<String> places = new LinkedList<String>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_AIRPORT)) {
				pstmt.setString(1, citiName);
				LOG.trace(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					places.add(rs.getString("airportName"));
				}
			}
		} catch (DBException | SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		LOG.trace(places);
		return places;
	}

}
