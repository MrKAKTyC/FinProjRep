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
import ua.nure.nechaev.summarytask.db.bean.WorkerBean;
import ua.nure.nechaev.summarytask.db.entity.Worker;
import ua.nure.nechaev.summarytask.exception.DBException;

public class WorkerDAO {
	private static final Logger LOG = Logger.getLogger(WorkerDAO.class);
	
	private static String SELECT_ALL_AVAIBLE = "SELECT workers.* FROM workers LEFT JOIN (SELECT * FROM flightcrew WHERE flightcrew.flightID  = ? ) AS SF ON workers.workerId = SF.workerID WHERE SF.crewID IS NULL";
	private static String SELECT_ALL_WORKERS = "SELECT * FROM workers";
	private static String SELECT_WORKER = "SELECT * FROM workers WHERE workerId = ?";
	private static String INSERT_WORKER = "INSERT INTO workers (workerName, workerSpec) VALUES (?, ?)";
	private static String UPDATE_WORKER = "UPDATE workers SET workerName = ?, workerSpec = ? WHERE workerId = ? ";
	private static String DELETE_WORKER = "DELETE FROM workers WHERE workerId = ?";

	public List<WorkerBean> getAll() {
		List<WorkerBean> workers = new LinkedList<WorkerBean>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_WORKERS);
			while (rs.next()) {
				Worker worker = new Worker();
				worker.setId(rs.getInt("workerId"));
				worker.setName(rs.getString("workerName"));
				worker.setSpec(rs.getInt("workerSpec"));
				LOG.trace("selected worker : " + worker);
				workers.add(WorkerBean.getInstance(worker));
			}
		} catch (DBException | SQLException e) {
			LOG.error(e);
			e.printStackTrace();
		}
		return workers;
	}
	
	public List<WorkerBean> getAvaible(int forFlight) throws DBException {
		List<WorkerBean> avaibleWorkers = new LinkedList<WorkerBean>();
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_AVAIBLE);
			pstmt.setInt(1, forFlight);
			LOG.trace(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Worker worker = new Worker();
				worker.setId(rs.getInt("workerId"));
				worker.setName(rs.getString("workerName"));
				worker.setSpec(rs.getInt("workerSpec"));
				avaibleWorkers.add(WorkerBean.getInstance(worker));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage(), e);
		}
		return avaibleWorkers;
	}

	public Worker get(int id) {
		Worker worker = null;
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(SELECT_WORKER);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				worker = new Worker();
				worker.setId(id);
				worker.setName(rs.getString("workerName"));
				worker.setSpec(rs.getInt("workerSpec"));
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
		}
		return worker;
	}

	public void create(Worker worker) {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(INSERT_WORKER);
			pstmt.setString(1, worker.getName());
			pstmt.setInt(2, worker.getSpec());
			pstmt.executeUpdate();
			con.commit();
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Worker worker) {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_WORKER);
			pstmt.setString(1, worker.getName());
			pstmt.setInt(2, worker.getSpec());
			pstmt.setInt(3, worker.getId());
			pstmt.executeUpdate();
			con.commit();
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int workerId) {
		try (Connection con = DBManager.getInstance().getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(DELETE_WORKER);
			pstmt.setInt(1, workerId);
			pstmt.executeUpdate();
			con.commit();
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
