package com.mitosis.msdemo.customer.dao;

import java.util.List;

import com.mitosis.msdemo.customer.model.Customer;


public interface CustomerDao {
	
		Customer save(Customer customer);
		
		Customer getById(String id);
		
		List<Customer> getAll();
		
		boolean deleteById(String id);

		Customer findByEmail(String email);

		Customer findByEmailAndPassword(String email, String encPass);
		
}
