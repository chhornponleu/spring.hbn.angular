package com.ponleu.shopcommerce.test.dao;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.UserDao;
import com.ponleu.shopcommerce.test.context.AbstractContextTest;

public class TestUserDao extends AbstractContextTest {
	
	@Inject
	private UserDao userDao;
	
	@Test
	@Transactional
	public void get() {
		printJson(userDao.get(1));
	}
	
}	
