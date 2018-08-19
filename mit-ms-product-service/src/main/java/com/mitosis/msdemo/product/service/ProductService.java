
package com.mitosis.msdemo.product.service;

import java.util.List;

import com.mitosis.msdemo.product.model.Product;

public interface ProductService {
	
		Product save(Product cOustomer);
		
		Product getById(String id);
		
		List<Product> getAll();
		
		boolean deleteById(String productId);

		boolean validateProduct(Product req);

}
