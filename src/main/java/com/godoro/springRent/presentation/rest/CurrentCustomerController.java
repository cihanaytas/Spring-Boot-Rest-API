package com.godoro.springRent.presentation.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godoro.springRent.business.dto.CarDto;
import com.godoro.springRent.business.dto.CustomerDto;
import com.godoro.springRent.business.service.AcceptService;
import com.godoro.springRent.business.service.CarService;
import com.godoro.springRent.business.service.CustomerService;
import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.exception.ResourceNotFoundException;



@RestController
@RequestMapping("customer")
public class CurrentCustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CarService carService;
	
	@Autowired 
	private AcceptService acceptService;
	
	@PutMapping("/updatecustomer")
	public Customer updateCustomer(@Valid @RequestBody CustomerDto customer) {
		return customerService.save(customer);	
	}
	
	
	@DeleteMapping("/deletecustomer")
	public void deleteCustomer() {
		customerService.delete();
	}
	
	
	@GetMapping("/cars")
	public List<CarDto> getCarList() {
		List<CarDto> cars = carService.rentableCarList();
		return cars;
	}
	

	@PostMapping("/rentcar/{car_id}/{day}")
	public void rentCar(@PathVariable("car_id") Long carID, @PathVariable("day") int day) {
		acceptService.rentcar(carID, day);
	}
	
	
	@DeleteMapping("deleterent")
	public void deleteRent() {
		acceptService.deleteRent();
	}
	
	
	@GetMapping("resptest")
	public ResponseEntity<CustomerDto> findCustomer() throws ResourceNotFoundException {
		ResponseEntity<CustomerDto> customerdto = customerService.findByUserName("ci6han");
		return customerdto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication); 
		}
		return "logOut";
	}
	 
	//-----------------------------------------------------------------------------------------//
	
	 

	
	@GetMapping("/testc")
	public String aas() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();
	}

}
