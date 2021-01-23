package com.godoro.springRent.presentation.rest;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.godoro.springRent.business.dto.CustomerDto;
import com.godoro.springRent.business.service.CustomerService;
import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.exception.GlobalExceptionHandler;
 


@RestController
public class NewCustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	

	
	@PostMapping("/addcustomer")
	public Customer addCustomer(@Valid @RequestBody CustomerDto customer) {
		return customerService.save(customer);		  
	}
	
	
//	@PostMapping("/addcustomer")
//	public String addCustomer(@Valid @RequestBody CustomerDto customer, BindingResult result) {
//		customerService.save(customer);
//		
////		if(result.hasErrors()) {
////			return result.getGlobalError();
////		}
////		return null;
//		
//		
//		 if (result.hasErrors()) {
//		        return result.toString();
//		    }
//		    return null;
//	}

 
	
	
	
	
	
	@GetMapping("/test")
	public String asfdasf() {
 
		return "test";
	}
	

}
