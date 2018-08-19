package com.mitosis.msdemo.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mitosis.msdemo.order.model.Order;
import com.mitosis.msdemo.order.repository.OrderRepository;
import com.mitosis.msdemo.utils.CommonUtils;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Order save(Order order) {
		orderRepo.save(order);
		return order;
	}

	@Override
	public Order getById(String id) {
		Order order = null;
		if(CommonUtils.validateString(id)) {
//			order = orderRepo.findById(id);
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			order = mongoTemplate.findOne(query, Order.class);
		}
		return order;
	}
	
	@Override
	public List<Order> getAll() {
		List<Order> orderList = orderRepo.findAll();
		return orderList;
	}

	@Override
	public boolean deleteById(String id) {
		boolean result = false;
		if(CommonUtils.validateString(id)){
			orderRepo.deleteById(id);
			result = true;
		}
		return result;
	}

	@Override
	public List<Order> getByCustomerId(String customerId) {
		List<Order> orders = new ArrayList<Order>();
		if(CommonUtils.validateString(customerId)){
			orders = orderRepo.findByCustomerId(customerId);
		}
		return orders;
	}
	
}
