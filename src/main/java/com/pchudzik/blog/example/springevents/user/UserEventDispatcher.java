package com.pchudzik.blog.example.springevents.user;

import com.pchudzik.blog.example.springevents.user.event.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserEventDispatcher {
	private final ApplicationEventPublisher eventPublisher;

	public void dispatch(UserEvent userEvent) {
		eventPublisher.publishEvent(userEvent);
	}
}
