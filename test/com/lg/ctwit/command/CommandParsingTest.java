package com.lg.ctwit.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lg.ctwit.core.User;
import com.lg.ctwit.repository.Repository;
import com.lg.ctwit.tools.AppConsole;
import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ExecuteCommand.class")
public class CommandParsingTest {
	private static final String USER = "Charlie";
	private static final String USER_2 = "Alice";
	private static final String MESSAGE = "I'm in New York today! Anyone want to have a coffee?";
	private static final String POST_COMMAND = USER + " -> " + MESSAGE;
	private static final String FOLLOW_COMMAND = USER + " follows " + USER_2;
	private static final String WALL_COMMAND = USER + " wall";
	
	private CommandParsing commandParsing;
	
	private TimerOnSeconds timeronSeconds = new TimerOnSeconds();
	private PrintStream printStream;
	@Mock private Repository repository;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		AppConsole printer = new DefaultAppConsole(printStream, timeronSeconds);
		commandParsing = spy(new CommandParsing(repository, printer));
	}

	@Test
	@DisplayName("Test execute Post")
	public void executePostCommandTest() throws Exception {
		User mockUser = mock(User.class);		
		doReturn(mockUser).when(repository).getOrCreateUser(USER);
		commandParsing.executeCommand(POST_COMMAND);
		verify(commandParsing).getCommand(POST_COMMAND);
	}

	@Test
	@DisplayName("Test Post Command")
	public void getPostCommandTest() throws Exception {
		Command returnedCommand = commandParsing.getCommand(POST_COMMAND);
		assertTrue(returnedCommand instanceof PostCommand);
	}

	@Test
	@DisplayName("Test Follow Command")
	public void getFollowCommandTest() throws Exception {
		Command returnedCommand = commandParsing.getCommand(FOLLOW_COMMAND);
		assertTrue(returnedCommand instanceof FollowCommand);
	}

	@Test
	@DisplayName("Test Read Command")
	public void getReadCommandTest() throws Exception {
		Command returnedCommand = commandParsing.getCommand(USER);
		assertTrue(returnedCommand instanceof ReadCommand);
	}

	@Test
	@DisplayName("Test Wall Command")
	public void getWallCommandTest() throws Exception {
		Command returnedCommand = commandParsing.getCommand(WALL_COMMAND);
		assertTrue(returnedCommand instanceof WallCommand);
	}
}