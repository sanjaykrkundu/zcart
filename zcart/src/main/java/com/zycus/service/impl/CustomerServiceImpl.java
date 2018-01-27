package com.zycus.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zycus.dao.CustomerDao;
import com.zycus.model.Customer;
import com.zycus.model.Feedback;
import com.zycus.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerDao customerDao;
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

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
	public boolean giveFeedback(Feedback feedback) {
		return customerDao.giveFeedback(feedback);
	}
	
}
