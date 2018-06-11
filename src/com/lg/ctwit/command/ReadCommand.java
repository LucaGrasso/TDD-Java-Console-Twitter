package com.lg.ctwit.command;

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

public class ReadCommand implements Command{
	private static final Pattern REGEX = Pattern.compile("^\\S+$");

	private Repository repository;
	private AppConsole postDisplayer;

	public ReadCommand(Repository repository, AppConsole postDisplayer) {
		this.repository = repository;
		this.postDisplayer = postDisplayer;
	}

	@Override
	public void execute(String commandLine) {
		User user = repository.getOrCreateUser(commandLine.trim());
		List<Post> posts = user.getPosts();
		// Sort posts.
		Collections.sort(posts, new Comparator<Post>() {
			@Override
			public int compare(Post p1, Post p2) {
				return p2.getPublishDate().compareTo(p1.getPublishDate());
			}
		});
		for (Post post : posts) {
			postDisplayer.displayPostForUser(post);
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
