package com.ponleu.app.services;

import java.util.List;

import com.ponleu.app.dto.OrderPagingRequest;
import com.ponleu.app.dto.OrderPagingResponse;
import com.ponleu.app.entities.Order;

public interface OrderService {
	public boolean save(Order order);
	public Order getDetailById(Long orderId);
	public OrderPagingResponse getPagination(OrderPagingRequest pagingRequest);
	public boolean setPaid(Long orderId);
	public List<Order> getUnPaidOrders();
}
