package ua.nure.nechaev.summarytask.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ua.nure.nechaev.summarytask.db.DBManager;
import ua.nure.nechaev.summarytask.db.entity.WorkerSpecialization;
import ua.nure.nechaev.summarytask.exception.DBException;

public class WorkerSpecializationDAO {
	private static String SELECT_ALL_SPECIALIZATION = "Select * FROM specializationen";
	private static String SELECT_BY_ID = "SELECT * FROM specializationen WHERE specID = ?";
	
	public List<WorkerSpecialization> getList(){
		List<WorkerSpecialization> specList = new LinkedList<WorkerSpecialization>();
		try(Connection con = DBManager.getInstance().getConnection()){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_SPECIALIZATION);
			while(rs.next()) {
				specList.add(WorkerSpecialization.valueOf(rs.getString("specName")));
			}
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return specList;
	}
	
	public WorkerSpecialization get(int id) {
		WorkerSpecialization spec = null;
		try(Connection con = DBManager.getInstance().getConnection()){
			PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				spec =  WorkerSpecialization.getSpec(rs.getInt("specID"));
			}
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return spec;
	}
}
