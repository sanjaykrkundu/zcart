package com.zycus.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zycus.dao.CustomerDao;
import com.zycus.model.Customer;
import com.zycus.model.Feedback;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Customer authenticate(Customer customer) {
		List<Customer> customerList = (List<Customer>) hibernateTemplate.find("from Customer where email =? AND password=? AND type=?",new Object[]{customer.getEmail(),customer.getPassword(),customer.getType()});
		if(!customerList.isEmpty()){
			return customerList.get(0);
		}
		return null;
	}

	public boolean register(Customer customer) {
		Integer customerId = (Integer) hibernateTemplate.save(customer);
		if(customerId!=null)
			return true;
		return false;
	}

	public boolean updateProfile(Customer customer) {
		try {
			hibernateTemplate.update(customer);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean updatePassword(Customer customer) {
		try {
			hibernateTemplate.update(customer);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean giveFeedback(Feedback feedback) {
		try {
			hibernateTemplate.save(feedback);
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
