package com.lg.ctwit.command;

import com.lg.ctwit.tools.AppConsole;

public class InvalidCommand implements Command{

	private AppConsole postDisplayer;

	public InvalidCommand(AppConsole postDisplayer) {
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		postDisplayer.displayMessage(String.format("Command: %s is invalid!", commandLine));
	}

	@Override
	public boolean matches(String input) {
		return false;
	}

}
