package com.cloudcog.automaton.admin.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BasicAccessControl implements AccessControl {
	private static final long serialVersionUID = -3687712904068196230L;

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public BasicAccessControl(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

    @Override
    public boolean signIn(String username, String password) {
		if (username != null && !username.isEmpty()) {
			UserDetails user = userDetailsService.loadUserByUsername(username);
			if (user != null && user.isEnabled() && passwordEncoder.matches(password, user.getPassword())) {

				CurrentUser.set(username);
				return true;
			}
		}
		return false;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
	public boolean isUserInRole(String role) {
		if ("admin".equals(role)) {
            // Only the "admin" user is in the "admin" role
            return getPrincipalName().equals("admin");
        }

        // All users are in all non-admin roles
        return true;
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

}
