package com.microservice.dto;

import com.microservice.pojo.BusRoute;
import com.microservice.pojo.Login;

public class BusRouteDto {
	Login login;
	BusRoute busRoute;
	
	public BusRouteDto() {
	
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public BusRoute getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

	@Override
	public String toString() {
		return "BusRouteDto [login=" + login + ", busRoute=" + busRoute + "]";
	}

	
}
