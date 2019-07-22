package ua.nure.nechaev.summarytask.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ua.nure.nechaev.summarytask.web.command.AutocompleteCommand;
import ua.nure.nechaev.summarytask.web.command.CommandContainer;
import ua.nure.nechaev.summarytask.web.command.LangCommand;
import ua.nure.nechaev.summarytask.web.command.LoginCommand;
import ua.nure.nechaev.summarytask.web.command.LogoutCommand;
import ua.nure.nechaev.summarytask.web.command.crew.CrewAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.crew.CrewDeleteComand;
import ua.nure.nechaev.summarytask.web.command.crew.CrewListCommand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightDeleteComand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightEditComand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightListCommand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightUpdateComand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightsCommand;
import ua.nure.nechaev.summarytask.web.command.manager.ManagerAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.manager.ManagerDeleteComand;
import ua.nure.nechaev.summarytask.web.command.manager.ManagerEditCommand;
import ua.nure.nechaev.summarytask.web.command.manager.ManagerListCommand;
import ua.nure.nechaev.summarytask.web.command.manager.ManagerUpdateComand;
import ua.nure.nechaev.summarytask.web.command.report.ReportAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.report.ReportListCommand;
import ua.nure.nechaev.summarytask.web.command.report.ReportUpdateCommand;
import ua.nure.nechaev.summarytask.web.command.worker.WorkerAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.worker.WorkerDeleteComand;
import ua.nure.nechaev.summarytask.web.command.worker.WorkerEditComand;
import ua.nure.nechaev.summarytask.web.command.worker.WorkerListCommand;
import ua.nure.nechaev.summarytask.web.command.worker.WorkerUpdateComand;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public InitListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		CommandContainer commands = new CommandContainer();
		// public
		commands.add("login", new LoginCommand());
		commands.add("flights", new FlightsCommand());
		commands.add("lang", new LangCommand());
		commands.add("autocomplete", new AutocompleteCommand());
		// secured
		// common commands
		commands.add("logout", new LogoutCommand());
		// manager commands
		commands.add("managerList", new ManagerListCommand());
		commands.add("addNewManager", new ManagerAddNewCommand());
		commands.add("managerEdit", new ManagerEditCommand());
		commands.add("deleteManager", new ManagerDeleteComand());
		commands.add("updateManager", new ManagerUpdateComand());
		// worker commands
		commands.add("workerList", new WorkerListCommand());
		commands.add("workerAddNew", new WorkerAddNewCommand());
		commands.add("workerEdit", new WorkerEditComand());
		commands.add("workerUpdate", new WorkerUpdateComand());
		commands.add("workerDelete", new WorkerDeleteComand());
		// flight commands
		commands.add("flightList", new FlightListCommand());
		commands.add("flightAddNew", new FlightAddNewCommand());
		commands.add("flightEdit", new FlightEditComand());
		commands.add("flightUpdate", new FlightUpdateComand());
		commands.add("flightDelete", new FlightDeleteComand());
		// crew commands
		commands.add("crewList", new CrewListCommand());
		commands.add("crewAddNew", new CrewAddNewCommand());
		commands.add("crewDelete", new CrewDeleteComand());
		// report commands
		commands.add("reportList", new ReportListCommand());
		commands.add("reportAdd", new ReportAddNewCommand());
		commands.add("reportUpdate", new ReportUpdateCommand());
		sce.getServletContext().setAttribute("commands", commands);
	}

}
