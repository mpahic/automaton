package com.cloudcog.automaton.admin.data.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {
	private static final long serialVersionUID = 3680828483897152675L;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(unique = true)
	private String username;

	@NotNull
	@Size(min = 4, max = 255)
	private String password;

	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@NotNull
	@Size(min = 1, max = 255)
	private String surname;

	@NotNull
	@Size(min = 1, max = 255)
	private String email;

	@Size(min = 1, max = 255)
	private String phoneNumber;

	private boolean blocked;

	@NotNull
	@Size(min = 1, max = 255)
	private String role;

	private boolean locked = false;

	public User() {
		// An empty constructor is needed for all beans
	}

	public User(String username, String name, String surname, String password, String role, String email) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		Objects.requireNonNull(name);
		Objects.requireNonNull(surname);
		Objects.requireNonNull(email);
		Objects.requireNonNull(role);

		this.username = username;
		this.name = name;
		this.password = password;
		this.role = role;
		this.surname = surname;
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
