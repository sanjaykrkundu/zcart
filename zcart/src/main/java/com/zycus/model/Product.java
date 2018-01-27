package com.zycus.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name = "zcart_products")
public class Product {
	@Id
	@GenericGenerator(strategy = "increment", name = "inc")
	@GeneratedValue(generator = "inc")
	private int productID;
	@Column(nullable=false)
	private double productPrice;
	@Column(unique=true,nullable=false)
	private String productName;
	@Column(length=300)
	private String productDetail;
	@Column(nullable=false,length=15)
	private String productCategory;
	@Column(nullable=false)
	private int quantity;

	public Product() {
		super();
	}

	public Product(int productID, double productPrice, String productName, String productDetail, int quantity,String productCategory) {
		super();
		this.productID = productID;
		this.productPrice = productPrice;
		this.productName = productName;
		this.productDetail = productDetail;
		this.quantity = quantity;
		this.productCategory = productCategory;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productPrice=" + productPrice + ", productName=" + productName
				+ ", productDetail=" + productDetail + ", productCategory=" + productCategory + ", quantity=" + quantity
				+ "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productCategory == null) {
			if (other.productCategory != null)
				return false;
		} else if (!productCategory.equals(other.productCategory))
			return false;
		if (productID != other.productID)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productCategory,productID,productName);
	}
	
}
