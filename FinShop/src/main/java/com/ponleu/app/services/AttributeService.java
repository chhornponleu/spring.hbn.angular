package com.ponleu.app.services;

import java.io.Serializable;
import java.util.List;

import com.ponleu.app.entities.Attribute;

public interface AttributeService {

	public boolean save(Attribute attribute);

	public boolean delete(Serializable id);

	public boolean update(Attribute attribute);
	
	public boolean existsAttributeName(String attributeName);
	
	public List<Attribute> getAll();

}