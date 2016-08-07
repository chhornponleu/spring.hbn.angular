package com.ponleu.app.daos;

import com.ponleu.app.entities.Category;
import com.ponleu.app.generics.GenericDao;

public interface CategoryDao extends GenericDao<Category> {
	public Category getByCategoryName(String categoryName); 
}
