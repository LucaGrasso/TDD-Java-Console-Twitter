package com.lg.ctwit.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lg.ctwit.command.FollowCommand;
import com.lg.ctwit.core.User;
import com.lg.ctwit.repository.Repository;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test FollowlCommand.class")
public class FollowCommandTest {
	private static final String USER_1 = "Charlie";
	private static final String USER_2 = "Alice";
	private static final String COMMAND = USER_1 + " follows " + USER_2;

	private FollowCommand command;
	
	@Mock private Repository repository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		command = new FollowCommand(repository);
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
	@DisplayName("Test Follow")
	public void testFollow() {
		User spyUser1 = spy(new User(USER_1));
		User mockUser2 = mock(User.class);

		doReturn(spyUser1).when(repository).getOrCreateUser(USER_1);
		doReturn(mockUser2).when(repository).getOrCreateUser(USER_2);

		command.execute(COMMAND);

		verify(repository).getOrCreateUser(USER_1);
		verify(repository).getOrCreateUser(USER_2);
		verify(spyUser1).addFollowing(mockUser2);
		assertTrue(spyUser1.getFollowingUsers().contains(mockUser2));
	}

}
