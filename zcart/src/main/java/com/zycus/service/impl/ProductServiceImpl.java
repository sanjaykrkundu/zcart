package com.zycus.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.dao.ProductDao;
import com.zycus.model.Product;
import com.zycus.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Transactional
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Transactional
	public boolean updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Transactional
	public boolean removeProduct(Product product) {
		return productDao.removeProduct(product);
	}

	@Transactional
	public Product getProduct(int productId) {
		return productDao.getProduct(productId);
	}

	@Transactional
	public List<Product> getProductList() {
		return productDao.getProductList();
	}

	@Transactional
	public List<Product> getProductListByCategory(String category) {
		return productDao.getProductListByCategory(category);
	}

	@Transactional
	public List<Product> serachProduct(String name) {
		return productDao.serachProduct(name);
	}
}
