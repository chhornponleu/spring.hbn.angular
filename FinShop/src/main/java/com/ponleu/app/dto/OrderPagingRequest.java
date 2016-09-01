package com.ponleu.app.dto;

import com.ponleu.app.entities.Order;
import com.ponleu.app.generics.AbstrPagingRequest;

public class OrderPagingRequest extends AbstrPagingRequest<Order> {

	@Override
	public String toString() {
		return "OrderPagingRequest [getPage()=" + getPage() + ", getPageSize()=" + getPageSize() + ", getSearch()="
				+ getSearch() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
