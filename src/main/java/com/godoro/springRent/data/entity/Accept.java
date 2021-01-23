package com.godoro.springRent.data.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Accept {
	
	@Id 
	private long ID;
	
	@OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
	private Car car;
	
	@OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "nickName")
	private Customer customer;

	
	private int day;
	
	
	
	
	public Accept() {

	}

	public Accept(long iD, Car car, Customer customer, int day) {
		ID = iD;
		this.car = car;
		this.customer = customer;
		this.day = day;
	}


	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public Car getCar() {
		return car;
	}


	public void setCar(Car car) {
		this.car = car;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}



	
}
