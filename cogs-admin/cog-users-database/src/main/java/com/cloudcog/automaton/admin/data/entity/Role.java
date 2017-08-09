package com.cloudcog.automaton.admin.data.entity;

public class Role {
	public static final String SUPERADMIN = "superadmin";
	public static final String ADMIN = "admin";
	public static final String MODERATOR = "moderator";
	public static final String EDITOR = "editor";
	public static final String VIEWER = "viewer";

	private Role() {
		// Static methods and fields only
	}

	public static String[] getAllRoles() {
		return new String[] { SUPERADMIN, ADMIN, MODERATOR, EDITOR, VIEWER };
	}

}
