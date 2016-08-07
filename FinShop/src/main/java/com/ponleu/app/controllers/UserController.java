package com.ponleu.app.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login (HttpServletResponse response) {
		return "user/login";
	}
}
