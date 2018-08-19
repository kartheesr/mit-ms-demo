package com.mitosis.msdemo.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mitosis.msdemo.customer.dao.CustomerDao;
import com.mitosis.msdemo.customer.model.Customer;
import com.mitosis.msdemo.customer.service.CustomerService;
import com.mitosis.msdemo.utils.CSResponseMessage;
import com.mitosis.msdemo.utils.CommonUtils;
import com.mitosis.msdemo.utils.MyAppointmentResponse;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerDao customerDao;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<MyAppointmentResponse> create(@RequestBody Customer req) {
		MyAppointmentResponse response = new MyAppointmentResponse();
    	try{
    		if(customerService.validateCustomer(req)) {
				String email = req.getEmail().trim().toLowerCase();
				Customer profile = customerDao.findByEmail(email);
				if(profile==null){
					req.setEmail(email);
					String encPass = CommonUtils.passwordToHash(req.getPassword());
					req.setPassword(encPass);
					Customer data = customerService.save(req);
					response.setStatus(CSResponseMessage.SUCCESS);
					response.setMessage(CSResponseMessage.RECORD_ADDED);
					response.setData(data);
				}else {
					response.setStatus(CSResponseMessage.FAILURE);
					response.setMessage(CSResponseMessage.EMAIL_EXIST);
				}
    		}else {
    			response.setStatus(CSResponseMessage.FAILURE);
				response.setMessage(CSResponseMessage.MANDATORY_PARAMETERS_MISSING);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		response.setStatus(CSResponseMessage.ERROR);
    		response.setMessage(e.getMessage());
    	}
        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<MyAppointmentResponse> login(@RequestBody Customer req) {
		MyAppointmentResponse response = new MyAppointmentResponse();
    	try{
    		if(customerService.validateLoginReq(req)) {
				String email = req.getEmail().trim().toLowerCase();
				String encPass = CommonUtils.passwordToHash(req.getPassword());
				Customer profile = customerDao.findByEmailAndPassword(email, encPass);
				if(profile!=null){
					response.setStatus(CSResponseMessage.SUCCESS);
					response.setMessage(CSResponseMessage.SUCCESS);
					response.setData(profile);
				}else {
					response.setStatus(CSResponseMessage.FAILURE);
					response.setMessage(CSResponseMessage.INCORRECT_EMAIL_OR_PASSWORD);
				}
    		}else {
    			response.setStatus(CSResponseMessage.FAILURE);
				response.setMessage(CSResponseMessage.MANDATORY_PARAMETERS_MISSING);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		response.setStatus(CSResponseMessage.ERROR);
    		response.setMessage(e.getMessage());
    	}
        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
    }
	
	 @RequestMapping(value="/{id}")
	    public ResponseEntity<MyAppointmentResponse> getById(@PathVariable("id") String id){
	    	MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		Customer customer = customerService.getById(id);
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setData(customer);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		response.setStatus(CSResponseMessage.ERROR);
	    		response.setMessage(e.getMessage());
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<MyAppointmentResponse> delete(@PathVariable("id") String sampleId){
	    	MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		customerService.deleteById(sampleId);
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setMessage(CSResponseMessage.RECORD_DELETED);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		response.setStatus(CSResponseMessage.ERROR);
	    		response.setMessage(e.getMessage());
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	    
	    @RequestMapping
	    public ResponseEntity<MyAppointmentResponse> list() {
	        MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		List<Customer> sampleList = customerService.getAll();
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setData(sampleList);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		response.setStatus(CSResponseMessage.ERROR);
	    		response.setMessage(e.getMessage());
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	
}
