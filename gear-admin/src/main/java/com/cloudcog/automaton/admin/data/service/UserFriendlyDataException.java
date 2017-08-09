package com.cloudcog.automaton.admin.data.service;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * A data integraty violation exception containing a message intended to be
 * shown to the end user.
 */
public class UserFriendlyDataException extends DataIntegrityViolationException {
	private static final long serialVersionUID = 6652420252183553953L;

	public UserFriendlyDataException(String message) {
		super(message);
	}

}
