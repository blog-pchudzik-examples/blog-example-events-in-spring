package com.pchudzik.blog.example.springevents.user.event;

import com.pchudzik.blog.example.springevents.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreatedEvent implements UserEvent {
	private final User user;
}
