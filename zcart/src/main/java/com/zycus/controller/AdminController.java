package com.zycus.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zycus.model.Customer;
import com.zycus.model.Product;
import com.zycus.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private AdminService adminService;

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	private static boolean isLoggedIn(HttpSession session) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null)
			return false;
		if (customer.getType() == 'A')
			return true;
		return false;
	}

	// get_pages mapping
	@RequestMapping(value = { "/", "/index", "/index.html", "/index.jsp", "/index.htm" })
	public String getHomePage(HttpSession session) {
		if (isLoggedIn(session))
			return "admin/index";
		return "admin/login";
	}

	@RequestMapping(value = { "/login", "/login.html", "/login.jsp", "/login.htm" })
	public String getLoginPage(Model model, HttpSession session) {
		if (isLoggedIn(session))
			return "admin/index";
		model.addAttribute("customer", new Customer());
		return "admin/login";
	}

	@RequestMapping(value = { "/profile", "/profile.html", "/profile.jsp", "/profile.htm" })
	public String getProfilePage(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "admin/login";
		model.addAttribute("customer", session.getAttribute("customer"));
		return "admin/profile";
	}

	@RequestMapping(value = { "/password", "/password.html", "/password.jsp", "/password.htm" })
	public String getChangePasswordPage(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "admin/login";
		model.addAttribute("customer", session.getAttribute("customer"));
		return "admin/password";
	}

	@RequestMapping(value = { "/addproduct", "/addproduct.html", "/addproduct.jsp", "/addproduct.htm" })
	public String getAddProductPage(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "admin/login";
		model.addAttribute("product", new Product());
		return "admin/addproduct";
	}

	@RequestMapping(value = { "/addstock", "/addstock.html", "/addstock.jsp", "/addstock.htm" })
	public String getAddStockPage(Model model, HttpSession session) {
		if (!isLoggedIn(session))
			return "admin/login";
		model.addAttribute("productList", adminService.getProductList());
		return "admin/addstock";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("customer") Customer customer, HttpSession session, Model model) {
		customer.setType('A');
		Customer customerNew = adminService.authenticate(customer);
		if (customerNew != null) {
			session.setAttribute("customer", customerNew);
			return "admin/index";
		} else {
			model.addAttribute("message", "Login Failed");
			return "admin/login";
		}
	}

	@RequestMapping(value = "/editProfile.do", method = RequestMethod.POST)
	public String editProfileProcess(@ModelAttribute("customer") Customer customer, HttpSession session, Model model) {
		if (!isLoggedIn(session))
			return "admin/login";
		Customer sessionCustomer = (Customer) session.getAttribute("customer");
		customer.setCustomerId(sessionCustomer.getCustomerId());
		customer.setPassword(sessionCustomer.getPassword());
		boolean status = adminService.updateProfile(customer);
		if (status) {
			session.setAttribute("customer", customer);
			model.addAttribute("message", "Profile Updated");
		} else {
			model.addAttribute("message", "Something Went Wrong");
		}
		return "admin/profile";
	}

	@RequestMapping(value = "/logout.html")
	public String logoutProcess(HttpSession session) {
		session.invalidate();
		return "admin/login";
	}

	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
	public String changePasswordProcess(HttpSession session, HttpServletRequest request, Model model) {
		if (!isLoggedIn(session))
			return "admin/login";
		String oldPass = request.getParameter("opassword");
		Customer customer = (Customer) session.getAttribute("customer");
		String oldPassFromSession = customer.getPassword();
		if (oldPass.equals(oldPassFromSession)) {
			String newPass = request.getParameter("password");
			customer.setPassword(newPass);
			boolean status = adminService.updatePassword(customer);
			if (status) {
				session.setAttribute("customer", customer);
				model.addAttribute("message", "Password updated successfully");
			} else {
				model.addAttribute("message", "Something went wrong");
			}
		} else {
			model.addAttribute("message", "Password doesn't match");
		}
		return "admin/password";
	}

	@RequestMapping(value = "/addProduct.do", method = RequestMethod.POST)
	public String addProductProcess(@RequestParam("productImage") MultipartFile file,@ModelAttribute("product") Product product,HttpSession session, Model model) {
		if (!isLoggedIn(session))
			return "admin/login";

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("resources/img/"+product.getProductCategory() +"/" + product.getProductID() + ".jpg");
			System.out.println(path);
			System.out.println(file.getName());
			Files.write(path, bytes);
		} catch (IOException e) {
			model.addAttribute("message", "Something went wrong");
			return "admin/addproduct";
		}

		boolean status = adminService.addProduct(product);
		if (status) {
			model.addAttribute("message", "Product Added");
		} else {
			model.addAttribute("message", "Something went wrong");
		}
		return "admin/addproduct";
	}

	@RequestMapping(value = "/addStock.do", method = RequestMethod.POST)
	public String addProductProcess(HttpSession session, HttpServletRequest request, RedirectAttributes redir) {
		if (!isLoggedIn(session))
			return "admin/login";

		Product product = adminService.getProduct(Integer.parseInt(request.getParameter("productId")));
		product.setQuantity(Integer.parseInt(request.getParameter("productQuantity")) + product.getQuantity());

		boolean status = adminService.updateProduct(product);

		if (status) {
			redir.addFlashAttribute("message", "Stock Added");
		} else {
			redir.addFlashAttribute("message", "Something went wrong");
		}
		return "redirect:addstock";
	}

	// unknow Page mapping
	@RequestMapping(value = { "/*" })
	public String getError404() {
		return "404";
	}

	private boolean uploadPic() {

		return false;
	}

}
