package com.lg.ctwit.command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lg.ctwit.tools.AppConsole;
import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;
import com.lg.ctwit.core.User;
import com.lg.ctwit.core.Post;
import com.lg.ctwit.repository.Repository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test ReadCommand.class")
public class ReadCommandTest {
	private static final String USER = "Bob";
	
	private ReadCommand command;
	private TimerOnSeconds timerOnSeconds = new TimerOnSeconds();
	private PrintStream printStream;
	private ByteArrayOutputStream baos;
	
	@Mock private Repository repository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		AppConsole printer = new DefaultAppConsole(printStream, timerOnSeconds);
		command = new ReadCommand(repository, printer);
	}

	
	@Test
	@DisplayName("Test - Mach Command")
	public void testMatch() {
		assertTrue(command.matches(USER));
	}

	@Test
	@DisplayName("Test - Null Command")
	public void testMatchNull() {
		assertFalse(command.matches(null));
	}

	@Test
	@DisplayName("Test - Empty Command")
	public void testMatchEmptyl() {
		assertFalse(command.matches(""));
	}

	@Test
	@DisplayName("Read Test")
	public void testRead() throws UnsupportedEncodingException {
		User spyUser = spy(new User(USER));
		spyUser.setPosts(Arrays.asList(new Post(USER, "Good game though.", new Date(
				System.currentTimeMillis() - 1 * 60 * 1000L)),
				new Post(USER, "Damn! We lost!", new Date(System.currentTimeMillis() - 60 * 10 * 1000L))));

		doReturn(spyUser).when(repository).getOrCreateUser(USER);
		
		command.execute(USER);

		verify(repository).getOrCreateUser(USER);
		verify(spyUser).getPosts();

		String result = baos.toString("UTF-8");
		String expectedResult = "Good game though. (1 minute ago)\nDamn! We lost! (10 minutes ago)\n";
		assertEquals(expectedResult, result);
	}
}
