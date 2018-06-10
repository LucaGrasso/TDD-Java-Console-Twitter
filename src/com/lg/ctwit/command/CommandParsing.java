package com.lg.ctwit.command;

import java.util.ArrayList;
import java.util.List;

import com.lg.ctwit.command.Command;
import com.lg.ctwit.command.ExitCommand;
import com.lg.ctwit.command.FollowCommand;
import com.lg.ctwit.command.InvalidCommand;
import com.lg.ctwit.command.PostCommand;
import com.lg.ctwit.command.ReadCommand;
import com.lg.ctwit.command.WallCommand;
import com.lg.ctwit.repository.Repository;
import com.lg.ctwit.tools.AppConsole;

public class CommandParsing {
	private List<Command> commands = new ArrayList<>();
	private Command invalidCommand;

	public CommandParsing(Repository repository, AppConsole appConsole) {
		commands.add(new ExitCommand(appConsole));
		commands.add(new PostCommand(repository));
		commands.add(new ReadCommand(repository, appConsole));
		commands.add(new FollowCommand(repository));
		commands.add(new WallCommand(repository, appConsole));

		invalidCommand = new InvalidCommand(appConsole);
	}

	public void executeCommand(String commandLine) {
		Command command = getCommand(commandLine);
		command.execute(commandLine);
	}

	public Command getCommand(String commandLine) {
		for (Command command : commands) {
			if (command.matches(commandLine))
				return command;
		}
		return invalidCommand;
	}

}
