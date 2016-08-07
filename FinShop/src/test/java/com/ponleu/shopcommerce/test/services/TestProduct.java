package com.ponleu.shopcommerce.test.services;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

import com.ponleu.app.entities.Product;
import com.ponleu.app.services.AttributeService;
import com.ponleu.app.services.CategoryService;
import com.ponleu.app.services.ProductService;
import com.ponleu.shopcommerce.test.context.AbstractContextTest;

public class TestProduct extends AbstractContextTest {
	
	@Inject
	ProductService productService;
	
	@Inject
	CategoryService categoryService;
	
	@Inject
	AttributeService attributeService;
	
	@Test
	public void get() {
		Product prod = new Product();
		prod.setProductDescription("desc");
		prod.setProductName("prod name");
		prod.setCreatedBy("ponleu");
		prod.setCreatedDate(new Date());
		prod.setCategory(categoryService.getAll().get(0));
		
		//Set<ProductAttributes> attr = new HashSet<>();
		productService.save(prod);
		System.out.println(prod.getId());
	}
	
}	
