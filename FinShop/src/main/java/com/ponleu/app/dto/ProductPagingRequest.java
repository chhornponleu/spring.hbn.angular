package com.ponleu.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ponleu.app.entities.Product;
import com.ponleu.app.generics.AbstrPagingRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPagingRequest extends AbstrPagingRequest<Product> {

	@Override
	public String toString() {
		return "ProductPagingRequest [getPage()=" + getPage() + ", getPageSize()=" + getPageSize() + ", getSearch()="
				+ getSearch() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}
