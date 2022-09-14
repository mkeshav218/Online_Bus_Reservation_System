package com.microservice.dto1;

import java.util.Set;

import com.microservice.pojo.BusRoute;

public class BusDetails {
	
	private int routeNo;

	private BusType newBusName;

	private Set<BusRoute> newRoute;
	
	public BusDetails() { 
		super();
	}

	public int getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(int routeNo) { 
		this.routeNo = routeNo;
	}

	public BusType getNewBusName() {
		return newBusName;
	}

	public void setNewBusName(BusType newBusName) {
		this.newBusName = newBusName;
	}

	public Set<BusRoute> getNewRoute() {
		return newRoute;
	}

	public void setNewRoute(Set<BusRoute> newRoute) {
		this.newRoute = newRoute;
	}

	@Override
	public String toString() {
		return "BusDetails [routeNo=" + routeNo + ", newBusName=" + newBusName + ", newRoute=" + newRoute + "]";
	}
	
	
	
}

