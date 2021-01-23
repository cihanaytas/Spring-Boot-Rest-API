package com.godoro.springRent.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.godoro.springRent.data.entity.Customer;
 

 
@Repository
public interface CustomerRepository extends CrudRepository<Customer,String>{

	@Query("select u from Customer u where u.nickName = :nickName")
	public Customer findByUsername(@Param("nickName")String nickName);
	
	@Query("select c from Customer c where c.car.carID = :carID")
	public Customer findByCarID(@Param("carID") Long carID);
}
