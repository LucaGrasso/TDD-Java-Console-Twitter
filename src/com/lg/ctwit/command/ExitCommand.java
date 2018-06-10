package com.lg.ctwit.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;

import com.lg.ctwit.tools.AppConsole;
import com.lg.ctwit.command.Command;

public class ExitCommand implements Command{

	private static final Pattern REGEX = Pattern.compile("^exit$");
	private AppConsole appConsole;

	public ExitCommand(AppConsole appPrinter) {
		this.appConsole = appPrinter;
	}

	@Override
	public void execute(String commandLine) {
		appConsole.displayMessage("Bye!");
		System.exit(0);
	}

	@Override
	public boolean matches(String input) {
		if (StringUtils.isNotBlank(input)) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}
	
}

