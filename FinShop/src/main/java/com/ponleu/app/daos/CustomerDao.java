package com.ponleu.app.daos;

import java.util.List;

import com.ponleu.app.entities.Customer;
import com.ponleu.app.generics.GenericDao;

public interface CustomerDao extends GenericDao<Customer> {
	public List<Customer>  search(String search, Integer limit); 
}
