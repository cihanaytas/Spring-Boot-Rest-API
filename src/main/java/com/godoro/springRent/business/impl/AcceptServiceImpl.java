package com.godoro.springRent.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.godoro.springRent.business.dto.AcceptDto;
import com.godoro.springRent.business.service.AcceptService;
import com.godoro.springRent.data.entity.Accept;
import com.godoro.springRent.data.entity.Car;
import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.data.repository.AcceptRepository;
import com.godoro.springRent.data.repository.CarRepository;
import com.godoro.springRent.data.repository.CustomerRepository; 
 
 
@Service
public class AcceptServiceImpl implements AcceptService{

	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private AcceptRepository acceptRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void rentcar(long carID,int day) {
		 
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 
			
			String controlCustomerName = acceptRepository.isCustomer(authentication.getName());
			Long controlCarId = acceptRepository.isCar(carID);
		 
			if(controlCustomerName==null && controlCarId==null)
			{	
				long accID = ThreadLocalRandom.current().nextLong(1000);
				Accept accept = new Accept();
				AcceptDto acceptdto = new AcceptDto();
				acceptdto.setCarID(carID);
				acceptdto.setDay(day);			
				acceptdto.setCustomerNicknamee(authentication.getName());
				acceptdto.setID(accID);
						
				convertToEntity(accept, acceptdto);
				acceptRepository.save(accept);
			}
			else {
				System.out.println("zaten arac kiralamis");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteRent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	 
		acceptRepository.deleteAccept(authentication.getName());
		
	}
	
	@Override
	public List<AcceptDto> acceptList() {
		List<AcceptDto> acceptDtos = new ArrayList<>();
		Iterable<Accept> accepts = acceptRepository.findAll();
		for(Accept accept : accepts) {
			AcceptDto acceptDto = new AcceptDto();
			convertToDto(accept, acceptDto);
			acceptDtos.add(acceptDto);
		}
		
		return acceptDtos; 
	}
	
 
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void acceptControl(Long acceptID) {
		Optional<Accept> optional = acceptRepository.findById(acceptID);
		Accept accept = optional.get();
		
		long total = accept.getDay() * accept.getCar().getPrice();
		
		Customer customer = customerRepository.findByUsername(accept.getCustomer().getNickName());
		
		if(customer.getBalance() >= total) {
			Optional<Car> optionalCar = carRepository.findById(accept.getCar().getCarID());
			Car car = optionalCar.get();
			
			customer.setBalance(customer.getBalance() - total);
			customer.setCar(car);
			customerRepository.save(customer);
		
			car.setStatus(true);
		 
			acceptRepository.deleteById(acceptID);
			
		}
		
		
		else {
			System.out.println("bakiye yetersiz");
			acceptRepository.deleteById(acceptID);
		}
			
	}
	
	
	@Override
	public void takeToCar(Long carID) {
		Optional<Car> carOptional = carRepository.findById(carID);
		Car car = carOptional.get();
		
		car.setStatus(false);
		carRepository.save(car);
		
		Customer customer = customerRepository.findByCarID(carID);
		customer.setCar(null);
		customerRepository.save(customer);
		
	}







	public void convertToEntity(Accept accept, AcceptDto acceptdto) {		
		accept.setID(acceptdto.getID());
		accept.setDay(acceptdto.getDay());
		
		if(acceptdto.getCarID()!= 0L) {
			Optional<Car> optional = carRepository.findById(acceptdto.getCarID());
			if(optional.isPresent()) {
				Car car = optional.get();
				accept.setCar(car);
			}
		}
		
		
		if(!acceptdto.getCustomerNickname().isEmpty()) {
			Optional<Customer> optional = customerRepository.findById(acceptdto.getCustomerNickname());
			if(optional.isPresent()) {
				Customer customer = optional.get();
				accept.setCustomer(customer);
			}
		}
		    	
		
	}
	
	
	public void convertToDto(Accept accept, AcceptDto acceptdto) {
		acceptdto.setID(accept.getID());
		acceptdto.setDay(accept.getDay());
		acceptdto.setCarID(accept.getCar().getCarID());
		acceptdto.setCustomerNicknamee(accept.getCustomer().getNickName());

	}

















}
