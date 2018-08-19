package com.mitosis.msdemo.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mitosis.msdemo.order.dao.OrderDao;
import com.mitosis.msdemo.order.model.Order;
import com.mitosis.msdemo.order.service.OrderService;
import com.mitosis.msdemo.product.model.Product;
import com.mitosis.msdemo.utils.CSResponseMessage;
import com.mitosis.msdemo.utils.CommonUtils;
import com.mitosis.msdemo.utils.MyAppointmentResponse;
import com.mitosis.msdemo.utils.OrderSortUtil;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MyAppointmentResponse> createOrder(@RequestBody Order req) {
		MyAppointmentResponse response = new MyAppointmentResponse();
    	try{
    		if(orderService.validateOrder(req)) {
    			
    			double unitPrice = req.getUnitPrice();
    			double totalPrice = unitPrice * req.getQty();
    			req.setTotalPrice(totalPrice);
    			req.setOrderId("MIT"+CommonUtils.generateRandomInteger(5));
    			req.setCreateOn(new Date());
				Order data = orderService.save(req);
				response.setStatus(CSResponseMessage.SUCCESS);
				response.setMessage(CSResponseMessage.RECORD_ADDED);
				response.setData(data);
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
	    		Order order = orderService.getById(id);
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setData(order);
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
	    		orderService.deleteById(sampleId);
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setMessage(CSResponseMessage.RECORD_DELETED);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		response.setStatus(CSResponseMessage.ERROR);
	    		response.setMessage(e.getMessage());
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	    
	    @RequestMapping(value="/customer/{customerId}")
	    public ResponseEntity<MyAppointmentResponse> getByCustomerId(@PathVariable("customerId") String customerId){
	    	MyAppointmentResponse response = new MyAppointmentResponse();
	    	try{
	    		List<Order> orders = orderService.getByCustomerId(customerId);
	    		
	    		OrderSortUtil.sort(orders, "CREATEON", "DESC");
	    		
	    		response.setStatus(CSResponseMessage.SUCCESS);
	    		response.setData(orders);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		response.setStatus(CSResponseMessage.ERROR);
	    		response.setMessage(e.getMessage());
	    	}
	        return new ResponseEntity<MyAppointmentResponse>(response, HttpStatus.OK);
	    }
	
}
