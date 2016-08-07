package com.ponleu.app.dto;

import com.ponleu.app.entities.Product;
import com.ponleu.app.generics.AbstrPagingRequest;

public class ProductPagingRequest extends AbstrPagingRequest<Product> {

	@Override
	public String toString() {
		return "ProductPagingRequest [getPage()=" + getPage() + ", getPageSize()=" + getPageSize() + ", getSearch()="
				+ getSearch() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
