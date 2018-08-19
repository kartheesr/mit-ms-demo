
package com.mitosis.msdemo.order.service;

import java.util.List;

import com.mitosis.msdemo.order.model.Order;

public interface OrderService {
	
		Order save(Order order);
		
		Order getById(String id);
		
		List<Order> getAll();
		
		boolean deleteById(String id);

		boolean validateOrder(Order req);

		List<Order> getByCustomerId(String customerId);

}
