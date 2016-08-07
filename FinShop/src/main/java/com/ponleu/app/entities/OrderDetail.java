package com.ponleu.app.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	private OrderDetailId id;
	
	@Column(name="ATTRIBUTE_PRICE")
	private BigDecimal attributePrice;
}
