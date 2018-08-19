package com.mitosis.msdemo.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mitosis.msdemo.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	Product findByName(String name);

	Product findByCode(String code);

}
