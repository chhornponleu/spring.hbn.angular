package com.ponleu.app.daos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.daos.ProductDao;
import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.entities.Product;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	@Override
	public Long count(Product search) {
		Criteria ctr = getSession().createCriteria(Product.class);

		if (search != null) {

			List<Criterion> criterions = new ArrayList<>();
			if (search.getId() != null && !search.getId().equals(0L)) {
				criterions.add(Restrictions.eq("id", search.getId()));
			}

			if (search.getProductName() != null) {
				criterions.add(Restrictions.like("productName", "%".concat(search.getProductName()).concat("%")));
			}
			if (search.getProductDescription() != null) {
				criterions.add(Restrictions.like("productDescription",
						"%".concat(search.getProductDescription()).concat("%")));
			}

			ctr.add(Restrictions.or(criterions.toArray(new Criterion[criterions.size()])));
		}
		ctr.add(Restrictions.eq("status", StatusEnum.STATUS_ACTIVE));
		ctr.setProjection(Projections.rowCount());

		return (Long) ctr.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getPagination(ProductPagingRequest pagingRequest) {
		Criteria ctr = getSession().createCriteria(Product.class);

		Product search = pagingRequest.getSearch();

		if (search != null) {

			List<Criterion> criterions = new ArrayList<>();
			if (search.getId() != null && !search.getId().equals(0L)) {
				criterions.add(Restrictions.eq("id", search.getId()));
			}

			if (search.getProductName() != null) {
				criterions.add(Restrictions.like("productName", "%".concat(search.getProductName()).concat("%")));
			}
			if (search.getProductDescription() != null) {
				criterions.add(Restrictions.like("productDescription",
						"%".concat(search.getProductDescription()).concat("%")));
			}

			ctr.add(Restrictions.or(criterions.toArray(new Criterion[criterions.size()])));
		}
		ctr.add(Restrictions.eq("status", StatusEnum.STATUS_ACTIVE));
		ctr.setFirstResult(this.calculateFirstResult(pagingRequest.getPage(), pagingRequest.getPageSize()));
		ctr.setMaxResults(pagingRequest.getPageSize());
		ctr.addOrder(Order.desc("createdDate"));
		return ctr.list();
	}
}
