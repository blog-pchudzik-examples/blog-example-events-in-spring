package com.pchudzik.blog.example.springevents.user;

import com.pchudzik.blog.example.springevents.user.event.UserEvent;
import com.pchudzik.blog.example.springevents.user.event.UserPermissionAssigned;
import com.pchudzik.blog.example.springevents.user.event.UserPermissionRejected;
import com.pchudzik.blog.example.springevents.user.event.UserPermissionRequested;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@ToString(of = {"login", "name"})
public class User {
	@Getter
	private final String login;

	private final String name;
	private final Set<Permission> permissions = new HashSet<>();

	private final Set<Permission> pendingPermissions = new HashSet<>();

	public User(String login, String name, Permission permission) {
		this.login = login;
		this.name = name;
		this.permissions.add(permission);
	}

	public List<UserEvent> requestPermission(Permission permission) {
		pendingPermissions.add(permission);
		return Collections.singletonList(new UserPermissionRequested(this, permission));
	}

	public List<UserEvent> assignPermission(Permission permission) {
		pendingPermissions.remove(permission);
		permissions.add(permission);
		return Collections.singletonList(new UserPermissionAssigned(this, permission));
	}

	public List<UserEvent> rejectPermission(Permission permission) {
		pendingPermissions.remove(permission);
		permissions.remove(permission);
		return Collections.singletonList(new UserPermissionRejected(this, permission));
	}

	public Collection<Permission> getPendingPermissions() {
		return Collections.unmodifiableSet(pendingPermissions);
	}

	public Collection<Permission> getUserPermissions() {
		return Collections.unmodifiableSet(permissions);
	}
}
