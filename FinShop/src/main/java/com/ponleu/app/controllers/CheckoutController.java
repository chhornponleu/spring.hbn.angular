package com.ponleu.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/checkout")
public class CheckoutController {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCheckout() {
		return "checkout.index";
	}
}
