package com.pchudzik.blog.example.springevents.user;

import com.pchudzik.blog.example.springevents.user.event.UserCreatedEvent;
import com.pchudzik.blog.example.springevents.user.event.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserEventDispatcher eventDispatcher;

	public void registerUser(String login, String name) {
		final User user = new User(login, name, Permission.USER);

		userRepository.saveUser(user);
		eventDispatcher.dispatch(new UserCreatedEvent(user));
	}

	public void requestPermission(String userLogin, Permission permission) {
		final User user = userRepository.load(userLogin);
		final List<UserEvent> events = user.requestPermission(permission);
		events.forEach(eventDispatcher::dispatch);
	}

	public void assignPermission(String userLogin, Permission permission) {
		final User user = userRepository.load(userLogin);
		final List<UserEvent> events = user.assignPermission(permission);
		events.forEach(eventDispatcher::dispatch);
	}

	public void rejectPermission(String userLogin, Permission permission) {
		final User user = userRepository.load(userLogin);
		final List<UserEvent> events = user.rejectPermission(permission);
		events.forEach(eventDispatcher::dispatch);
	}

	public User loadUser(String userLogin) {
		return userRepository.load(userLogin);
	}
}
