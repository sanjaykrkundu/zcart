package com.zycus.service;

import java.util.List;

import com.zycus.model.Customer;
import com.zycus.model.Product;

public interface AdminService {
	public Customer authenticate(Customer customer);

	public boolean register(Customer customer);

	public boolean updateProfile(Customer customer);

	public boolean updatePassword(Customer customer);

	public boolean addProduct(Product product);

	public boolean updateProduct(Product product);

	public boolean removeProduct(Product product);

	public Product getProduct(int productId);

	public List<Product> getProductList();
	
	public List<Product> getProductListByCategory(String category);

}
