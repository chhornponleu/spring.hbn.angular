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
@Table(name = "catgories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Integer id;

	@Column(name = "CATEGORY_NAME", unique = true, nullable = false)
	private String categoryName;

	@Column(name = "CATEGORY_DESC")
	private String categoryDesc;

	@Column(name = "STATUS")
	private String status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Product> products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryDesc == null) ? 0 : categoryDesc.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Category other = (Category) obj;
		if (categoryDesc == null) {
			if (other.categoryDesc != null)
				return false;
		} else if (!categoryDesc.equals(other.categoryDesc))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
