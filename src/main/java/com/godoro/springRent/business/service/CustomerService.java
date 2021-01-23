package com.godoro.springRent.business.service;
 
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.godoro.springRent.business.dto.CustomerDto;
import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.exception.ResourceNotFoundException;

public interface CustomerService {
	
	public Customer save(CustomerDto customerDto);
	
	public void update(CustomerDto customerDto);
	
	public void delete();

	public void convertToDto(Customer customer, CustomerDto customerDto);
	
	public void convertToEntity(Customer customer, CustomerDto customerDto);
	
	public List<CustomerDto> customerList();
	
	 
	public ResponseEntity<CustomerDto> findByUserName(String nickName) throws ResourceNotFoundException;

}
