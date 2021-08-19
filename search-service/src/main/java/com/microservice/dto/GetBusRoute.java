package com.microservice.dto;

import org.springframework.stereotype.Component;

@Component
public class GetBusRoute {
	int busNo;
	String source;
	String destination;
	public GetBusRoute() {
		super();
	}
	public int getBusNo() {
		return busNo;
	}
	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
