package com.mitosis.msdemo.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.msdemo.product.dao.ProductDao;
import com.mitosis.msdemo.product.model.Product;
import com.mitosis.msdemo.utils.CommonUtils;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public Product save(Product saveReq) {
		saveReq = productDao.save(saveReq);
		return (saveReq);
	}
	
	@Override
	public Product getById(String id) {
		Product product = productDao.getById(id);
		return (product);
	}

	@Override
	public List<Product> getAll() {
		List<Product> productList = productDao.getAll();
		return (productList);
	}

	@Override
	public boolean deleteById(String id) {
		boolean result = false;
		productDao.deleteById(id);
		result = true;
		return result;
	}

	@Override
	public boolean validateProduct(Product req) {
		if(req == null)
			return false;
		else if(!CommonUtils.validateString(req.getName()))
			return false;
		else if(req.getPrice()<=0)
			return false;
		else
			return true;
	}

}
