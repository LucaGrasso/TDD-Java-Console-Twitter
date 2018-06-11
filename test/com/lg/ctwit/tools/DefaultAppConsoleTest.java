package com.lg.ctwit.tools;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.WithAssertions;

import com.lg.ctwit.core.Post;
import com.lg.ctwit.tools.DefaultAppConsole;
import com.lg.ctwit.tools.TimerOnSeconds;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test DefaultAppConsole.class")
public class DefaultAppConsoleTest implements WithAssertions{
	
	private DefaultAppConsole printer;
	
	private PrintStream printStream;
    private TimerOnSeconds timerOnSeconds = new TimerOnSeconds();
    private ByteArrayOutputStream baos;
    @Mock private Post post;  
   
	@BeforeEach
	@DisplayName("BeforeEach setUp after Mock")
	public void SetUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		baos = new ByteArrayOutputStream();
		printStream = new PrintStream(baos);
		printer = new DefaultAppConsole(printStream, timerOnSeconds);				
	}
	
	@Test
    @DisplayName("Test don't return Null")
    public void shouldInjectMocks() {
		assertThat(DefaultAppConsole.class).isPublic();
		
        assertThat(printer).isNotNull();
        assertThat(printStream).isNotNull();
        assertThat(timerOnSeconds).isNotNull();
        assertThat(baos).isNotNull();
        assertThat(post).isNotNull();
	}
		
	@Test
	@DisplayName("Simple Mock Post Test")
	public void testPostObject() {
		
		when(post.getUserName()).thenReturn("Alice");
		when(post.getMessage()).thenReturn("I love the weather today");
		assertEquals("Alice", post.getUserName());
		assertEquals("I love the weather today", post.getMessage());		
	}
		
	@Test
    @DisplayName("Test Display Post For Wall")
	public void testDisplayPostForWall() throws UnsupportedEncodingException {	
		
		when(post.getUserName()).thenReturn("Alice");
		when(post.getMessage()).thenReturn("I love the weather today");
		assertEquals("Alice", post.getUserName());
		assertEquals("I love the weather today", post.getMessage());
		
		post = new Post("Alice", "I love the weather today", new Date(System.currentTimeMillis() - 5 * 60 * 1000L));
		printer.displayPostForWall(post);
		
		String expectedResult = "Alice - I love the weather today (5 minutes ago)" + System.getProperty("line.separator");
		assertEquals(expectedResult , baos.toString("UTF-8"));		
	}

	@Test
    @DisplayName("Test Display Post For User")
	public void testDisplayPostForUser() throws UnsupportedEncodingException {
		when(post.getUserName()).thenReturn("Alice");
		when(post.getMessage()).thenReturn("I love the weather today");
		assertEquals("Alice", post.getUserName());
		assertEquals("I love the weather today", post.getMessage());
		
		
		post = new Post("Alice", "I love the weather today", new Date(System.currentTimeMillis() - 5 * 60 * 1000L));
		
		printer = new DefaultAppConsole(printStream, timerOnSeconds);
		printer.displayPostForUser(post);
		
		String expectedResult = "I love the weather today (5 minutes ago)";
		assertEquals(expectedResult + System.getProperty("line.separator"), baos.toString("UTF-8"));
	}

	@Test
	@DisplayName("Test Display Single Message")
	public void testDisplayMessage() throws UnsupportedEncodingException {
		String message = "Good evening";
		printer.displayMessage(message);
		assertEquals(message + System.getProperty("line.separator"), baos.toString("UTF-8"));
	}
}
