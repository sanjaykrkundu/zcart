package com.zycus.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.dao.PurchaseDao;
import com.zycus.model.Cart;
import com.zycus.model.Customer;
import com.zycus.model.Order;
import com.zycus.model.Product;
import com.zycus.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	private PurchaseDao purchaseDao;
	
	
	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	@Autowired
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	@Transactional
	public void savePurchase(Cart cart, Customer customer,String status) {
		purchaseDao.savePurchase(cart, customer,status);
	}

	@Transactional
	public Map<Order,Product> getCart(Customer customer) {
		return purchaseDao.getCart(customer);
	}

	@Transactional
	public Map<Order,Product> getOrders(Customer customer) {
		return purchaseDao.getOrders(customer);
	}

}
