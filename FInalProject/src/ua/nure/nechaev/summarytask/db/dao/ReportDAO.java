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
import ua.nure.nechaev.summarytask.db.entity.Report;
import ua.nure.nechaev.summarytask.exception.DBException;
/**
 * DAO class for getting reports from data base
 * @author Maks
 *
 */
public class ReportDAO {

	private static final Logger LOG = Logger.getLogger(ReportDAO.class);
	private static String SELECT_ALL_PENDING = "SELECT * FROM report WHERE status = 3";
	private static String INSERT_NEW_REPORT = "INSERT INTO report (flightId, description, status) VALUES (?, ?, '3');";
	private static String UPDATE_REPORT_STAUS = "UPDATE report SET status = ? WHERE reportId = ?";

	public List<Report> getAllPending() throws DBException {
		List<Report> reports = new LinkedList<Report>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery(SELECT_ALL_PENDING);
				while (rs.next()) {
					Report report = new Report();
					report.setId(rs.getInt("reportId"));
					report.setFlightId(rs.getInt("flightId"));
					report.setDescription(rs.getString("description"));
					reports.add(report);
					LOG.trace("All reports selected");
				}
			}
		} catch (SQLException e) {
			LOG.error("Exeption in geting reports", e);
			throw new DBException(e.getMessage(), e);
		}
		return reports;

	}

	public void insert(Report report) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			try (PreparedStatement pstmt = con.prepareStatement(INSERT_NEW_REPORT)) {
				pstmt.setInt(1, report.getFlightId());
				pstmt.setString(2, report.getDescription());
				pstmt.executeUpdate();
				con.commit();
				LOG.trace("Report inserted");
			}
		} catch (SQLException e) {
			LOG.error("Error in adding report", e);
			throw new DBException(e.getMessage(), e);
		}
	}

	public void updateStatus(Report report) throws DBException {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_REPORT_STAUS);
			pstmt.setInt(1, report.getStatus());
			pstmt.setInt(2, report.getId());
			pstmt.executeUpdate();
			con.commit();
			LOG.trace("Report status updated");
		} catch (SQLException e) {
			LOG.error("Exeption in report status updating", e);
			throw new DBException(e.getMessage(), e);
		}
	}

}
