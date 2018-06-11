package com.lg.ctwit.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;


import com.lg.ctwit.core.Post;
import com.lg.ctwit.core.User;
import com.lg.ctwit.repository.Repository;
import com.lg.ctwit.tools.AppConsole;

public class WallCommand implements Command{
	private static final Pattern REGEX = Pattern.compile("^(\\S+) wall$");

	private Repository repository;
	private AppConsole postDisplayer;

	public WallCommand(Repository repository, AppConsole postDisplayer) {
		this.repository = repository;
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		Matcher matcher = REGEX.matcher(commandLine);
		matcher.find();

		String userName = matcher.group(1);
		User user = repository.getOrCreateUser(userName);
		// Get user's posts.
		List<Post> posts = new ArrayList<Post>();
		posts.addAll(user.getPosts());
		// Add following users post.
		for (User following : user.getFollowingUsers()) {
			posts.addAll(following.getPosts());
		}
		// Sort posts.
		Collections.sort(posts, new Comparator<Post>() {
			@Override
			public int compare(Post p1, Post p2) {
				return p2.getPublishDate().compareTo(p1.getPublishDate());
			}
		});
		for (Post post : posts) {
			postDisplayer.displayPostForWall(post);
		}
	}

	@Override
	public boolean matches(String input) {
		if (StringUtils.isNotBlank(input)) {
			Matcher matcher = REGEX.matcher(input);
			return matcher.find();
		}
		return false;
	}

}
