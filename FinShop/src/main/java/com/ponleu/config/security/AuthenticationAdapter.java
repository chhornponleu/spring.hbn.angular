package com.ponleu.config.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.ponleu.app.entities.User;
import com.ponleu.app.entities.UserAccess;

public class AuthenticationAdapter extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 1L;

	private User user;

	public AuthenticationAdapter(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public AuthenticationAdapter(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, User user) {
		super(principal, credentials, authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAccess getUserAccess() {
		return this.getUser().getUserAccess();
	}

}
