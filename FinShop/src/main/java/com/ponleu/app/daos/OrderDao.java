package com.ponleu.app.daos;

import java.util.List;

import com.ponleu.app.dto.OrderPagingRequest;
import com.ponleu.app.entities.Order;
import com.ponleu.app.generics.GenericDao;

public interface OrderDao extends GenericDao<Order> {
	public Long count(Order search);
	public List<Order> getPagination(OrderPagingRequest pagingRequest);
}
