package com.ponleu.app.daos;

import java.util.List;

import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.entities.Product;
import com.ponleu.app.generics.GenericDao;

public interface ProductDao extends GenericDao<Product> {
	public Long count(Product search);
	public List<Product> getPagination(ProductPagingRequest pagingRequest);
	public Integer decrement(Long productId, Double interval);
	public Integer increment(Long productId, Double interval);
}
