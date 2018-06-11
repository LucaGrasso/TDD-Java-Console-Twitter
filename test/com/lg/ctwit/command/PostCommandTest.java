package com.lg.ctwit.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lg.ctwit.command.PostCommand;
import com.lg.ctwit.core.User;
import com.lg.ctwit.repository.Repository;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test PostCommand.class")
public class PostCommandTest {
	private static final String USER = "Alice";
	private static final String MESSAGE = "I Love the weather today.";
	private static final String COMMAND = USER + " -> " + MESSAGE;

	private PostCommand command;
	
	@Mock private Repository repository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new PostCommand(repository);
	}

	@Test
	@DisplayName("Test - Match")
	public void testMatch() {
		assertTrue(command.matches(COMMAND));
	}

	@Test
	@DisplayName("Test Match Null")
	public void testMatchNull() {
		assertFalse(command.matches(null));
	}

	@Test
	@DisplayName("Test Match Empty")
	public void testMatchEmptyl() {
		assertFalse(command.matches(""));
	}

	@Test
	@DisplayName("Test Post Command")
	public void testPost() {
		User mockUser = mock(User.class);

		when(repository.getOrCreateUser(USER)).thenReturn(mockUser);

		command.execute(COMMAND);

		verify(repository).getOrCreateUser(USER);
		verify(mockUser).addPost(MESSAGE);

	}

}
