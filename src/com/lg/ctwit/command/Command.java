package com.lg.ctwit.command;

public interface Command {
	/**
	 * input command string
	 * 
	 * @param commandLine
	 * 
	 *            Command string to execute from User
	 *            
	 * @return the result of the command
	 */
	public void execute(String commandLine);

	/**
	 * Checks command is eligible to execute the given string
	 * 
	 * @param input
	 * 
	 *            command string which is able to be executed 
	 * @return
	 */
	boolean matches(String input);
}
