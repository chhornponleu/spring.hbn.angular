package com.ponleu.app.daos.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ponleu.app.daos.AttributeDao;
import com.ponleu.app.entities.Attribute;
import com.ponleu.app.generics.GenericDaoImpl;

@Repository
public class AttributeDaoImpl extends GenericDaoImpl<Attribute> implements AttributeDao {

	@Override
	public Attribute getByAttributeName(String attributeName) {
		Criteria cri = getSession().createCriteria(Attribute.class);
		cri.add(Restrictions.eq("attributeName", attributeName));
		return (Attribute) cri.uniqueResult();
	}

}
