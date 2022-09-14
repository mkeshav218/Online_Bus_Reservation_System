package com.microservice.pojo;

import java.util.Set;

public class BusInfo {

	private String busName;
	private String busType;
	private String busStatus;
	
	private Set<BusDetails> busDetails;

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

	public Set<BusDetails> getBusDetails() {
		return busDetails;
	}

	public void setBusDetails(Set<BusDetails> busDetails) {
		this.busDetails = busDetails;
	}

	@Override
	public String toString() {
		return "BusType [busName=" + busName + ", busType=" + busType + ", busStatus=" + busStatus + ", busDetails="
				+ busDetails + "]";
	}


	
}
