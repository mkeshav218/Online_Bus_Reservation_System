package com.microservice.dto;

public class UpdateBus {
	String busName;
	String busStatus;
	public UpdateBus() {
		super();
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusStatus() {
		return busStatus;
	}
	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}
	
	
}
