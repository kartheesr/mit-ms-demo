
package com.mitosis.msdemo.customer.service;

import java.util.List;
import com.mitosis.msdemo.customer.model.Customer;

public interface CustomerService {
	
		Customer save(Customer cOustomer);
		
		Customer getById(String id);
		
		List<Customer> getAll();
		
		boolean deleteById(String customerId);

		boolean validateCustomer(Customer req);

		boolean validateLoginReq(Customer req);
		
}
