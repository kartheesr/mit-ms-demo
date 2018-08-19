package com.mitosis.msdemo.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.msdemo.customer.dao.CustomerDao;
import com.mitosis.msdemo.customer.model.Customer;
import com.mitosis.msdemo.utils.CommonUtils;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Override
	public Customer save(Customer saveReq) {
		saveReq = customerDao.save(saveReq);
		return (saveReq);
	}
	
	@Override
	public Customer getById(String id) {
		Customer customer = customerDao.getById(id);
		return (customer);
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customerList = customerDao.getAll();
		return (customerList);
	}

	@Override
	public boolean deleteById(String id) {
		boolean result = false;
		customerDao.deleteById(id);
		result = true;
		return result;
	}

	@Override
	public boolean validateCustomer(Customer req) {
		if(req == null)
			return false;
		else if(!CommonUtils.validateString(req.getName()))
			return false;
		else if(!CommonUtils.validateString(req.getEmail()))
			return false;
		else if(!CommonUtils.validateString(req.getPassword()))
			return false;
		else
			return true;
	}

	@Override
	public boolean validateLoginReq(Customer req) {
		if(req == null)
			return false;
		else if(!CommonUtils.validateString(req.getEmail()))
			return false;
		else if(!CommonUtils.validateString(req.getPassword()))
			return false;
		else
			return true;
	}

}
