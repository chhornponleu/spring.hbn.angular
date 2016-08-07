package com.ponleu.app.apis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.dto.RestfulResponse;
import com.ponleu.app.dto.RestfulResponseHeader;
import com.ponleu.app.entities.Attribute;
import com.ponleu.app.services.AttributeService;



@RestController
@RequestMapping(value = "/apis/attributes")
public class AttributeApiController {

	@Inject
	AttributeService attributeService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RestfulResponse save(@RequestBody Attribute attribute) {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();
		header.setResult(attributeService.save(attribute));
		resp.setHeader(header);
		resp.setBody(attribute);
		return resp;
		
	}
	
	@RequestMapping(value = "/existsName/{attributeName}", method = RequestMethod.GET)
	public RestfulResponse existCategoryName(@PathVariable("attributeName") String attributeName) {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();
		header.setResult(attributeService.existsAttributeName(attributeName));
		resp.setHeader(header);
		return resp;
		
	}
	
}
