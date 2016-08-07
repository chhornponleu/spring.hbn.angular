package com.ponleu.app.daos;

import com.ponleu.app.entities.Attribute;
import com.ponleu.app.generics.GenericDao;

public interface AttributeDao extends GenericDao<Attribute> {
	public Attribute getByAttributeName(String attributeName);
}
