package com.zycus.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.dao.CustomerDao;
import com.zycus.dao.ProductDao;
import com.zycus.model.Customer;
import com.zycus.model.Product;
import com.zycus.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private CustomerDao customerDao;
	private ProductDao productDao;

	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	@Transactional
	public Customer authenticate(Customer customer) {
		return customerDao.authenticate(customer);
	}
	@Transactional
	public boolean register(Customer customer) {
		return customerDao.register(customer);
	}
	@Transactional
	public boolean updateProfile(Customer customer) {
		return customerDao.updateProfile(customer);
	}
	@Transactional
	public boolean updatePassword(Customer customer) {
		return customerDao.updatePassword(customer);
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

}
