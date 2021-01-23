package com.godoro.springRent.business.dto;


public class AcceptDto {
	
	
	private long ID;
	private long carID;
	private String customerNickname;
	private int day;
	
	
	
	
	public AcceptDto() {
	}
	public AcceptDto(long iD, long carID, String customerNickname, int day) {
		ID = iD;
		this.carID = carID;
		this.customerNickname = customerNickname;
		this.day = day;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getCarID() {
		return carID;
	}
	public void setCarID(long carID) {
		this.carID = carID;
	}
	public String getCustomerNickname() {
		return customerNickname;
	}
	public void setCustomerNicknamee(String customerNickname) {
		this.customerNickname = customerNickname;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

	
	
	
 


	

}
