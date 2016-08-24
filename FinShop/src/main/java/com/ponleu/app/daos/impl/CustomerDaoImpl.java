package com.ponleu.app.daos.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ponleu.app.daos.CustomerDao;
import com.ponleu.app.entities.Customer;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> search(String search, Integer limit) {
		String hql = "FROM Customer cu WHERE cu.customerName LIKE :search OR cu.contact LIKE :search OR cu.address LIKE :search";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("search", "%"+search+"%");
		if(limit != null) { 
			query.setMaxResults(limit);
		}
		return query.getResultList();
	}

}
