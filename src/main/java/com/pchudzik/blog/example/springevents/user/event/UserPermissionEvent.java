package com.pchudzik.blog.example.springevents.user.event;

import com.pchudzik.blog.example.springevents.user.Permission;
import com.pchudzik.blog.example.springevents.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
abstract class UserPermissionEvent implements UserEvent {
	private final User user;
	private final Permission permission;
}
