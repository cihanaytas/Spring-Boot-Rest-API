package com.godoro.springRent.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.godoro.springRent.data.entity.Car;


 

  
@Repository
public interface CarRepository extends CrudRepository<Car, Long>{

	@Query("select c from Car c where c.status = false")
	public Iterable<Car> findByRentableCar();

}
