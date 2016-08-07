package com.ponleu.app.services;

import java.io.Serializable;

import com.ponleu.app.dto.ProductPagingRequest;
import com.ponleu.app.dto.ProductPagingResponse;
import com.ponleu.app.entities.Product;

public interface ProductService {

	public Product get(Long productId);

	public boolean save(Product product);

	public boolean delete(Serializable id);

	public boolean update(Product product);

	public ProductPagingResponse getPagination(ProductPagingRequest pagingRequest);

}
