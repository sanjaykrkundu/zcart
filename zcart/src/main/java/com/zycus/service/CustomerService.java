package com.zycus.service;

import com.zycus.model.Customer;
import com.zycus.model.Feedback;

public interface CustomerService {

	public Customer authenticate(Customer customer);

	public boolean register(Customer customer);

	public boolean updateProfile(Customer customer);

	public boolean updatePassword(Customer customer);
	
	public boolean giveFeedback(Feedback feedback);
}
