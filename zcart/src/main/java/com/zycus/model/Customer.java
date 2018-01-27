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
@Table(name = "zcart_customers")
public class Customer {
	@Id
	@GenericGenerator(strategy = "increment", name = "inc")
	@GeneratedValue(generator = "inc")
	private int customerId;
	@Column(length = 25, nullable = false)
	private String customerName;
	@Column(length = 256, unique = true, nullable = false)
	private String email;
	@Column(length = 32, nullable = false)
	private String password;
	@Column
	private long mobile;
	@Column
	private char type = 'U';

	public char getType() {
		return type;
	}

	public Customer(int customerId, String customerName, String email, String password, long mobile) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Customer() {
		super();
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + "]";
	}

}
