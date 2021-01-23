package com.godoro.springRent.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.godoro.springRent.business.dto.CarDto;
import com.godoro.springRent.business.service.CarService;
import com.godoro.springRent.data.entity.Car;
import com.godoro.springRent.data.entity.Customer;
import com.godoro.springRent.data.repository.CarRepository;
import com.godoro.springRent.exception.ResourceNotFoundException;

@Service
public class CarServiceImpl implements CarService{
	
	
	@Autowired 
	private CarRepository carRepository;
	
	
	@Override
	public List<CarDto> carList() {
		List<CarDto> carDtos = new ArrayList<>();
		Iterable<Car> cars = carRepository.findAll();
		for(Car car : cars) {
			CarDto carDto = new CarDto();
			convertToDto(car, carDto);
			carDtos.add(carDto);
		}
		
		return carDtos; 
	}
	
	
	@Override
	public List<CarDto> rentableCarList() {
		List<CarDto> carDtos = new ArrayList<>();
		Iterable<Car> cars = carRepository.findByRentableCar();
		for(Car car : cars) {
			CarDto carDto = new CarDto();
			convertToDto(car, carDto);
			carDtos.add(carDto);
		}
		
		return carDtos; 
		
	}

	
	

	@Override
	public Car addCar(CarDto carDto) {
		
		try {
			Car car = new Car();
			carDto.setStatus(false);
			convertToEntity(car,carDto);
			return carRepository.save(car);
			
		}
		
		
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	@Override
	public void deleteCar(long carID) throws ResourceNotFoundException {
		Car car = carRepository.findById(carID)
			.orElseThrow(() -> new ResourceNotFoundException("car bulunamadi"));
		
		carRepository.deleteById(car.getCarID());		
	}
	
	

	@Override
	public ResponseEntity<CarDto> findByCarID(long carID) throws ResourceNotFoundException{
		CarDto cardto = new CarDto();
		Car car = carRepository.findById(carID)
				.orElseThrow(() -> new ResourceNotFoundException("car bulunamadi"));
		
		
		if(!car.equals(null)) {	 
			convertToDto(car,cardto);
			return ResponseEntity.ok(cardto);
		}	
		else {
			return (ResponseEntity<CarDto>) ResponseEntity.notFound();
		}
		
		 
	}
	
	
	
	
	
	
	private void convertToEntity(Car car, CarDto carDto) {
		car.setCarID(carDto.getCarID());
		car.setCarBrand(carDto.getCarBrand());
		car.setCarModel(carDto.getCarModel());
		car.setPrice(carDto.getPrice());
		car.setStatus(carDto.getStatus());
	}
	
	
	private void convertToDto(Car car, CarDto carDto) {
		carDto.setCarID(car.getCarID());
		carDto.setCarBrand(car.getCarBrand());
		carDto.setCarModel(car.getCarModel());
		carDto.setPrice(car.getPrice());
		carDto.setStatus(car.getStatus());
	}





	






}
