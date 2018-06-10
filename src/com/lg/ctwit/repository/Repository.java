package com.lg.ctwit.repository;

import com.lg.ctwit.core.User;

public interface Repository {
	/**
	 * Retrieves the specified user from the repository, if the user does not
	 * exist, this method will create it.
	 * 
	 * @param userName
	 *            The username Creation 
	 *            
	 *            
	 * @return The requested user.
	 * 
	 */
	public User getOrCreateUser(String userName);

}
