package com.ponleu.app.apis;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.dto.RestfulResponse;
import com.ponleu.app.dto.RestfulResponseHeader;
import com.ponleu.app.entities.Category;
import com.ponleu.app.services.CategoryService;

@RestController
@RequestMapping(value = "/apis/categories")
public class CategoryApiController {
	
	@Inject
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public RestfulResponse getAll() {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();
		resp.setHeader(header);
		resp.setBody(categoryService.getAll());
		return resp;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public RestfulResponse save(@RequestBody Category category) {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();
		header.setResult(categoryService.save(category));
		resp.setHeader(header);
		resp.setBody(category);
		return resp;
		
	}
	
	@RequestMapping(value = "/hasName/{categoryName}", method = RequestMethod.GET)
	public RestfulResponse existCategoryName(@PathVariable("categoryName") String categoryName) {
		RestfulResponse resp = new RestfulResponse();
		RestfulResponseHeader header = new RestfulResponseHeader();
		
		header.setResult(categoryService.existsCategoryName(categoryName));
		resp.setHeader(header);
		return resp;
		
	}
	
}
