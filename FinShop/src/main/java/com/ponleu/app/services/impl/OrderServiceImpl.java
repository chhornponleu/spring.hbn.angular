package com.ponleu.app.services.impl;

import java.util.Date;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.daos.OrderDao;
import com.ponleu.app.dto.OrderPagingRequest;
import com.ponleu.app.dto.OrderPagingResponse;
import com.ponleu.app.entities.Order;
import com.ponleu.app.entities.OrderDetail;
import com.ponleu.app.services.OrderService;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
	
	@Inject
	private OrderDao orderDao;
	
	@Override
	@Transactional(readOnly = false)
	public boolean save(Order order) {
		boolean result = true;
		try {
			for (OrderDetail od : order.getOrderDetails()) {
				od.getId().setOrder(order);
			}
			order.setOrderDate(new Date());
			order.setStatus(StatusEnum.STATUS_PENDING);
			orderDao.save(order);
		}
		catch(HibernateException | NullPointerException e ) {
			LOGGER.error("Error saving product : " + order.toString(), e);
			result = false;
		}
		return result;
	}

	@Override
	public Order getDetailById(Long orderId) {
		Order order = null;
		try {
			order = orderDao.get(orderId);
			Hibernate.initialize(order.getOrderDetails());
		}
		catch(HibernateException e) {
			order = null;
		}
		return order;
	}

	@Override
	public OrderPagingResponse getPagination(OrderPagingRequest pagingRequest) {
		return null;
	}

}
