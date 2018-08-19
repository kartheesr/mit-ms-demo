package com.mitosis.msdemo.order.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mitosis.msdemo.order.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

	List<Order> findByCustomerId(String customerId);

}
