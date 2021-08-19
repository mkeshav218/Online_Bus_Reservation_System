package com.microservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservice.entity.BusDetails;
import com.microservice.entity.BusRoute;
import com.microservice.entity.BusType;
import com.microservice.service.SearchService;

@SpringBootTest
class SearchServiceApplicationTests {

	@Autowired
	BusType busType;
	
	@Autowired
	BusDetails busDetails;
	
	@Autowired
	BusRoute busRoute;
	
	@Autowired
	SearchService searchService;
	
	@Test
	void test1() {
		
		busType.setBusName("Deepmala");
		busType.setBusType("Driver");
		busType.setBusStatus("Active");
		
		int result = searchService.addBusType(busType);
		System.out.println("BusType res = " + result);
		
		busDetails.setNewBusName(busType);
		busDetails.setRouteNo(1);
		
		result = searchService.addBusDetails(busDetails);
		System.out.println("BusDetails res = " + result);

		busRoute.setPathNo(1);
		busRoute.setBusNo(101);
		busRoute.setSource("Supaul");
		busRoute.setDestination("Mumbai");
		busRoute.setDistance(1500);
		busRoute.setStartTime("05:00");
		busRoute.setReachTime("17:00");
		busRoute.setFare(1500);
		busRoute.setNewBusDetails(busDetails);
		
		result = searchService.addRoute(busRoute);
		System.out.println("BusRoute res = " + result);

	}
	
	@Test
	void test2() {
		
		busType.setBusName("RajRath");
		busType.setBusType("Driver");
		busType.setBusStatus("Active");
		
		int result = searchService.addBusType(busType);
		System.out.println("BusType res = " + result);
		
		

	}

}
