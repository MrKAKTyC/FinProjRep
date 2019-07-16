package ua.nure.nechaev.summarytask.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.nechaev.summarytask.web.command.crew.CrewAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.crew.CrewDeleteComand;
import ua.nure.nechaev.summarytask.web.command.crew.CrewListCommand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightAddNewCommand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightDeleteComand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightEditComand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightListCommand;
import ua.nure.nechaev.summarytask.web.command.flight.FlightUpdateComand;
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

public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("adminMenu", new AdminMenuCommand());
		//manager commands
		commands.put("managerList", new ManagerListCommand());
		commands.put("addNewManager", new ManagerAddNewCommand());
		commands.put("managerEdit", new ManagerEditCommand());
		commands.put("deleteManager", new ManagerDeleteComand());
		commands.put("updateManager", new ManagerUpdateComand());
		//worker commands
		commands.put("workerList", new WorkerListCommand());
		commands.put("workerAddNew", new WorkerAddNewCommand());
		commands.put("workerEdit", new WorkerEditComand());
		commands.put("workerUpdate", new WorkerUpdateComand());
		commands.put("workerDelete", new WorkerDeleteComand());
		//flight commands
		commands.put("flightList", new FlightListCommand());
		commands.put("flightAddNew", new FlightAddNewCommand());
		commands.put("flightEdit", new FlightEditComand());
		commands.put("flightUpdate", new FlightUpdateComand());
		commands.put("flightDelete", new FlightDeleteComand());
		//crew commands
		commands.put("crewList", new CrewListCommand());
		commands.put("crewAddNew", new CrewAddNewCommand());
		commands.put("crewDelete", new CrewDeleteComand());
		//report commands
		commands.put("reportList", new ReportListCommand());
		commands.put("reportAdd", new ReportAddNewCommand());
		commands.put("reportUpdate", new ReportUpdateCommand());
		
//		LOG.debug("Command container was successfully initialized");
//		LOG.trace("Number of commands --> " + commands.size());
	}

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}

}
