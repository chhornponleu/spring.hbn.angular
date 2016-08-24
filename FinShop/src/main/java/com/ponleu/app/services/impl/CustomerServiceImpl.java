package com.ponleu.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.CustomerDao;
import com.ponleu.app.entities.Customer;
import com.ponleu.app.services.CustomerService;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	
	@Inject
	private CustomerDao customerDao;
	
	@Override
	public List<Customer> search(String search, Integer limit) {
		List<Customer> customers = new ArrayList<>();
		try {
			customers = customerDao.search(search, limit);
		}
		catch (HibernateException e) {
			LOGGER.error(e);
		}
		return customers;
	}

}
