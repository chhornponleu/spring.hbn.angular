package com.ponleu.app.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetail {

	@Id
	private OrderDetailId id;
	
	@Column(name="ATTRIBUTE_PRICE", nullable = false)
	private BigDecimal attributePrice;
	
	@Column(name="ATTRIBUTE_QTY", nullable = false)
	private Double  attributeQty;

	public OrderDetailId getId() {
		return id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	public BigDecimal getAttributePrice() {
		return attributePrice;
	}

	public void setAttributePrice(BigDecimal attributePrice) {
		this.attributePrice = attributePrice;
	}

	public Double getAttributeQty() {
		return attributeQty;
	}

	public void setAttributeQty(Double attributeQty) {
		this.attributeQty = attributeQty;
	}
	
}
