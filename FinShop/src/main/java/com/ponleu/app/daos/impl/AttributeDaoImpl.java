package com.ponleu.app.daos.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.ponleu.app.daos.AttributeDao;
import com.ponleu.app.entities.Attribute;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class AttributeDaoImpl extends GenericDaoImpl<Attribute> implements AttributeDao {
	private static final Logger LOGGER = Logger.getLogger(AttributeDaoImpl.class);
	private static final String HQL_GET_BY_ATTRIBUTE_NAME = "FROM Attribute at WHERE at.attributeName = :attributeName";

	@Override
	public Attribute getByAttributeName(String attributeName) {
		Attribute attr = null; 
		try {
			Query<Attribute> query = this.getSession().createQuery(HQL_GET_BY_ATTRIBUTE_NAME, Attribute.class);
			query.setParameter("attributeName", attributeName);
			attr = query.getSingleResult(); 
		}
		catch(NoResultException e) {
			LOGGER.error("Error getting attribute by name: " + attributeName, e);
		}
		return attr;
	}

}
