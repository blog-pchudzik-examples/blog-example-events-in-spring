package com.pchudzik.blog.example.springevents.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
class UserRepository {
	private final Map<String, User> users = new HashMap<>();

	public void saveUser(User user) {
		users.put(user.getLogin(), user);
	}

	public User load(String userLogin) {
		return Optional
				.ofNullable(users.get(userLogin))
				.orElseThrow(() -> new NoSuchElementException("No user with login " + userLogin));
	}
}
