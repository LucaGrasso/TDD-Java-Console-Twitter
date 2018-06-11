package com.lg.ctwit.core;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertAll;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.assertj.core.api.WithAssertions;

import com.lg.ctwit.core.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test User.class")
public class UserTest implements WithAssertions {
	
	@Mock private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	
	
	@Test
    @DisplayName("Test User")
    public void shouldInjectMocks() {
		assertThat(User.class).isPublic();
        assertThat(user).isNotNull();
     
        when(user.getName()).thenReturn("Alice");
        assertEquals("Alice", user.getName());
        verify(user, times(1)).getName();
	}
	
	@Test
	@DisplayName("Test post")
	public void testPost() {
		user = new User("Alice");
		
		user.addPost("I Love the weather today");
		
		
		assertAll("post",
				() -> assertEquals(1, user.getPosts().size()),
	            () -> assertEquals("I Love the weather today", user.getPosts().get(0).getMessage()),
	            () -> assertEquals("Alice", user.getPosts().get(0).getUserName()),
	            () -> user.getPosts().get(0).getUserName()
	        );
	}

	@Test
	@DisplayName("Test following post")
	public void testFollowing() {
		user = new User("Alice");
		user.addFollowing(new User("Charlie"));
		assertAll("user",
				() -> assertEquals(1, user.getFollowingUsers().size()),
	            () -> assertEquals("Charlie", user.getFollowingUsers().get(0).getName())
	        );
	}

	@Test
	@DisplayName("Test only 1 unique following")
	public void testUniqueFollowing() {
		user = new User("Alice");
		user.addFollowing(new User("Charlie"));
		user.addFollowing(new User("Charlie"));
		
		assertEquals(1, user.getFollowingUsers().size());
	}

	@Test
	@DisplayName("Test add following")
	public void testSetFollowing() {
		user = new User("Alice");
		user.setFollowingUsers(Arrays.asList(new User("Charlie")));
		assertEquals(1, user.getFollowingUsers().size());
	}
}
