package com.ponleu.app.services.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.UserDao;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Inject
	UserDao userDao;

	@Override
	public User getByUsername(String username) {
		User user = null;
		try {
			user = userDao.getByUsername(username);
		} catch (HibernateException e) {
			logger.error("Error getting user " + username, e);
		}
		return user;
	}

}
