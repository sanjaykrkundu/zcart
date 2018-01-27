package com.zycus.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zycus.dao.PurchaseDao;
import com.zycus.model.Cart;
import com.zycus.model.Customer;
import com.zycus.model.Order;
import com.zycus.model.Product;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void savePurchase(Cart cart, Customer customer, String status) {
		for (Entry<Product, Integer> item : cart.getCartItems().entrySet()) {
			Product product = item.getKey();
			int quantity = item.getValue();
			Order order = new Order(product.getProductID(), customer.getCustomerId(), quantity, status);
			hibernateTemplate.save(order);
			product.setQuantity(product.getQuantity()-quantity);
			hibernateTemplate.update(product);
		}
	}

	@Override
	public Map<Order,Product> getCart(Customer customer) {
		List<Order> list =  (List<Order>) hibernateTemplate.find("from Order where customerId=? AND upper(status)=?", new Object[]{customer.getCustomerId(),"cart".toUpperCase()});
		hibernateTemplate.deleteAll(list);
		Map<Order,Product> map = new HashMap<>();
			list.forEach(e->{
				map.put(e, hibernateTemplate.get(Product.class, ((Order)e).getProductId()));
			});
		return map;
	}

	@Override
	public Map<Order,Product> getOrders(Customer customer) {
		List<Order> list =  (List<Order>) hibernateTemplate.find("from Order where customerId=? AND upper(status)=?", new Object[]{customer.getCustomerId(),"ordered".toUpperCase()});
		Map<Order,Product> map = new HashMap<>();
			list.forEach(e->{
				map.put(e, hibernateTemplate.get(Product.class, ((Order)e).getProductId()));
			});
		return map;
	}

}
