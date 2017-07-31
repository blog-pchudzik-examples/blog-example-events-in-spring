package com.pchudzik.blog.example.springevents.user.event;

import com.pchudzik.blog.example.springevents.user.Permission;
import com.pchudzik.blog.example.springevents.user.User;

public class UserPermissionRejected extends UserPermissionEvent {
	public UserPermissionRejected(User user, Permission permission) {
		super(user, permission);
	}
}
