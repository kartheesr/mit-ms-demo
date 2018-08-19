package com.mitosis.msdemo.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mitosis.msdemo.product.dao.ProductDao;
import com.mitosis.msdemo.product.model.Product;
import com.mitosis.msdemo.product.service.ProductService;
import com.mitosis.msdemo.utils.CSResponseMessage;
import com.mitosis.msdemo.utils.MyAppointmentResponse;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MyAppointmentResponse> create(@RequestBody Product req) {
		MyAppointmentResponse response = new MyAppointmentResponse();
    	try{
    		if(productService.validateProduct(req)) {
				String code = req.getName().trim().toUpperCase().replaceAll(" ","");
				Product product = productDao.findByCode(code);
				if(product==null || (product.getId().equals(req.getId()))){
					req.setCode(code);
					Product data = productService.save(req);
					response.setMessage(CSResponseMessage.RECORD_UPDATED);
					response.setStatus(CSResponseMessage.SUCCESS);
					response.setData(data);
				}else {
					response.setMessage(CSResponseMessage.FAILURE);
					response.setStatus(CSResponseMessage.NAME_EXIST);
				}
    		}else {
    			response.setMessage(CSResponseMessage.FAILURE);
				response.setStatus(CSResponseMessage.MANDATORY_PARAMETERS_MISSING);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		response.setStatus(CSResponseMessage.ERROR);
    		response.setMessage(CSResponseMessage.RECORD_ADD_ERROR);
    	}
        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
    }

	@RequestMapping(value="/{id}")
	    public ResponseEntity<MyAppointmentResponse> getById(@PathVariable("id") String id){
	    	MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		Product product = productService.getById(id);
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setData(product);
	    	}catch(Exception e){
	    		response.setStatus(CSResponseMessage.FAILURE);
	    		response.setMessage(CSResponseMessage.ERROR);
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<MyAppointmentResponse> delete(@PathVariable("id") String sampleId){
	    	MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		productService.deleteById(sampleId);
	    		response.setMessage(CSResponseMessage.RECORD_DELETED);
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    	}catch(Exception e){
	    		response.setStatus(CSResponseMessage.FAILURE);
	    		response.setMessage(CSResponseMessage.RECORD_DELETE_ERROR);
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	    
	    @RequestMapping
	    public ResponseEntity<MyAppointmentResponse> list() {
	        MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		List<Product> sampleList = productService.getAll();
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setData(sampleList);
	    	}catch(Exception e){
	    		response.setStatus(CSResponseMessage.FAILURE);
	    		response.setMessage(CSResponseMessage.ERROR);
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	
}
