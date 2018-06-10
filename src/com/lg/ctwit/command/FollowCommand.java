package com.lg.ctwit.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;

import com.lg.ctwit.core.User;
import com.lg.ctwit.repository.Repository;

public class FollowCommand implements Command{
	private static final Pattern REGEX = Pattern.compile("^(\\S+) follows (\\S+)$");
	private Repository repository;

	public FollowCommand(Repository repository) {
		this.repository = repository;
	}

	public void execute(String commandLine) {
		Matcher matcher = REGEX.matcher(commandLine);
		matcher.find();
		String userName = matcher.group(1);
		String userNameToFollow = matcher.group(2);

		User currentUser = repository.getOrCreateUser(userName);
		User userToFollow = repository.getOrCreateUser(userNameToFollow);
		currentUser.addFollowing(userToFollow);
	}

	public boolean matches(String input) {
		if (StringUtils.isNotBlank(input)) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}