package com.mitosis.msdemo.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mitosis.msdemo.product.model.Product;
import com.mitosis.msdemo.product.repository.ProductRepository;
import com.mitosis.msdemo.utils.CommonUtils;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Product save(Product product) {
		productRepo.save(product);
		return product;
	}

	@Override
	public Product getById(String id) {
		Product product = null;
		if(CommonUtils.validateString(id)) {
//			product = productRepo.findById(id);
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			product = mongoTemplate.findOne(query, Product.class);
		}
		return product;
	}
	
	@Override
	public List<Product> getAll() {
		List<Product> productList = productRepo.findAll();
		return productList;
	}

	@Override
	public boolean deleteById(String id) {
		boolean result = false;
		if(CommonUtils.validateString(id)){
			productRepo.deleteById(id);
			result = true;
		}
		return result;
	}

	@Override
	public Product findByName(String name) {
		Product product = null;
		if(CommonUtils.validateString(name)) {
			product = productRepo.findByName(name);
		}
		return product;
	}
	
	@Override
	public Product findByCode(String code) {
		Product product = null;
		if(CommonUtils.validateString(code)) {
			product = productRepo.findByCode(code);
		}
		return product;
	}

}
