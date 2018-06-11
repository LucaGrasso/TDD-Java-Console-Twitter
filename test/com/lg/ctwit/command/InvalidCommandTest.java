package com.lg.ctwit.command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import com.lg.ctwit.command.InvalidCommand;
import com.lg.ctwit.tools.AppConsole;
import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;


@DisplayName("Test InvalidCommand.class")
public class InvalidCommandTest {
	private static final String USER = "Alice xxx";
	
	private InvalidCommand command;
	private TimerOnSeconds timerOnSeconds = new TimerOnSeconds();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;

	@BeforeEach
	public void setUp() {	
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		AppConsole printer = new DefaultAppConsole(printStream, timerOnSeconds);
		command = new InvalidCommand(printer);
	}

	@Test
	@DisplayName("Test Match")
	public void testMatch() {
		assertFalse(command.matches(USER));
	}

	@Test
	@DisplayName("Test Null value")
	public void testMatchNull() {
		assertFalse(command.matches(null));
	}

	@Test
	@DisplayName("Test Empty")
	public void testMatchEmptyl() {
		assertFalse(command.matches(""));
	}

	@Test
	@DisplayName("Test Read")
	public void testRead() throws UnsupportedEncodingException {
		command.execute(USER);

		String result = baos.toString("UTF-8");
		String expectedResult = String.format("Command: %s is invalid!" + System.getProperty("line.separator"), USER);
		assertEquals(expectedResult, result);
	}
}