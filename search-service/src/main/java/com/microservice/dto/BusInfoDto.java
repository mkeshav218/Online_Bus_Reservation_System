package com.microservice.dto;

import com.microservice.entity.BusInfo;
import com.microservice.pojo.Login;

public class BusInfoDto {
	Login login;
	BusInfo busInfo;
	public BusInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public BusInfo getBusInfo() {
		return busInfo;
	}
	public void setBusInfo(BusInfo busInfo) {
		this.busInfo = busInfo;
	}
	@Override
	public String toString() {
		return "BusInfoDto [login=" + login + ", busInfo=" + busInfo + "]";
	}	
}
