package com.ponleu.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.entities.User;

public class UserDetailsAdapter implements UserDetails {

	private static final Logger LOGGER = Logger.getLogger(UserDetailsAdapter.class);

	private static final long serialVersionUID = 1L;

	private User user;

	public UserDetailsAdapter() {

	}

	public UserDetailsAdapter(User user) {

	}

	public User getUser() {
		return this.user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority authoritie = new SimpleGrantedAuthority(user.getUserAccess().getRole());
		authorities.add(authoritie);
		return authorities;
	}

	@Override
	public String getPassword() {
		String password = null;

		try {
			password = this.getUser().getUserAccess().getPassword();
		} catch (NullPointerException ex) {
			LOGGER.error("Error getting password", ex);
		}

		return password;
	}

	@Override
	public String getUsername() {
		String username = null;
		try {
			username = this.getUser().getUserAccess().getUsername();
		} catch (NullPointerException ex) {
			LOGGER.error("Error getting password", ex);
		}
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getUser().getUserAccess().getStatus().equals(StatusEnum.STATUS_ACTIVE);
	}

}
