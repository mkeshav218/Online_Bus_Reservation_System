package com.microservice.service;

import java.util.List;

import com.microservice.entity.BusDetails;
import com.microservice.entity.BusRoute;
import com.microservice.entity.BusType;

public interface SearchService {
	//Bus_Type Table Operations
	public int addBusType(BusType newBus);
	public int updateBusStatus(String busName, String busStatus);
	public int deleteBus(String busName);
	public BusType getBusType(String busName);
	public List<BusType> getAllBusType();  

	
	//Bus_Details_table
	public int addBusDetails(BusDetails busObj); 
	public int deleteBusDetails(int routeNo);  
	public int updateBusDetails(BusDetails busDetails); 
	public BusDetails getBus(int routeNo);
	public List<BusDetails> getAllBusDetails();  
	
	//Route Table
	public int addRoute(BusRoute newRoute); 
	public int deleteRoute(int pathNo);  
	public List<String[]> searchBus(String src, String dest, String fromTime, String toTime, String typeOfUser);  //return list of bus_Details
	public BusRoute getRouteDetails(int busNo, String source, String destination);
	public BusRoute getRouteDetails(int pathNo); 
	public int updateBusRoute(BusRoute busRoute); 
	public List<BusRoute> getAllBusRoute();  
}
