package com.ponleu.app.services;

import java.io.Serializable;
import java.util.List;

import com.ponleu.app.entities.Category;

public interface CategoryService {
	public boolean save(Category category);
	public boolean update(Category category);
	public boolean delete(Serializable id);
	public boolean existsCategoryName(String categoryName);
	public List<Category> getAll();
}
