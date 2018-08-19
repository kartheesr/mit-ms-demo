package com.mitosis.msdemo.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.msdemo.order.dao.OrderDao;
import com.mitosis.msdemo.order.model.Order;
import com.mitosis.msdemo.utils.CommonUtils;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Override
	public Order save(Order saveReq) {
		saveReq = orderDao.save(saveReq);
		return (saveReq);
	}
	
	@Override
	public Order getById(String id) {
		Order order = orderDao.getById(id);
		return (order);
	}

	@Override
	public List<Order> getAll() {
		List<Order> orderList = orderDao.getAll();
		return (orderList);
	}

	@Override
	public boolean deleteById(String id) {
		boolean result = false;
		orderDao.deleteById(id);
		result = true;
		return result;
	}

	@Override
	public boolean validateOrder(Order req) {
		if(req == null)
			return false;
		else if(!CommonUtils.validateString(req.getCustomerId()))
			return false;
		else if(!CommonUtils.validateString(req.getProductId()))
			return false;
		else if(req.getQty()<=0)
			return false;
		else
			return true;
	}

	@Override
	public List<Order> getByCustomerId(String customerId) {
		List<Order> orders = orderDao.getByCustomerId(customerId);
		return orders;
	}

}
