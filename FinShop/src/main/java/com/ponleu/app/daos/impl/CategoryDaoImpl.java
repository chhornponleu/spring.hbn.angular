package com.ponleu.app.daos.impl;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ponleu.app.daos.CategoryDao;
import com.ponleu.app.entities.Category;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

	private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);

	private static final String HQL_GET_BY_CATEGORY_NAME = "FROM Category ca WHERE ca.categoryName = :categoryName";

	@Override
	public Category getByCategoryName(String categoryName) {
		Category category = null;
		try {
			Query<Category> query = this.getSession().createQuery(HQL_GET_BY_CATEGORY_NAME, Category.class);
			query.setParameter("categoryName", categoryName);
			category = query.getSingleResult();
		} catch (Exception e) {
			LOGGER.error("Error getting catetegory by name : " + categoryName, e);
		}
		return category;
	}

}
