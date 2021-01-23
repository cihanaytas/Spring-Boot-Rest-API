package com.godoro.springRent.data.entity;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Car {
	
	@Id// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long carID;
	
	@NotNull 
	private String carBrand;
	
	@NotNull
	private String carModel;
	
	@NotNull
	private long price;
	
	@Column(columnDefinition="boolean default false")
	private Boolean status;
	
	
	
	@OneToOne(mappedBy = "car")
	private Customer customer;
	
	@OneToOne(mappedBy = "car")
	private Accept accept;
	

	public Car() {
 	}

	public Car(long carID, String carBrand, String carModel, long price, Boolean status, Customer customer) {
		super();
		this.carID = carID;
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.price = price;
		this.status = status;
		this.customer = customer;
	}

	public long getCarID() {
		return carID;
	}

	public void setCarID(long carID) {
		this.carID = carID;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



}
