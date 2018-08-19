package com.mitosis.msdemo.customer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mitosis.msdemo.customer.model.Customer;
import com.mitosis.msdemo.customer.repository.CustomerRepository;
import com.mitosis.msdemo.utils.CommonUtils;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Customer save(Customer customer) {
		customerRepo.save(customer);
		return customer;
	}

	@Override
	public Customer getById(String id) {
		Customer customer = null;
		if(CommonUtils.validateString(id)) {
//			customer = customerRepo.findById(id);
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			customer = mongoTemplate.findOne(query, Customer.class);
		}
		return customer;
	}
	
	@Override
	public List<Customer> getAll() {
		List<Customer> customerList = customerRepo.findAll();
		return customerList;
	}

	@Override
	public boolean deleteById(String id) {
		boolean result = false;
		if(CommonUtils.validateString(id)){
			customerRepo.deleteById(id);
			result = true;
		}
		return result;
	}

	@Override
	public Customer findByEmail(String email) {
		Customer customer = null;
		if(CommonUtils.validateString(email))
			customer = customerRepo.findByEmail(email);
		return customer;
	}

	@Override
	public Customer findByEmailAndPassword(String email, String encPass) {
		Customer customer = null;
		if(CommonUtils.validateString(email) && CommonUtils.validateString(encPass))
			customer = customerRepo.findByEmailAndPassword(email, encPass);
		return customer;
	}
	
}
