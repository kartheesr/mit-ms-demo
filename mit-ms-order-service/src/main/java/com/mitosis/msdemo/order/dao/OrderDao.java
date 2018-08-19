package com.mitosis.msdemo.order.dao;

import java.util.List;

import com.mitosis.msdemo.order.model.Order;


public interface OrderDao {
	
		Order save(Order order);
		
		Order getById(String id);
		
		List<Order> getAll();
		
		boolean deleteById(String id);

		List<Order> getByCustomerId(String customerId);

}
