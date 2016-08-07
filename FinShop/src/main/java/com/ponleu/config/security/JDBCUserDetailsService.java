package com.ponleu.config.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

public class JDBCUserDetailsService implements UserDetailsService {

	@Inject
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		} else if (user.getUserAccess() != null) {
			throw new UsernameNotFoundException("User is not permitted");
		} else if (user.getUserAccess().getStatus().equalsIgnoreCase(StatusEnum.STATUS_ACTIVE)) {
			throw new UsernameNotFoundException("User is not active");
		}

		return new UserDetailsAdapter(user);
	}

}
