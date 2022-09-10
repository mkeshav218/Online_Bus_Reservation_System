package com.microservice.dto;

import com.microservice.entity.BusDetails;
import com.microservice.pojo.Login;

public class BusDetailsDto {
	Login login;
	BusDetails busDetails;
	
	public BusDetailsDto() {
	
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public BusDetails getBusDetails() {
		return busDetails;
	}

	public void setBusDetails(BusDetails busDetails) {
		this.busDetails = busDetails;
	}

	@Override
	public String toString() {
		return "BusDetailsDto [login=" + login + ", busDetails=" + busDetails + "]";
	}
	
	
}
