package com.godoro.springRent.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.lang.Nullable;
import javax.persistence.CascadeType;


@Entity
public class Customer {
	
	@Id
	private String nickName;
	private String name;
	private String surname;
	private String e_mail;
	private String password;
	private long balance;
	
	@OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_id")
	@Nullable
	private Car car;
	
	@OneToOne(mappedBy = "customer")
	private Accept accept;
	
	
	
	public Customer() {
	}

	public Customer(String nickName, String name, String surname, String e_mail, String password, long balance, Car car) {
		super();
		this.nickName = nickName;
		this.name = name;
		this.surname = surname;
		this.e_mail = e_mail;
		this.password = password;
		this.balance = balance;
		this.car = car;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}



	
	

}
