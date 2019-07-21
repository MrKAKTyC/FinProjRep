package ua.nure.nechaev.summarytask.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private Map<String, Command> commands = new TreeMap<String, Command>();
	
	public CommandContainer() {
		commands = new TreeMap<String, Command>();
	}

	public Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}

	public void add(String commandRequest, Command command) {
		commands.put(commandRequest, command);
	}

}
