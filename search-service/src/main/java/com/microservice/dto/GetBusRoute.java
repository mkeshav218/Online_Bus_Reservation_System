package com.microservice.dto;

import org.springframework.stereotype.Component;

@Component
public class GetBusRoute {
	String source;
	String destination;
	public GetBusRoute() {
		super();
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
