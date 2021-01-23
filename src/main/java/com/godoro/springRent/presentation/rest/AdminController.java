package com.godoro.springRent.presentation.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.godoro.springRent.business.dto.AcceptDto;
import com.godoro.springRent.business.dto.CarDto;
import com.godoro.springRent.business.dto.CustomerDto;
import com.godoro.springRent.business.service.AcceptService;
import com.godoro.springRent.business.service.CarService;
import com.godoro.springRent.business.service.CustomerService;
import com.godoro.springRent.data.entity.Car;
import com.godoro.springRent.exception.ResourceNotFoundException;

 

 

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired 
	private CarService carService;
	
	@Autowired 
	private AcceptService acceptService;
	

	
	
	@GetMapping("/customer/list")
	public List<CustomerDto> getCustomers() throws ResourceNotFoundException {
		List<CustomerDto> customers = customerService.customerList();
		return customers;
	}
	
		
	@GetMapping("/customer/{nickName}")
	public ResponseEntity<CustomerDto> findCustomer(@PathVariable("nickName") String nickName) throws ResourceNotFoundException {
		ResponseEntity<CustomerDto> customerdto = customerService.findByUserName(nickName);
		return customerdto;
	}
	
	
	
	@PostMapping("/addcar")
	public Car addCar(@Valid @RequestBody CarDto car) {
		return carService.addCar(car);
	}
	
	
	@PutMapping("/addcar")
	public Car updateCar(@Valid @RequestBody CarDto car) {
		//car bulunup sonra update edilmeli?
		return carService.addCar(car);
	}
	
	@DeleteMapping("deletecar/{car_id}")
	public void deleteCar(@PathVariable("car_id") Long carID) throws ResourceNotFoundException {
		carService.deleteCar(carID);
	}
	
	@GetMapping("/cars")
	public List<CarDto> getCarList() {
		List<CarDto> cars = carService.carList();
		return cars;
	}
	
	@GetMapping("car/{car_id}")
	public ResponseEntity<CarDto> getCarById(@PathVariable("car_id") Long carID) throws ResourceNotFoundException {
		ResponseEntity<CarDto> car = carService.findByCarID(carID);
		return car;
	}
	
	
	
	@GetMapping("/accepts")
	public List<AcceptDto> getAccepts() {
		List<AcceptDto> accepts = acceptService.acceptList();
		return accepts;
	}
	
	
	@GetMapping("accept/{accept_id}")
	public void acceptCar(@PathVariable("accept_id") Long acceptID) {
		acceptService.acceptControl(acceptID);
	}
	
	
	@GetMapping("takecar/{car_id}")
	public void takeToCar(@PathVariable("car_id") Long carID) {
		acceptService.takeToCar(carID);
	}
	
	
	
	@GetMapping("testadmin")
	public String ddafa()
	{
		return "testadminbasarili";
				
	}
	
	
		
	
	
	

	
}
