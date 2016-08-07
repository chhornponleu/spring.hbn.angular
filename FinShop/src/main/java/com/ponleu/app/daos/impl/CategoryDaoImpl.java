package com.ponleu.app.daos.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ponleu.app.daos.CategoryDao;
import com.ponleu.app.entities.Category;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {

	@Override
	public Category getByCategoryName(String categoryName) {
		Criteria cri = getSession().createCriteria(Category.class);
		cri.add(Restrictions.eq("categoryName", categoryName));
		return (Category) cri.uniqueResult();
	}

}
