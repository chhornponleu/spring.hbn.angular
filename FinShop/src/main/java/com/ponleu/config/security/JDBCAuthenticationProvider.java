package com.ponleu.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.commons.utils.SecurityUtil;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

public class JDBCAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private UserService userService;

	private BCryptPasswordEncoder encoder;

	public JDBCAuthenticationProvider() {
		encoder = new BCryptPasswordEncoder();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = SecurityUtil.retrieveUserName(authentication);
		String password = SecurityUtil.retrievePassword(authentication);

		User user = this.userService.getByUsername(username);

		if (user == null) {
			throw new BadCredentialsException("User not found");
		} else if (user.getUserAccess() == null) {
			throw new BadCredentialsException("User access has not been granted");
		} else if (!encoder.matches(password, user.getUserAccess().getPassword())) {
			throw new BadCredentialsException("Invalid username and password");
		} else if (!user.getUserAccess().getStatus().equalsIgnoreCase(StatusEnum.STATUS_ACTIVE)) {
			throw new BadCredentialsException("User is not active");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getUserAccess().getRole()));

		AuthenticationAdapter auth = new AuthenticationAdapter(username, password, authorities, user);

		return auth;
	}

	@Override
	public boolean supports(Class<? extends Object> clazz) {
		return clazz.isAssignableFrom(AuthenticationAdapter.class);
	}
}
