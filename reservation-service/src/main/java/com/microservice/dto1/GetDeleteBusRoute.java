package com.microservice.dto1;

import org.springframework.stereotype.Component;

@Component
public class GetDeleteBusRoute {
	int pathNo;

	public GetDeleteBusRoute() {
		super();
	}

	public int getPathNo() {
		return pathNo;
	}

	public void setPathNo(int pathNo) {
		this.pathNo = pathNo;
	}
	
}
