package com.mitosis.msdemo.product.dao;

import java.util.List;

import com.mitosis.msdemo.product.model.Product;


public interface ProductDao {
	
		Product save(Product product);
		
		Product getById(String id);
		
		List<Product> getAll();
		
		boolean deleteById(String id);

		Product findByName(String name);

		Product findByCode(String code);

}
