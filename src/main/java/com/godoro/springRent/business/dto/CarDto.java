package com.godoro.springRent.business.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarDto {
	
	private long carID;
	
	@NotNull
	@Size(min = 2, max = 20, message = "Incorret Car Brand")
	private String carBrand;
	
	@NotNull
	@Size(min = 2, max = 20, message = "Incorret Car Model")
	private String carModel;
	
	@NotNull
	private long price;
	private Boolean status;

		
	public CarDto() {
		
	}
	public CarDto(long carID, String carBrand, String carModel, long price) {
		 
		this.carID = carID;
		this.carBrand = carBrand;
		this.carModel = carModel;
		this.price = price;
		this.status = false;
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

}
