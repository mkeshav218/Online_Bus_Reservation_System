package com.microservice.dto;

import com.microservice.pojo.Login;

public class GetDeleteBusTypeDto {
	Login login;
	GetDeleteBusType busInfo;
	
	public GetDeleteBusTypeDto() {
		super();
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public GetDeleteBusType getBusInfo() {
		return busInfo;
	}

	public void setBusInfo(GetDeleteBusType busInfo) {
		this.busInfo = busInfo;
	}

	@Override
	public String toString() {
		return "GetDeleteBusTypeDto [login=" + login + ", removeBus=" + busInfo + "]";
	}

	
}
