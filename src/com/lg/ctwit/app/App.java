package com.lg.ctwit.app;

import java.util.Scanner;

import com.lg.ctwit.command.CommandParsing;
import com.lg.ctwit.repository.MemoryOfRepository;
import com.lg.ctwit.repository.Repository;
import com.lg.ctwit.tools.AppConsole;
import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;

/**
 * Java Console Twitter - Main class.
 * Start Point.
 * 
 * lucaG
 *
 */


public class App {
	
	/** option for console e input
	* 
	* lucaG
	* 
	*/
	
	private static final String PROMPT = ">";
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Repository repository = new MemoryOfRepository();
		AppConsole printer = new DefaultAppConsole(System.out, new TimerOnSeconds());
		CommandParsing commandParser = new CommandParsing(repository, printer);

		while (true) {
			System.out.print(PROMPT);
			String input = scanner.nextLine();
			commandParser.executeCommand(input);
		}
	}
	
}
