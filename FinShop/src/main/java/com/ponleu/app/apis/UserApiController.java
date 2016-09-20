package com.ponleu.app.apis;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.dto.RestfulResponse;
import com.ponleu.app.dto.RestfulResponseHeader;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

@RestController
@RequestMapping(value = "/apis/users")
public class UserApiController {

	@Inject
	private UserService userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public RestfulResponse update(@PathVariable("userId") Integer userId, @RequestBody User user) {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();

		User oldUser = userService.getByUserId(userId);

		if (oldUser == null) {
			header.setResult(false);
		} else {
			if (user.getUserAccess() != null) {
				if (user.getUserAccess().getPassword() != null) {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String password = user.getUserAccess().getPassword();
					String encPassword = encoder.encode(password);
					user.getUserAccess().setPassword(encPassword);
				}
				else {
					user.getUserAccess().setPassword(oldUser.getUserAccess().getPassword());
				}
				
			}
			header.setResult(userService.update(user));
		}

		resp.setHeader(header);
		return resp;

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public RestfulResponse getProfile(Principal principal) {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();
		resp.setHeader(header);
		resp.setBody(userService.getByUsername(principal.getName()));
		return resp;

	}

}
