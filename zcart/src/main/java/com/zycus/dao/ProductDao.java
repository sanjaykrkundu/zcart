package com.zycus.dao;

import java.util.List;

import com.zycus.model.Product;

public interface ProductDao {
	
	public boolean addProduct(Product product);

	public boolean updateProduct(Product product);

	public boolean removeProduct(Product product);
	
	public Product getProduct(int productId);
	
	public List<Product> getProductList();
	
	public List<Product> getProductListByCategory(String category);
	
	public List<Product> serachProduct(String name);
}
