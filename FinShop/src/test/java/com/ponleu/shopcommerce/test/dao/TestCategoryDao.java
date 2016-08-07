package com.ponleu.shopcommerce.test.dao;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.daos.CategoryDao;
import com.ponleu.app.entities.Category;
import com.ponleu.shopcommerce.test.context.AbstractContextTest;

public class TestCategoryDao extends AbstractContextTest {
	
	@Inject
	private CategoryDao categoryDao;
	
	@Test
	@Transactional(readOnly = false)
	public void save() {
		Category cat  = new Category();
		cat.setCategoryName("Ear Ring");
		cat.setStatus(StatusEnum.STATUS_ACTIVE);
		printJson(categoryDao.get(1));
	}
	
	@Test
	@Transactional
	public void get() {
		printJson(categoryDao.get(1));
	}
	
	@Test
	@Transactional
	public void getall() {
		printJson(categoryDao.getAll());
	}
}	
