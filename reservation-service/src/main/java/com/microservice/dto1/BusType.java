package com.microservice.dto1;

import java.util.Set;

public class BusType {

	private String busName;

	private String busType;

	private String busStatus;
	
	private Set<BusDetails> newBusDetails;

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}


	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusStatus() {
		return busStatus;
	}

	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}

	public Set<BusDetails> getNewBusDetails() {
		return newBusDetails;
	}

	public void setNewBusDetails(Set<BusDetails> newBusDetails) {
		this.newBusDetails = newBusDetails;
	}

	@Override
	public String toString() {
		return "BusType [busName=" + busName + ", busType=" + busType + ", busStatus=" + busStatus + ", newBusDetails="
				+ newBusDetails + "]";
	}

	
	
}
