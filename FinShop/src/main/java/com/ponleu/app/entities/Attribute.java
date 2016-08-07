package com.ponleu.app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "attributes")
public class Attribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTR_ID")
	private Integer id;

	@Column(name = "ATTR_NAME", length = 45, unique = true, nullable = false)
	private String attributeName;

	@Column(name = "ATTR_DESC", length = 255)
	private String attributeDesc;

	@Column(name = "STATUS", length = 10)
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	private Set<ProductAttributes> products;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.attribute")
	private Set<OrderDetail> orderDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeDesc() {
		return attributeDesc;
	}

	public void setAttributeDesc(String attributeDesc) {
		this.attributeDesc = attributeDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<ProductAttributes> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductAttributes> products) {
		this.products = products;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeDesc == null) ? 0 : attributeDesc.hashCode());
		result = prime * result + ((attributeName == null) ? 0 : attributeName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (attributeDesc == null) {
			if (other.attributeDesc != null)
				return false;
		} else if (!attributeDesc.equals(other.attributeDesc))
			return false;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
