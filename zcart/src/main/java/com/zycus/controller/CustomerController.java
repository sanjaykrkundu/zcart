package com.zycus.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zycus.model.Cart;
import com.zycus.model.Customer;
import com.zycus.model.Feedback;
import com.zycus.model.Order;
import com.zycus.model.Product;
import com.zycus.service.CustomerService;
import com.zycus.service.ProductService;
import com.zycus.service.PurchaseService;

@Controller
public class CustomerController {
	private Cart cart;
	private CustomerService customerService;
	private ProductService productService;
	private PurchaseService purchaseService;

	// Methods
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
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
	
	private boolean isLoggedIn(HttpSession session){
		if(session.getAttribute("customer")==null)
			return false;
		return true;
	}
	
	// get_pages mapping
	@RequestMapping(value = { "/", "/index", "/index.html", "/index.jsp", "/index.htm" })
	public String getHomePage(HttpSession session) {
		return "index";
	}

	@RequestMapping(value = { "/login", "/login.html", "/login.jsp", "/login.htm" })
	public String getLoginPage(Model model,HttpSession session) {
		if(isLoggedIn(session))
			return "index";
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = { "/signup", "/signup.html", "/signup.jsp", "/signup.htm" })
	public String getSignupPage(Model model,HttpSession session) {
		if(isLoggedIn(session))
			return "index";
		model.addAttribute("customer", new Customer());
		return "signup";
	}

	@RequestMapping(value = { "/profile", "/profile.html", "/profile.jsp", "/profile.htm" })
	public String getProfilePage(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "login";
		model.addAttribute("customer", session.getAttribute("customer"));
		return "profile";
	}

	@RequestMapping(value = { "/password", "/password.html", "/password.jsp", "/password.htm" })
	public String getChangePasswordPage(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "login";
		model.addAttribute("customer", session.getAttribute("customer"));
		return "password";
	}

	@RequestMapping(value = "/category/{category}")
	public String getCategoryPage(@PathVariable String category,Model model) {
		model.addAttribute("productList",productService.getProductListByCategory(category));
		return "category";
	}
	
	@RequestMapping(value = "/search")
	public String getSearchPage(@RequestParam("query") String product,Model model) {
		model.addAttribute("productList",productService.serachProduct(product));
		return "search";
	}
	
	@RequestMapping(value = "/product/{productid}",method=RequestMethod.GET)
	public String getProductPage(@PathVariable int productid,Model model) {
		Product product = productService.getProduct(productid);
		model.addAttribute("product",product);
		return "product";
	}
	
	
	@RequestMapping(value = { "/aboutus", "/aboutus.html", "/aboutus.jsp", "/aboutus.htm" })
	public String getAboutPage() {
		return "aboutus";
	}
	
	@RequestMapping(value = { "/contactus", "/contactus.html", "/contactus.jsp", "/contactus.htm" })
	public String getContactPage(Model model,HttpSession session) {
		if (!isLoggedIn(session))
			model.addAttribute("feedback",new Feedback());
		else
			model.addAttribute("feedback",new Feedback(0,((Customer)session.getAttribute("customer")).getCustomerName(),null));
		return "contactus";
	}
	
	// processing page mapping
	@RequestMapping(value = { "/signup.do" })
	public String singUpProcess(@ModelAttribute("customer") Customer customer, Model model, HttpSession session) {
		boolean status = customerService.register(customer);
		if (status) {
			model.addAttribute("message", "Registration Successful");
		} else {
			model.addAttribute("message", "Registration Failed");
		}
		return "signup";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("customer") Customer customer, HttpSession session, Model model) {
		Customer customerNew = customerService.authenticate(customer);
		if (customerNew != null) {
			session.setAttribute("customer", customerNew);
			
			Map<Order, Product> map = (Map<Order, Product>) purchaseService.getCart(customerNew);
			System.out.println("map" + map);
			
			for (Entry item : map.entrySet()) {
				cart.addProduct((Product)item.getValue(), ((Order)item.getKey()).getQuantity());
			}
			
			
			return "index";
		} else {
			model.addAttribute("message", "Login Failed");
			return "login";
		}
	}

	@RequestMapping(value = "/editProfile.do", method = RequestMethod.POST)
	public String editProfileProcess(@ModelAttribute("customer") Customer customer, HttpSession session, Model model) {
		if (!isLoggedIn(session))
			return "login";
		Customer sessionCustomer = (Customer) session.getAttribute("customer");
		customer.setCustomerId(sessionCustomer.getCustomerId());
		customer.setPassword(sessionCustomer.getPassword());
		boolean status = customerService.updateProfile(customer);
		if (status) {
			session.setAttribute("customer", customer);
			model.addAttribute("message", "Profile Updated");
		} else {
			model.addAttribute("message", "Something Went Wrong");
		}
		return "profile";
	}

	@RequestMapping(value = "/logout.html")
	public String logoutProcess(HttpSession session) {
		if(isLoggedIn(session)){
			if (!cart.getCartItems().isEmpty()) {
				Customer customer = (Customer) session.getAttribute("customer");
				purchaseService.savePurchase(cart, customer, "cart");
				cart.clearCart();
			}
		} 
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String changePasswordProcess(HttpSession session, HttpServletRequest request, Model model) {
		if(!isLoggedIn(session)) 
			return "login";
		String oldPass = request.getParameter("opassword");
		Customer customer = (Customer) session.getAttribute("customer");
		String oldPassFromSession = customer.getPassword();
		if (oldPass.equals(oldPassFromSession)) {
			String newPass = request.getParameter("password");
			customer.setPassword(newPass);
			boolean status = customerService.updatePassword(customer);
			if (status) {
				session.setAttribute("customer", customer);
				model.addAttribute("message", "Password updated successfully");
			} else {
				model.addAttribute("message", "Something went wrong");
			}
		} else {
			model.addAttribute("message", "Password doesn't match");
		}
		return "password";
	}

	
	@RequestMapping(value = { "/feedback.do" })
	public String feedbackProcess(@ModelAttribute("feedback") Feedback feedback, RedirectAttributes redAttr) {
		boolean status = customerService.giveFeedback(feedback);
		if (status) {
			redAttr.addFlashAttribute("message", "Feedback Posted");
		} else {
			redAttr.addFlashAttribute("message", "Something Went Wrong");
		}
		return "redirect:contactus";
	}
	
	
	
	
	// unknow Page mapping
	@RequestMapping(value = { "/*" })
	public String getError404() {
		return "404";
	}
}
