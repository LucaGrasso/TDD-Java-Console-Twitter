package com.lg.ctwit.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lg.ctwit.core.User;
import com.lg.ctwit.repository.MemoryOfRepository;

public class MemoryOfRepositoryTest {
	private static final String USER = "Charlie";

	private MemoryOfRepository repository;

	@BeforeEach
	public void setUp() {
		repository = new MemoryOfRepository();
	}

	@Test
	public void testGetOrCreateUser() {
		User user = repository.getOrCreateUser(USER);
		assertNotNull(user);
		assertEquals(USER, user.getName());
	}

	@Test
	public void testGetExistingUser() {
		User user = repository.getOrCreateUser(USER);
		User existingUser = repository.getOrCreateUser(USER);
		assertEquals(user, existingUser);
	}
}
