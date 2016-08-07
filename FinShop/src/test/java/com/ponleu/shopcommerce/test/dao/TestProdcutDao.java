package com.ponleu.shopcommerce.test.dao;

import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.ProductDao;
import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.entities.Product;
import com.ponleu.shopcommerce.test.context.AbstractContextTest;

public class TestProdcutDao extends AbstractContextTest {
	
	@Inject
	ProductDao productDao;
	
	@Test
	@Transactional
	public void get() {
		ProductPagingRequest req = new ProductPagingRequest();
		req.setPage(1);
		req.setPageSize(20);
		req.setSearch(null);
		
		printJson(productDao.getPagination(req));
	}
	
	
	@Test
	@Transactional
	public void get1() {
		Product pro = productDao.get(3L);
		Hibernate.initialize(pro.getAttributes());
		printJson(pro);
	}
}	
