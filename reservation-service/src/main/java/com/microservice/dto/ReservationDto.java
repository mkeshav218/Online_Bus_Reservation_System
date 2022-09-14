package com.microservice.dto;

import com.microservice.entity.Reservation;
import com.microservice.pojo.Login;

public class ReservationDto {
	
	Login login;
	Reservation reservationInfo;
	
	public ReservationDto() {
		super();
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Reservation getReservationInfo() {
		return reservationInfo;
	}

	public void setReservationInfo(Reservation reservationInfo) {
		this.reservationInfo = reservationInfo;
	}

	@Override
	public String toString() {
		return "ReservationDto [login=" + login + ", reservationInfo=" + reservationInfo + "]";
	}
	
}
