package com.ponleu.app.daos.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ponleu.app.daos.UserDao;
import com.ponleu.app.entities.User;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	@Override
	public User getByUsername(String username) {
		Query query = getSession().createQuery("FROM User u LEFT JOIN FETCH u.userAccess ua WHERE ua.username = :username" );
		query.setString("username", username);
		return (User) query.uniqueResult();
		
	}
	
}
