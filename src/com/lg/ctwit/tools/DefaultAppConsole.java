package com.lg.ctwit.tools;

import java.io.PrintStream;

import com.lg.ctwit.core.Post;

public class DefaultAppConsole implements AppConsole {
	private static final String MESSAGE_FORMAT_FOR_WALL = "%s - %s %s";
	private static final String MESSAGE_FORMAT_FOR_USER = "%s %s";

	private PrintStream printStream;
	private TimerOnSeconds timerOnSeconds;

	public DefaultAppConsole(PrintStream printStream, TimerOnSeconds timerOnSeconds) {
		this.printStream = printStream;
		this.timerOnSeconds = timerOnSeconds;
	}
	
	@Override
	public void displayPostForWall(Post post) {
		printStream.println(String.format(MESSAGE_FORMAT_FOR_WALL, post.getUserName(), post.getMessage(),
				timerOnSeconds.formatTimeDifference(post.getPublishDate())));
	}
	
	@Override
	public void displayPostForUser(Post post) {
		printStream.println(String.format(MESSAGE_FORMAT_FOR_USER, post.getMessage(),
				timerOnSeconds.formatTimeDifference(post.getPublishDate())));
	}
	
	@Override
	public void displayMessage(String message) {
		printStream.println(message);
	}
}
