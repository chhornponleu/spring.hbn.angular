package com.ponleu.shopcommerce.test.dao;

import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.OrderDao;
import com.ponleu.app.dto.OrderPagingRequest;
import com.ponleu.app.entities.Order;
import com.ponleu.shopcommerce.test.context.AbstractContextTest;

public class TestOrderDao extends AbstractContextTest {
	
	@Inject
	private OrderDao orderDao;
	
	@Test
	@Transactional
	public void get() {
		Order order = orderDao.get(14L);
		Hibernate.initialize(order.getOrderDetails());
		printJson(order);
	}
	
	@Test
	@Transactional
	public void count() {
		Order order = new Order();
		order.setId(Long.parseLong("14"));
		printJson(orderDao.count(order));
	}
	
	@Test
	@Transactional
	public void paging() {
		OrderPagingRequest req = new OrderPagingRequest();
		Order order = new Order();
		order.setId(Long.parseLong("14"));
		req.setPage(1);
		req.setPageSize(5);
		req.setSearch(order);
		printJson(orderDao.getPagination(req));
	}
	
}	
