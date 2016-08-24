package com.ponleu.app.apis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.dto.RestfulResponse;
import com.ponleu.app.dto.RestfulResponseHeader;
import com.ponleu.app.services.CustomerService;

@RestController
@RequestMapping(value = "/apis/customers")
public class CustomerApiController {

	private static final int DEFAULT_SEARCH_LIMIT = 10;
	
	@Inject
	private CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public RestfulResponse getSearch(@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "limit", required = false) Integer limit) {
		if(search == null ) {search = "";}
		if(limit != null && limit.equals(0)) {limit = DEFAULT_SEARCH_LIMIT;}
		
		RestfulResponse resp = new RestfulResponse();
		
		resp.setBody(customerService.search(search, limit));
		resp.setHeader(new RestfulResponseHeader());
		return resp;
	};

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public RestfulResponse getById(@PathVariable("customerId") Long customerId) {
		RestfulResponse resp = new RestfulResponse();

		return resp;
	}

}
