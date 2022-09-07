package com.microservice.repository;

import java.util.List;

import com.microservice.entity.BusDetails;
import com.microservice.entity.BusRoute;
import com.microservice.entity.BusInfo;

public interface SearchRepo {
	//Bus_Type Table Operations
	public BusInfo addNewBusDetails(BusInfo newBus);
	public BusInfo updateBusStatus(String busName, String busStatus);
	public void deleteBus(String busName);
	public BusInfo getBusInfo(String busName);
	public List<BusInfo> getAllBusType();
	
	//Bus_Details_table
	public void addBusDetails(BusDetails busObj);
	public void deleteBusDetails(int routeNo);
	public void updateBusDetails(BusDetails busDetails);
	public BusDetails getBus(int routeNo);
	public List<BusDetails> getAllBusDetails();

	
	//Route Table
	public int addRoute(BusRoute newRoute);
	public void deleteRoute(int pathNo);
	public List<String[]> searchBus(String src, String dest);  //return list of bus_Details
	public BusRoute getRouteDetails(int pathNo);
	public BusRoute getRouteDetails(int busNo, String source, String destination);
	public void updateBusRoute(BusRoute busRoute);
	public List<BusRoute> getAllBusRoute();

}
