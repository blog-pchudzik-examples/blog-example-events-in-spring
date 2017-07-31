package com.pchudzik.blog.example.springevents.user.notification;

import com.pchudzik.blog.example.springevents.user.event.UserCreatedEvent;
import com.pchudzik.blog.example.springevents.user.event.UserPermissionAssigned;
import com.pchudzik.blog.example.springevents.user.event.UserPermissionRejected;
import com.pchudzik.blog.example.springevents.user.event.UserPermissionRequested;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Async
class EmailNotificationService {
	@EventListener
	public void handleUserCreatedAction(UserCreatedEvent event) {
		log.info("Email: User created {}", event.getUser());
	}

	@EventListener
	public void handlePermissionRequested(UserPermissionRequested event) {
		log.info("Email: User {} permission requested {}", event.getUser(), event.getPermission());
	}

	@EventListener
	public void handlePermissionAssigned(UserPermissionAssigned event) {
		log.info("Email: User {} permission assigned {}", event.getUser(), event.getPermission());
	}

	@EventListener
	public void handlePermissionRejected(UserPermissionRejected event) {
		log.info("Email: User {} permission rejected {}", event.getUser(), event.getPermission());
	}
}
