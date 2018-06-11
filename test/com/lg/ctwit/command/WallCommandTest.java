package com.lg.ctwit.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

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

import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;
import com.lg.ctwit.command.WallCommand;
import com.lg.ctwit.core.User;
import com.lg.ctwit.core.Post;
import com.lg.ctwit.repository.Repository;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test WallCommand.class")
public class WallCommandTest {
	
	private static final String USER = "Charlie";
	private static final String USER_2 = "Alice";
	private static final String USER_3 = "Bob";
	private static final String COMMAND = USER + " wall";

	private ByteArrayOutputStream baos;	
	private WallCommand command;	
	private PrintStream printStream;
	private TimerOnSeconds timerOnSeconds = new TimerOnSeconds();
	private DefaultAppConsole printer;
	
	@Mock private Repository repository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		printer = new DefaultAppConsole(printStream, timerOnSeconds);		
		command = new WallCommand(repository, printer);
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
	public void testMatchEmptyl() {
		assertFalse(command.matches(""));
	}

	@Test
	@DisplayName("Test Read")
	public void testRead() throws UnsupportedEncodingException {

		
		User spyuser = spy(new User(USER));
		spyuser.setPosts(Arrays.asList(new Post(USER, "I'm in New York today! Anyone want to have a coffee?", new Date(System.currentTimeMillis() - 2 * 1000L))));

		
		User spyuser2 = spy(new User(USER_2));
		spyuser2.setPosts(Arrays.asList(new Post(USER_2, "I love the weather today", new Date(System.currentTimeMillis() - 5 * 60 * 1000L))));
		
		User spyuser3 = spy(new User(USER_3));	
		spyuser3.setPosts(Arrays.asList(new Post(USER_3, "Good game though.", new Date(System.currentTimeMillis() - 1 * 60 * 1000L)), new Post(USER_3, "Damn! we lost.", new Date(System.currentTimeMillis() - 2 * 60 * 1000L))));
		
		spyuser.addFollowing(spyuser2);
		
		doReturn(spyuser).when(repository).getOrCreateUser(USER);
		
		command.execute(COMMAND);
		
		verify(repository).getOrCreateUser(USER);
		verify(spyuser).getPosts();
		verify(spyuser).getFollowingUsers();
		verify(spyuser2).getPosts();
		String result = baos.toString("UTF-8");
		String expectedResult = "Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)" + System.getProperty("line.separator") + "Alice - I love the weather today (5 minutes ago)" + System.getProperty("line.separator");
		assertEquals(expectedResult, result);
		
		// This part of code is useless for testing but i want to try a long wall for futures studies
		
		baos.reset();

		spyuser.setPosts(Arrays.asList(new Post(USER, "I'm in New York today! Anyone want to have a coffee?", new Date(System.currentTimeMillis() - 15 * 1000L))));
		spyuser.addFollowing(spyuser3);
		doReturn(spyuser).when(repository).getOrCreateUser(USER);
		
		command.execute(COMMAND);
		
		result = baos.toString("UTF-8");
		expectedResult = "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)" + System.getProperty("line.separator") + 
						 "Bob - Good game though. (1 minute ago)" + System.getProperty("line.separator") +
						 "Bob - Damn! we lost. (2 minutes ago)" + System.getProperty("line.separator") +
						 "Alice - I love the weather today (5 minutes ago)" + System.getProperty("line.separator");
		assertEquals(expectedResult, result);
	}
}
