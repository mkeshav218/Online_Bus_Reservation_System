package com.microservice.pojo;

import java.util.Set;

public class BusDetails {

	private int routeNo;
	private BusInfo busInfo;
	private Set<BusRoute> busRoute;
	
	public BusDetails() { 
		super();
	}

	public int getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(int routeNo) { 
		this.routeNo = routeNo;
	}

	public BusInfo getBusInfo() {
		return busInfo;
	}

	public void setBusInfo(BusInfo busInfo) {
		this.busInfo = busInfo;
	}

	public Set<BusRoute> getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(Set<BusRoute> busRoute) {
		this.busRoute = busRoute;
	}

	@Override
	public String toString() {
		return "BusDetails [routeNo=" + routeNo + ", busInfo=" + busInfo + ", busRoute=" + busRoute + "]";
	}

	
}

