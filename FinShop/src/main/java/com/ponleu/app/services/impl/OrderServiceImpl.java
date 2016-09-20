package com.ponleu.app.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		} catch (HibernateException | NullPointerException e) {
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
		} catch (HibernateException e) {
			order = null;
		}
		return order;
	}

	@Override
	public OrderPagingResponse getPagination(OrderPagingRequest pagingRequest) {
		List<Order> orders = new ArrayList<>();
		Long total = 0L;
		try {
			total = orderDao.count(pagingRequest.getSearch());
			if (total > 0L) {
				orders = orderDao.getPagination(pagingRequest);
			}
		} catch (HibernateException e) {
			LOGGER.error("Cannot paginate order: " + pagingRequest.toString(), e);
		}
		OrderPagingResponse resp = new OrderPagingResponse();
		resp.setData(orders);
		resp.setTotal(total);
		resp.setPage(pagingRequest.getPage());
		resp.setPageSize(pagingRequest.getPageSize());

		return resp;

	}

	@Override
	@Transactional(readOnly = false)
	public boolean setPaid(Long orderId) {
		boolean result = true;
		try {
			Order order = orderDao.get(orderId);
			if (order == null) {
				result = false;
			} else {
				order.setStatus(StatusEnum.STATUS_ACTIVE);
				BigDecimal discount = order.getDiscountAmount();
				if (discount == null) {
					discount = BigDecimal.ZERO;
				}
				order.setPaidAmount(order.getTotalAmount().subtract(discount));
				orderDao.update(order);
			}
		} catch (HibernateException e) {
			result = false;
		}
		return result;
	}

	@Override
	public List<Order> getUnPaidOrders() {
		List<Order> orders = new ArrayList<>();
		try {
			orders = orderDao.getByStatus(StatusEnum.STATUS_PENDING);
		} catch (HibernateException e) {
			LOGGER.error("Cannot getting upaid order", e);
		}
		return orders;
	}

}
