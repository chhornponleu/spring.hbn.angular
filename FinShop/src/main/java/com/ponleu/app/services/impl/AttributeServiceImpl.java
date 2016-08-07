package com.ponleu.app.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.daos.AttributeDao;
import com.ponleu.app.entities.Attribute;
import com.ponleu.app.services.AttributeService;

@Service
@Transactional(readOnly = true)
public class AttributeServiceImpl implements AttributeService {

	@Inject
	AttributeDao attributeDao;

	@Override
	@Transactional(readOnly = false)
	public boolean save(Attribute attribute) {
		boolean result = true;
		try {
			attributeDao.save(attribute);
		} catch (HibernateException | PersistenceException e) {
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Serializable id) {
		boolean result = true;
		try {
			attributeDao.deleteById(id);
		} catch (HibernateException | PersistenceException e) {
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Attribute attribute) {
		boolean result = true;
		try {
			attributeDao.update(attribute);
		} catch (HibernateException | PersistenceException e) {
			result = false;
		}
		return result;

	}
	
	@Override
	public boolean existsAttributeName(String attributeName) {
		boolean result = true;
		
		try {
			result = (attributeDao.getByAttributeName(attributeName) != null);
		}
		catch(HibernateException | PersistenceException e) {
			result = false;
		}
		
		return result;
	}

	@Override
	public List<Attribute> getAll() {
		return attributeDao.getAll();
	}

	

}
