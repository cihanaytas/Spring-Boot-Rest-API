package com.godoro.springRent.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.godoro.springRent.business.dto.CustomerDto;
import com.godoro.springRent.business.service.CustomerService;
import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.data.repository.CustomerRepository;
import com.godoro.springRent.exception.ResourceNotFoundException;

 
 
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	

	
	@Override
	public Customer save(CustomerDto customerdto){
		try {
			Customer customer = new Customer();
			convertToEntity(customer, customerdto);
 
			return customerRepository.save(customer);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	
	
	@Override
	public void update(CustomerDto customerDto) {
		try {
			Customer customer = new Customer();
			convertToEntity(customer, customerDto);
			customerRepository.save(customer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

	@Override
	public void delete() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customerRepository.deleteById(authentication.getName());		
	}
	
	
	
	
	@Override
	public List<CustomerDto> customerList() {
		List<CustomerDto> customerDtos = new ArrayList<>();
		Iterable<Customer> customers = customerRepository.findAll();
		for(Customer customer : customers) {
			CustomerDto customerDto = new CustomerDto();
			convertToDto(customer, customerDto);
			customerDtos.add(customerDto);
		}
		
		return customerDtos; 
	}
	
	


	
	
	
	@Override
	public ResponseEntity<CustomerDto> findByUserName(String nickName) throws ResourceNotFoundException{
			 
		CustomerDto customerdto = new CustomerDto();
		
		Customer customer = customerRepository.findById(nickName)
				.orElseThrow(() ->  new ResourceNotFoundException("bulunamadi."));
				
		if(!customer.equals(null)) {
			convertToDto(customer,customerdto);
			return ResponseEntity.ok(customerdto);
			
		}	
		else {
			return  (ResponseEntity<CustomerDto>) ResponseEntity.notFound(); 
			
		}
		
		
	}
	
	


	
	
	

	
	
	
	
	public void convertToEntity(Customer customer, CustomerDto customerdto) {		
		customer.setNickName(customerdto.getNickName());
		customer.setName(customerdto.getName());
		customer.setSurname(customerdto.getSurname());
		customer.setE_mail(customerdto.getE_mail());
		customer.setBalance(customerdto.getBalance());
		customer.setPassword(customerdto.getPassword());
//		if(customerdto.getCarID()!=0) {
//			
//		}
		
	}
	
	
	public void convertToDto(Customer customer, CustomerDto customerdto) {
		customerdto.setNickName(customer.getNickName());
		customerdto.setName(customer.getName());
		customerdto.setSurname(customer.getSurname());
		customerdto.setE_mail(customer.getE_mail());
		customerdto.setBalance(customer.getBalance());
		customerdto.setPassword(customer.getPassword());
		//car eklenecek
	}



























 }

  
