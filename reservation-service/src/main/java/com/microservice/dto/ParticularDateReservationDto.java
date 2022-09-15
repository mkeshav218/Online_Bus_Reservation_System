package com.microservice.dto;

import com.microservice.pojo.Login;
import com.microservice.pojo.ParticularDate;

public class ParticularDateReservationDto {
	Login login;
	ParticularDate date;

	public ParticularDateReservationDto() {
		super();
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public ParticularDate getDate() {
		return date;
	}

	public void setDate(ParticularDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ParticularDateReservationDto [login=" + login + ", date=" + date + "]";
	}

	
	
}
