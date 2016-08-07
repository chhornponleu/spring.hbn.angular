package com.ponleu.app.apis;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.dto.RestfulResponse;
import com.ponleu.app.dto.RestfulResponseHeader;
import com.ponleu.app.entities.Product;
import com.ponleu.app.services.FileService;
import com.ponleu.app.services.ProductService;

@RestController
@RequestMapping(value = "/apis/products")
public class ProductApiController {

	@Inject
	private ProductService productService;

	@Inject
	private FileService scFileService;
	
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public RestfulResponse getById(@PathVariable("productId") Long productId) {
		RestfulResponse resp = new RestfulResponse();
		
		Product product = productService.get(productId);
		
		resp.setHeader(new RestfulResponseHeader());
		resp.setBody(product);
		
		return resp;
	}
	

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public RestfulResponse getPaging(@RequestBody Product product) {

		String imageName = null;
		if (product.getImageName() != null && !product.getImageName().isEmpty()) {
			imageName = System.currentTimeMillis() + "."+ scFileService.getFileExtension(product.getImageName());
			product.setImageName(imageName); 
		}
		
		productService.save(product);
		
		if(imageName != null) {
			scFileService.saveProductImge(imageName, product.getImageFileBase64());
		}

		Map<String, Object> result = new HashMap<>();
		result.put("productId", product.getId());
		result.put("imageName", imageName);

		return new RestfulResponse(new RestfulResponseHeader(), product.getId());
	}

	@RequestMapping(value = "/paging", method = RequestMethod.POST)
	public RestfulResponse getPaging(@RequestBody ProductPagingRequest pagingRequest) {
		return new RestfulResponse(new RestfulResponseHeader(), productService.getPagination(pagingRequest));
	}
}
