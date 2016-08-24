package com.ponleu.app.apis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.dto.RestfulResponse;
import com.ponleu.app.dto.RestfulResponseHeader;
import com.ponleu.app.entities.Order;
import com.ponleu.app.services.OrderService;

@RestController
@RequestMapping(value = "/apis/orders")
public class OrderApiController {

	@Inject
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.POST)
	public RestfulResponse getSearch(@RequestBody Order order) {
		boolean result = orderService.save(order);
		RestfulResponse resp = new RestfulResponse();
		resp.setBody(order.getId());
		resp.setHeader(new RestfulResponseHeader(result, null, null, null)); 
		return resp;
	};
	
	@RequestMapping(value="/{orderId}", method = RequestMethod.GET)
	public RestfulResponse getById(@PathVariable Long orderId) {
		Order order= orderService.getDetailById(orderId);
		RestfulResponse resp = new RestfulResponse();
		resp.setBody(order);
		resp.setHeader(new RestfulResponseHeader((order != null), null, null, null)); 
		return resp;
	};

	@RequestMapping(value = "/paging", method = RequestMethod.POST)
	public RestfulResponse getPaging(@RequestBody ProductPagingRequest pagingRequest) {
		return null; //new RestfulResponse(new RestfulResponseHeader(), orderService.getPagination(pagingRequest));
	}
}
