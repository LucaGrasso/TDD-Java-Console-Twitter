package com.lg.ctwit.tools;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.lg.ctwit.tools.TimerOnSeconds;

@DisplayName("Test TimerOnSecond.class")
public class TimerOnSecondsTest {
	private TimerOnSeconds formatter;
	
	@BeforeEach
	public void init() {
		formatter = new TimerOnSeconds();
	}
	
	@Test
	@DisplayName("Test Display second - seconds")
	public void formatTimeDifferenceTestWithSeconds() {
		Date date = new Date();
		Date spydate = spy(date);
			
		// Use spy for multiTest for second - seconds
		doReturn(System.currentTimeMillis() - 26 * 1000L).when(spydate).getTime();
		
		String response = formatter.formatTimeDifference(date);
		String spyresponse = formatter.formatTimeDifference(spydate);
		
		assertEquals("(0 second ago)", response);
		assertEquals("(26 seconds ago)", spyresponse);
	
	}

	@Test
	@DisplayName("Test Display minute - minutes")
	public void formatTimeDifferenceTestWithMinutes() {
		Date date = new Date(System.currentTimeMillis() - 60 * 1000L);
		Date spydate = spy(date);
		
		// Use spy for multiTest for minute - minutes

		doReturn(System.currentTimeMillis() - 10 * 60 * 1000L).when(spydate).getTime();
		
		String response = formatter.formatTimeDifference(date);
		String spyresponse = formatter.formatTimeDifference(spydate);
		
		assertEquals("(1 minute ago)", response);
		assertEquals("(10 minutes ago)", spyresponse);
	}

	@Test
	@DisplayName("Test Display hour - hours")
	public void formatTimeDifferenceTestWithHours() {
		Date date = new Date(System.currentTimeMillis() - 1 * 60 * 60 * 1000L);
		Date spydate = spy(date);
		
		// Use spy for multiTest for hour - hours
		
		doReturn(System.currentTimeMillis() - 10 * 60 * 60 * 1000L).when(spydate).getTime();
		
		String response = formatter.formatTimeDifference(date);
		String spyresponse = formatter.formatTimeDifference(spydate);
		
		assertEquals("(1 hour ago)", response);
		assertEquals("(10 hours ago)", spyresponse);
	}

	@Test
	@DisplayName("Test Display day - days")
	public void formatTimeDifferenceTestWithDays() {
		Date date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000L);
		Date spydate = spy(date);
		
		// Use spy for multiTest for day - days

		doReturn(System.currentTimeMillis() - 48 * 60 * 60 * 1000L).when(spydate).getTime();

		String response = formatter.formatTimeDifference(date);
		String spyresponse = formatter.formatTimeDifference(spydate);
		
		assertEquals("(1 day ago)", response);
		assertEquals("(2 days ago)", spyresponse);

	}
}
