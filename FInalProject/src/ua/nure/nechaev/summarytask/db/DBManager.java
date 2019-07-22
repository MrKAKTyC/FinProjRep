package ua.nure.nechaev.summarytask.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.exception.DBException;

/**
 * 
 * @author Maks
 * Class for obtaining connection
 */

public class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	private static DBManager instance;
	private DataSource ds;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			// ST4DB - the name of data source
			ds = (DataSource) envContext.lookup("jdbc/ST4DB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @return Connection to database
	 * @throws DBException if any problems occurred during obtaining connection 
	 */
	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			LOG.error("Messages.ERR_CANNOT_OBTAIN_CONNECTION", ex);
			throw new DBException("Messages.ERR_CANNOT_OBTAIN_CONNECTION", ex);
		}
		return con;
	}

}
