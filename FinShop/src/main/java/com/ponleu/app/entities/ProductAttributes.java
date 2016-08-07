package com.ponleu.app.entities;

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

	public Double getQuanitty() {
		return quantity;
	}

	public void setQuanitty(Double quantity) {
		this.quantity = quantity;
	}

	public ProductAttributeId getId() {
		return id;
	}

	public void setId(ProductAttributeId id) {
		this.id = id;
	}

	public Boolean getIsSinglePrice() {
		return isSinglePrice;
	}

	public void setIsSinglePrice(Boolean isSinglePrice) {
		this.isSinglePrice = isSinglePrice;
	}

}
