package com.microservice.service;

import java.util.List;

import com.microservice.entity.BusDetails;
import com.microservice.entity.BusRoute;
import com.microservice.entity.BusInfo;

public interface SearchService {
	//Bus_Info Table Operations
	public BusInfo addNewBusDetails(BusInfo newBus);
	public BusInfo updateBusStatus(String busName, String busStatus);
	public int deleteBus(String busName);
	public BusInfo getBusInfo(String busName);
	public List<BusInfo> getAllBusType();  

	
	//Bus_Details_table
	public BusDetails addBusDetails(BusDetails busObj); 
	public int deleteBusDetails(int routeNo);  
	public BusDetails updateBusDetails(BusDetails busDetails); 
	public BusDetails getBus(int routeNo);
	public List<BusDetails> getAllBusDetails();  
	
	//Route Table
	public BusRoute addRoute(BusRoute newRoute); 
	public int deleteRoute(int pathNo);  
	public List<String[]> searchBus(String src, String dest, String fromTime, String toTime, String typeOfUser);  //return list of bus_Details
	public BusRoute getRouteDetails(int busNo, String source, String destination);
	public BusRoute getRouteDetails(int pathNo); 
	public int updateBusRoute(BusRoute busRoute); 
	public List<BusRoute> getAllBusRoute();  
}
