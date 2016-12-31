package com.ponleu.app.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prodcuts_attributes")
public class ProductAttributes {

	@Id
	private ProductAttributeId id;

	@Column(name = "QUANTITY")
	private Double quantity;

	@Column(name = "IS_SINGLE_PRICE")
	private Boolean isSinglePrice;

	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;

	public ProductAttributeId getId() {
		return id;
	}

	public void setId(ProductAttributeId id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsSinglePrice() {
		return isSinglePrice;
	}

	public void setIsSinglePrice(Boolean isSinglePrice) {
		this.isSinglePrice = isSinglePrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
