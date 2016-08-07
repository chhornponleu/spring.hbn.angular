package com.ponleu.app.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.daos.CategoryDao;
import com.ponleu.app.entities.Category;
import com.ponleu.app.services.CategoryService;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	@Inject
	private CategoryDao categoryDao;

	@Override
	@Transactional(readOnly = false)
	public boolean save(Category category) {
		boolean result = true;
		try {
			category.setStatus(StatusEnum.STATUS_ACTIVE);
			categoryDao.save(category);
		} catch (HibernateException | PersistenceException e) {
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Category category) {
		boolean result = true;
		try {
			categoryDao.update(category);
		} catch (HibernateException | PersistenceException e) {
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Serializable id) {
		boolean result = true;
		try {
			categoryDao.deleteById(id);
		} catch (HibernateException | PersistenceException e) {
			result = false;
		}
		return result;
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public boolean existsCategoryName(String categoryName) {
		return categoryDao.getByCategoryName(categoryName) != null;
	}

}
