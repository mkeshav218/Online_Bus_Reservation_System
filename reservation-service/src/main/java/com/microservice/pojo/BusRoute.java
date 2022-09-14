package com.microservice.pojo;

public class BusRoute {

	private int pathNo;
	private int busNo;
	private String source;
	private String destination;
	private int distance;
	private String startTime;
	private String reachTime;
	private int fare;
	private BusDetails newBusDetails;

	
	public BusRoute() {
		super();
	}


	public int getPathNo() {
		return pathNo;
	}


	public void setPathNo(int pathNo) {
		this.pathNo = pathNo;
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


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getReachTime() {
		return reachTime;
	}


	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}


	public int getFare() {
		return fare;
	}


	public void setFare(int fare) {
		this.fare = fare;
	}


	public BusDetails getNewBusDetails() {
		return newBusDetails;
	}


	public void setNewBusDetails(BusDetails newBusDetails) {
		this.newBusDetails = newBusDetails;
	}


	@Override
	public String toString() {
		return "BusRoute [pathNo=" + pathNo + ", busNo=" + busNo + ", source=" + source + ", destination=" + destination
				+ ", distance=" + distance + ", startTime=" + startTime + ", reachTime=" + reachTime + ", fare=" + fare
				+ ", newBusDetails=" + newBusDetails + "]";
	}

}
