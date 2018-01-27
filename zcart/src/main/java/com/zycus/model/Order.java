package com.zycus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "zcart_orders")
public class Order {
	@Id
	@GenericGenerator(strategy = "increment", name = "inc")
	@GeneratedValue(generator = "inc")
	private int orderId;
	@Column
	private int productId;
	@Column
	private int customerId;
	@Column(nullable = false)
	private int quantity;
	@Column
	private String status;

	public Order() {
		super();
	}

	public Order(int productId, int customerId, int quantity, String status) {
		super();
		this.productId = productId;
		this.customerId = customerId;
		this.quantity = quantity;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
