package com.lg.ctwit.tools;

import com.lg.ctwit.core.Post;

public interface AppConsole {

	/**
	 * Display the wall.
	 * 
	 * @param post
	 *            The post to be displayed.
	 */
	public void displayPostForWall(Post post);

	/**
	 * Display the user timeline.
	 * 
	 * @param post
	 *            The post to be displayed.
	 */
	public void displayPostForUser(Post post);

	/**
	 * Display the user message.
	 * 
	 * @param message
	 *            The message to be displayed.
	 */
	public void displayMessage(String message);

}