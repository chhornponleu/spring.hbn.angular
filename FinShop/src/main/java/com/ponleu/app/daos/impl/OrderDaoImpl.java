package com.ponleu.app.daos.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.daos.OrderDao;
import com.ponleu.app.dto.OrderPagingRequest;
import com.ponleu.app.entities.Customer;
import com.ponleu.app.entities.Order;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
	private static String HQL = "FROM Order od WHERE (od.status = :statusActive OR od.status = :statusPending) ";
	private static String HQL_SEARCH = " AND (od.id = :orderId OR od.customer.contact LIKE :contact "
			+ "OR od.customer.customerName LIKE :customerName OR od.customer.address LIKE :address)";

	@Override
	public Long count(Order search) {
		String hql = "SELECT count(od) " + HQL;
		hql += HQL_SEARCH;

		Query<Long> query = this.getSession().createQuery(hql, Long.class);
		Customer customer = null;
		if (search == null) {
			search = new Order();
		}
		else {
			customer = search.getCustomer();
			if (customer == null) {
				if(search.getId() != null) {
					customer = new Customer("", "", "");
				}
				else {
					customer = new Customer("%", "%", "%");
				}
			} else {
				customer.setContact(customer.getContact() == null ? "" : "%" + customer.getContact() + "%");
				customer.setAddress(customer.getAddress() == null ? "" : "%" + customer.getAddress() + "%");
				customer.setCustomerName(customer.getCustomerName() == null ? "" : "%" + customer.getCustomerName() + "%");
			}
		}
		query.setParameter("statusActive", StatusEnum.STATUS_ACTIVE);
		query.setParameter("statusPending", StatusEnum.STATUS_PENDING);
		query.setParameter("orderId", search.getId());
		query.setParameter("customerName", customer.getCustomerName());
		query.setParameter("contact", customer.getContact());
		query.setParameter("address", customer.getAddress());

		return (Long) query.getSingleResult();
	}

	@Override
	public List<Order> getPagination(OrderPagingRequest pagingRequest) {
		Order search = pagingRequest.getSearch();

		String hql = HQL;
		hql += (HQL_SEARCH + " ORDER BY od.id DESC");
		System.out.println(hql);

		Query<Order> query = this.getSession().createQuery(hql, Order.class);
		query.setMaxResults(pagingRequest.getPageSize());
		query.setFirstResult(calculateFirstResult(pagingRequest.getPage(), pagingRequest.getPageSize()));

		if (search == null) {
			search = new Order();
		}
		Customer customer = search.getCustomer();
		if (customer == null) {
			if(search.getId() != null) {
				customer = new Customer("", "", "");
			}
			else {
				customer = new Customer("%", "%", "%");
			}
		} else {
			customer.setContact(customer.getContact() == null ? "" : "%" + customer.getContact() + "%");
			customer.setAddress(customer.getAddress() == null ? "" : "%" + customer.getAddress() + "%");
			customer.setCustomerName(customer.getCustomerName() == null ? "" : "%" + customer.getCustomerName() + "%");
		}
		query.setParameter("statusActive", StatusEnum.STATUS_ACTIVE);
		query.setParameter("statusPending", StatusEnum.STATUS_PENDING);
		query.setParameter("orderId", search.getId());
		query.setParameter("customerName", customer.getCustomerName());
		query.setParameter("contact", customer.getContact());
		query.setParameter("address", customer.getAddress());
		return query.getResultList();
	}

}
