package com.godoro.springRent.data.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.godoro.springRent.data.entity.Accept;
 



@Repository
public interface AcceptRepository extends CrudRepository<Accept,Long>{
	
	
	@Query("select a.customer.nickName from Accept a where a.customer.nickName = :nickName")
	public String isCustomer(@Param("nickName") String nickName);
	
	
	
	@Query("select a.car.carID from Accept a where a.car.carID = :carID")
	public Long isCar(@Param("carID") Long carID);
	
	
	
	@Modifying
	@Query("delete from Accept a where a.customer.nickName = :nickName")
	public void deleteAccept(@Param("nickName") String nickName);

}
