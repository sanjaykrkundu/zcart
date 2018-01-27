package com.zycus.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zycus.model.Cart;
import com.zycus.model.Customer;
import com.zycus.model.Order;
import com.zycus.model.Product;
import com.zycus.service.ProductService;
import com.zycus.service.PurchaseService;

@Controller
public class CartController {

	private Cart cart;
	private ProductService productService;
	private PurchaseService purchaseService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@Autowired
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}

	private boolean isLoggedIn(HttpSession session) {
		if (session.getAttribute("customer") == null)
			return false;
		return true;
	}

	@RequestMapping(value = { "/cart", "/cart.html", "/cart.jsp", "/cart.htm" })
	public String showCart(Model model) {
		model.addAttribute(cart);
		return "cart";
	}

	@RequestMapping(value = { "/orders", "/orders.html", "/orders.jsp", "/orders.htm" })
	public String showOrder(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "redirect:/login";
		model.addAttribute("orderList", purchaseService.getOrders((Customer) session.getAttribute("customer")));
		return "orders";
	}

	@RequestMapping(value = "/cart/add/{pid}", method = RequestMethod.POST)
	public String addProductToCart(@PathVariable int pid, HttpServletRequest request,
			@RequestHeader("referer") String ref, RedirectAttributes redAttr) {
		int qty = Integer.parseInt(request.getParameter("quantity"));
		cart.addProduct(productService.getProduct(pid), qty);
		redAttr.addFlashAttribute("message", "Product Added To The Card");
		return "redirect:" + ref;
	}

	@RequestMapping(value = "/cart/remove/{productID}", method = RequestMethod.GET)
	public String removeFromCart(@PathVariable("productID") int productid, Model model) {
		Product product = productService.getProduct(productid);
		if (product != null)
			cart.removeProduct(product);
		model.addAttribute(cart);
		return "/cart";
	}

	@RequestMapping(value = "/cart/placeOrder", method = RequestMethod.POST)
	public String placeOrder(HttpSession session, RedirectAttributes redirectAttributes) {
		if (!isLoggedIn(session))
			return "redirect:/login";
		if (cart.getCartItems().isEmpty()) {
			redirectAttributes.addFlashAttribute("cartMessage", "Cart empty, Please add products to the cart");
			return "redirect:/cart";
		} else {
			Customer customer = (Customer) session.getAttribute("customer");
			purchaseService.savePurchase(cart, customer, "ordered");
			cart.clearCart();
			return "redirect:/cart";
		}

	}

	@RequestMapping(value = "/loadCart", method = RequestMethod.POST)
	public String loadCart(HttpSession session, @RequestHeader("referer") String ref) {
		Customer customer = (Customer) session.getAttribute("customer");
		Map<Order, Product> map = (Map<Order, Product>) purchaseService.getCart(customer);
		for (Entry item : map.entrySet()) {
			cart.addProduct((Product)item.getValue(), ((Order)item.getKey()).getQuantity());
		}
		return "redirect:/index";
	}

}
