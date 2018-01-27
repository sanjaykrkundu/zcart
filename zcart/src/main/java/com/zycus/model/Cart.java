package com.zycus.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.zycus.model.Product;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
	private Map<Product, Integer> cartItems;

	public Cart() {
		cartItems = new HashMap<Product, Integer>();
	}

	public Map<Product, Integer> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<Product, Integer> cartItems) {
		this.cartItems = cartItems;
	}

	public void addProduct(Product product, int count) {
		if (cartItems.containsKey(product)) {
			int quantity = cartItems.get(product) + count;
			if (product.getQuantity() >= quantity)
				cartItems.put(product, quantity);
		} else {
			cartItems.put(product, count);
		}
	}

	public void removeProduct(Product product) {
		cartItems.remove(product);
	}

	public void clearCart() {
		cartItems.clear();
	}

	@Override
	public String toString() {
		return "Cart [cartItems=" + cartItems + "]";
	}

	
	public double getGrandTotal() {
		return cartItems.entrySet().parallelStream().map((entry)->{
			return entry.getKey().getProductPrice() * entry.getValue();
		}).reduce(0.0,Double::sum);

	}
}
