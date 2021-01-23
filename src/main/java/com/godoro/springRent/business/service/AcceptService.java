package com.godoro.springRent.business.service;

import java.util.List;

import com.godoro.springRent.business.dto.AcceptDto;

public interface AcceptService {
	
	public void rentcar(long carID, int day);

	public void deleteRent();

	public List<AcceptDto> acceptList();

	public void acceptControl(Long acceptID);

	public void takeToCar(Long carID);

}
