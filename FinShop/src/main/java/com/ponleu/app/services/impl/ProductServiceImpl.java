package com.ponleu.app.services.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ponleu.app.commons.StatusEnum;
import com.ponleu.app.commons.utils.SecurityUtil;
import com.ponleu.app.daos.ProductDao;
import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.dto.ProductPagingResponse;
import com.ponleu.app.entities.Product;
import com.ponleu.app.entities.ProductAttributes;
import com.ponleu.app.services.ProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Inject
	private ProductDao productDao;

	@Override
	@Transactional(readOnly = false)
	public boolean save(Product product) {

		boolean result = true;

		try {
			BigDecimal totalPrice = BigDecimal.ZERO;
			for (ProductAttributes pa : product.getAttributes()) {
				pa.getId().setProduct(product);
				
				if(pa.getUnitPrice() != null && pa.getQuantity() != null) {
					totalPrice = totalPrice.add(pa.getUnitPrice().multiply(BigDecimal.valueOf(pa.getQuantity()))); 					
				}
			}
			product.setTotalPrice(totalPrice);
			product.setCreatedDate(new Date());
			product.setStatus(StatusEnum.STATUS_ACTIVE);
			product.setCreatedBy(SecurityUtil.getCurrentUserName());
			productDao.save(product);
			logger.info("Product ID: " + product.getId() + " has been saved successfully");
		} catch (HibernateException e) {
			logger.error("Cannot save product " + product.toString(), e);
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Serializable id) {
		boolean result = true;
		try {
			productDao.deleteById(id);
		} catch (HibernateException e) {
			logger.error("Cannot delete product ID: " + id, e);
			result = false;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean update(Product product) {
		boolean result = true;
		try {
			productDao.update(product);
		} catch (HibernateException e) {
			logger.error("Cannot delete update product: " + product.toString(), e);
			result = false;
		}
		return result;
	}

	@Override
	public ProductPagingResponse getPagination(ProductPagingRequest pagingRequest) {
		List<Product> products = new ArrayList<>();
		Long total = 0L;
		try {

			total = productDao.count(pagingRequest.getSearch());
			if (total > 0L) {
				products = productDao.getPagination(pagingRequest);
			}
		} catch (HibernateException e) {
			logger.error("Cannot paginate product: " + pagingRequest.toString(), e);
		}

		ProductPagingResponse response = new ProductPagingResponse();
		response.setData(products);
		response.setTotal(total);
		response.setPage(pagingRequest.getPage());
		response.setPageSize(pagingRequest.getPageSize());

		return response;
	}

	@Override
	public Product get(Long productId) {
		Product prod = productDao.get(productId);
		if(prod != null) {
			Hibernate.initialize(prod.getAttributes()); 
		}
		return prod;
	}

}
