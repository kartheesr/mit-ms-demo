package com.mitosis.msdemo.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mitosis.msdemo.customer.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

	Customer findByEmail(String email);

	Customer findByEmailAndPassword(String email, String encPass);

}
