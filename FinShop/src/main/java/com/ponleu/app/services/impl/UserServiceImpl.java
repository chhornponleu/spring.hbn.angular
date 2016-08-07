package com.ponleu.app.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.UserDao;
import com.ponleu.app.entities.User;
import com.ponleu.app.services.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Inject
	UserDao userDao;

	@Override
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

}
