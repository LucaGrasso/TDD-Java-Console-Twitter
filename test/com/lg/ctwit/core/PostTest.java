package com.lg.ctwit.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import com.lg.ctwit.core.Post;

@Nested
@DisplayName("Test Post.Class")
public class PostTest{
		
	@Test
	@DisplayName("Post without Date")
	public void NoDatePostTest(){
		Post post = new Post("Alice", "I Love the weather today");
		
		assertAll("post",
				() -> assertEquals("I Love the weather today", post.getMessage()),
		        () -> assertEquals("Alice", post.getUserName()),
		        () -> assertNotNull(post.getPublishDate())
		);		
	}
	@Test
	@DisplayName("Post with Date")
	public void DatePostTest(){
		Post post = new Post("Alice", "I Love the weather today",new Date(System.currentTimeMillis() - 5 * 60 * 1000L));
		
		assertAll("post",
				() -> assertEquals("I Love the weather today", post.getMessage()),
		        () -> assertEquals("Alice", post.getUserName()),
		        () -> assertNotNull(post.getPublishDate())
		);		
	}
}