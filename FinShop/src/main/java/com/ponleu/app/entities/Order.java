package com.ponleu.app.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "ORDER_DATE")
	private Date orderDate;

	@Column(name = "PICKUP_DATE")
	private Date pickupDate;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name = "PAID_AMOUNT")
	private BigDecimal paidAmount;

	@Column(name = "DISCOUNT_AMOUNT")
	private BigDecimal discountAmount;

	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private User orderBy;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.order")
	private Set<OrderDetail> orderDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public User getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(User orderBy) {
		this.orderBy = orderBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((discountAmount == null) ? 0 : discountAmount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((paidAmount == null) ? 0 : paidAmount.hashCode());
		result = prime * result + ((pickupDate == null) ? 0 : pickupDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((taxAmount == null) ? 0 : taxAmount.hashCode());
		result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
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
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (discountAmount == null) {
			if (other.discountAmount != null)
				return false;
		} else if (!discountAmount.equals(other.discountAmount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderBy == null) {
			if (other.orderBy != null)
				return false;
		} else if (!orderBy.equals(other.orderBy))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (paidAmount == null) {
			if (other.paidAmount != null)
				return false;
		} else if (!paidAmount.equals(other.paidAmount))
			return false;
		if (pickupDate == null) {
			if (other.pickupDate != null)
				return false;
		} else if (!pickupDate.equals(other.pickupDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (taxAmount == null) {
			if (other.taxAmount != null)
				return false;
		} else if (!taxAmount.equals(other.taxAmount))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		return true;
	}

}
