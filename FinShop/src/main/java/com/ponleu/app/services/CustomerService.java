package com.ponleu.app.services;

import java.util.List;

import com.ponleu.app.entities.Customer;

public interface CustomerService {
	public List<Customer> search(String search, Integer limit);

}
