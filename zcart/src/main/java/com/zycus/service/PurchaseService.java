package com.zycus.service;

import java.util.Map;

import com.zycus.model.Cart;
import com.zycus.model.Customer;
import com.zycus.model.Order;
import com.zycus.model.Product;

public interface PurchaseService {

	public void savePurchase(Cart cart, Customer customer,String status);

	public Map<Order,Product> getCart(Customer customer);

	public Map<Order,Product> getOrders(Customer customer);

}
