package com.lg.ctwit.command;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.lg.ctwit.tools.AppConsole;
import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;
import com.lg.ctwit.command.ExitCommand;
import com.lg.ctwit.utils.ExitDeniedSecurityManager;
import com.lg.ctwit.utils.ExitDeniedSecurityManager.ExitSecurityException;

@DisplayName("Test ExitCommand.class")
public class ExitCommandTest {
	private static final String COMMAND = "exit";
	
	private ExitCommand command;
	private TimerOnSeconds timerOnSeconds = new TimerOnSeconds();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;


	@BeforeEach
	public void setUp() {
		System.setSecurityManager(new ExitDeniedSecurityManager());
		
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		AppConsole printer = new DefaultAppConsole(printStream, timerOnSeconds);
		command = new ExitCommand(printer);

	}

	@Test
	@DisplayName("Test Match")
	public void testMatch() {
		assertTrue(command.matches(COMMAND));
	}

	@Test
	@DisplayName("Test Null")
	public void testMatchNull() {
		assertFalse(command.matches(null));
	}

	@Test
	@DisplayName("Test Empty")
	public void testMatchEmpty() {
		assertFalse(command.matches(""));
	}

	@Test
	@DisplayName("Test Exit")
	public void testExit() throws UnsupportedEncodingException {
		
        try {
    		command.execute(COMMAND);
    		fail("Expected exit without error");
    
        } catch (ExitSecurityException e) {
            int status = e.getStatus();
            assertEquals(0, status);
            String result = baos.toString("UTF-8");
    		String expectedResult = "Bye!" + System.getProperty("line.separator");
    		assertEquals(expectedResult, result);
        }	
	}
}
