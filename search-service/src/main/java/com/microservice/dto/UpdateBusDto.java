package com.microservice.dto;

import com.microservice.pojo.Login;

public class UpdateBusDto {
	Login login;
	UpdateBus updateBus;
	public UpdateBusDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public UpdateBus getUpdateBus() {
		return updateBus;
	}
	public void setUpdateBus(UpdateBus updateBus) {
		this.updateBus = updateBus;
	}
	@Override
	public String toString() {
		return "UpdateBusDto [login=" + login + ", updateBus=" + updateBus + "]";
	}
}
