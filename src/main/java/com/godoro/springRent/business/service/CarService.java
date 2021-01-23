package com.godoro.springRent.business.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.godoro.springRent.business.dto.CarDto;
import com.godoro.springRent.data.entity.Car;
import com.godoro.springRent.exception.ResourceNotFoundException;
 

public interface CarService {
		
	public List<CarDto> carList();
	
	public List<CarDto> rentableCarList();
	
	public Car addCar(CarDto carDto);
	
	public void deleteCar(long carID) throws ResourceNotFoundException;
	
	public ResponseEntity<CarDto> findByCarID(long carID) throws ResourceNotFoundException;
	
	

}
