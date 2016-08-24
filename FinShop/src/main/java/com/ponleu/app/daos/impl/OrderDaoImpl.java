package com.ponleu.app.daos.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.daos.OrderDao;
import com.ponleu.app.dto.OrderPagingRequest;
import com.ponleu.app.entities.Customer;
import com.ponleu.app.entities.Order;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

	@Override
	public Long count(Order search) {
		String hql = "SELECT count(od) " + this.buildOrderSearchHQL(search);
		System.out.println(hql);
		Query query = this.getSession().createQuery(hql);
		query.setParameter("status", StatusEnum.STATUS_DEACTIVE);
		if (search != null) {
			Customer customer = search.getCustomer();
			if (hql.indexOf(":orderId") != -1) {
				query.setParameter("orderId", search.getId());
			}
			if (hql.indexOf(":customerName") != -1) {
				query.setParameter("customerName", "%" + customer.getCustomerName() + "%");
			}
			if (hql.indexOf(":contact") != -1) {
				query.setParameter("contact", "%" + customer.getContact() + "%");
			}
			if (hql.indexOf(":address") != -1) {
				query.setParameter("address", "%" + customer.getAddress() + "%");
			}
		}
		return (Long) query.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> getPagination(OrderPagingRequest pagingRequest) {
		Order search = pagingRequest.getSearch();

		String hql = this.buildOrderSearchHQL(search);
		System.out.println(hql);

		Query query = this.getSession().createQuery(hql);
		query.setParameter("status", StatusEnum.STATUS_DEACTIVE);
		query.setMaxResults(pagingRequest.getPageSize());
		query.setFirstResult(calculateFirstResult(pagingRequest.getPage(), pagingRequest.getPageSize()));

		if (search != null) {
			Customer customer = search.getCustomer();
			if (hql.indexOf(":orderId") != -1) {
				query.setParameter("orderId", search.getId());
			}
			if (hql.indexOf(":customerName") != -1) {
				query.setParameter("customerName", "%" + customer.getCustomerName() + "%");
			}
			if (hql.indexOf(":contact") != -1) {
				query.setParameter("contact", "%" + customer.getContact() + "%");
			}
			if (hql.indexOf(":address") != -1) {
				query.setParameter("address", "%" + customer.getAddress() + "%");
			}
		}

		return query.getResultList();
	}

	/**
	 * Build Order Search HQL
	 * 
	 * @param order
	 * @return String started with "FROM ...."
	 */
	public String buildOrderSearchHQL(Order order) {
		String hql = "FROM Order od WHERE od.status IS NOT :status";

		if (order == null) {
			return hql;
		}

		if (order.getId() != null) {
			hql += " AND od.id = :orderId";
		}

		Customer customer = order.getCustomer();
		if (customer != null) {
			if (customer.getCustomerName() != null) {
				hql += " OR od.customer.customerName LIKE :customerName";
			}
			if (customer.getContact() != null) {
				hql += " OR od.customer.contact LIKE :contact";
			}
			if (customer.getAddress() != null) {
				hql += " OR od.customer.address LIKE :address";
			}
		}

		return hql;
	}

}
