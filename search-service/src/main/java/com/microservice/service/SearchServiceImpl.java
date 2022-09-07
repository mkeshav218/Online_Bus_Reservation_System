package com.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entity.BusDetails;
import com.microservice.entity.BusRoute;
import com.microservice.entity.BusInfo;
import com.microservice.exception.BusServiceException;
import com.microservice.repository.SearchRepo;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	SearchRepo searchRepo;

	@Override
	public BusInfo addNewBusDetails(BusInfo newBus) {
		try {
			return searchRepo.addNewBusDetails(newBus);
		}catch(Exception e) {
			throw new BusServiceException("Bus-Info already Present...!!");
		}
	}

	@Override
	public BusInfo updateBusStatus(String busName, String busStatus) {
		try {
			return searchRepo.updateBusStatus(busName, busStatus);
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public int deleteBus(String busName) {
		try {
			searchRepo.deleteBus(busName);
			return 1;
		}catch (Exception e) {
			throw new BusServiceException("Cannot remove bus");
		}
	}

	@Override
	public BusInfo getBusInfo(String busName) {
		try {
			return searchRepo.getBusInfo(busName);
		} catch (Exception e) {
			throw new BusServiceException("No Bus Found");
		}
	}
	
	@Override
	public List<BusInfo> getAllBusType(){
		try {
			return searchRepo.getAllBusType();
		}catch (Exception e) {
			throw new BusServiceException("No Bus Found");
		}
	}

	@Override
	public int addBusDetails(BusDetails busObj) {
		try {
			searchRepo.addBusDetails(busObj);
			return 1;
		} catch (Exception e) {
			throw new BusServiceException("Cannot add bus details");
		}
	}

	@Override
	public int deleteBusDetails(int routeNo) {
		try {
			searchRepo.deleteBusDetails(routeNo);
			return 1;
		} catch (Exception e) {
			throw new BusServiceException("Cannot remove bus details");
		}
	}

	@Override
	public int updateBusDetails(BusDetails busDetails) {
		try {
			searchRepo.updateBusDetails(busDetails);
			return 1;
		} catch (Exception e) {
			throw new BusServiceException("Cannot update bus details");
		}
	}

	@Override
	public BusDetails getBus(int routeNo) {
		try {
			return searchRepo.getBus(routeNo);
		} catch (Exception e) {
			throw new BusServiceException("bus not found");
		}
	}
	
	@Override
	public List<BusDetails> getAllBusDetails(){
		try {
			return searchRepo.getAllBusDetails();
		}catch (Exception e) {
			throw new BusServiceException("No Bus-Details Found");
		}
	}

	@Override
	public int addRoute(BusRoute newRoute) {
		try {
			int res = searchRepo.addRoute(newRoute);
			if(res == 1) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			throw new BusServiceException("Bus-Route Already Present...!!");
		}
	}

	@Override
	public int deleteRoute(int pathNo) {
		try {
			searchRepo.deleteRoute(pathNo);
			return 1;
		}catch (Exception e) {
			throw new BusServiceException("Cannot remove bus route");
		}
	}

	@Override
	public List<String[]> searchBus(String src, String dest, String fromTime, String toTime, String typeOfUser) {
		try {
			List<String[]> allBus = searchRepo.searchBus(src, dest);
			List<String[]> allBuses = new ArrayList<String[]>();
			int checkhr1=0,checkmin1=0;
			int checkhr2=0,checkmin2=0;
			                                   //05:40 to 06:00  
			for(int i=0;i<2;i++) {
				int k=1;
				checkhr1 += ((int)fromTime.charAt(i)-48)*Math.pow(10, k-i);  //0+10^2 + 5+10^1
				checkmin1 += ((int)fromTime.charAt(i+3)-48)*Math.pow(10, k-i);  //4+10^2 + 0+10^1
				checkhr2 += ((int)toTime.charAt(i)-48)*Math.pow(10, k-i);  //0+10^2 + 6+10^1
				checkmin2 += ((int)toTime.charAt(i+3)-48)*Math.pow(10,k- i);  //0+10^2 + 0+10^1
			}
			for(String[] bus:allBus) {
				String arrivalTime = bus[5];//05:30   05:40 to 06:00
				int hrbus=0;
				int minbus=0;
				for(int i=0;i<2;i++) {
					int k=1;
					hrbus += ((int)arrivalTime.charAt(i)-48)*Math.pow(10, k-i);  //0+10^2 + 5+10^1
					minbus += ((int)arrivalTime.charAt(i+3)-48)*Math.pow(10, k-i);  //3+10^2 + 0+10^1
				}
				if(hrbus>checkhr1 && hrbus<=checkhr2) {
					if(typeOfUser.equals("unauthorized") && bus[10].equals("Driverless")) {
						//(no need to add bus to the list);
					}else if(typeOfUser.equals("unauthorized") && bus[10].equals("Driver")) {
						allBuses.add(bus);
					}else {
						allBuses.add(bus);
					}
					
				}else if(hrbus==checkhr1) {
					if(minbus>=checkmin1 && minbus<=checkmin2) {
						if(typeOfUser.equals("unauthorized") && bus[10].equals("Driverless")) {
							//(no need to add bus to the list);
						}else if(typeOfUser.equals("unauthorized") && bus[10].equals("Driver")) {
							allBuses.add(bus);
						}else {
							allBuses.add(bus);
						}
					}else {
						//remove bus from busList (no need to add bus to the list)
					}
				}else {
					//remove bus from busList (no need to add bus to the list)
				}
			}
			if(allBuses.size()==0) {                                            
				throw new BusServiceException("No Bus Available for source :" + src +" and destination : " + dest);
			}
			return allBuses;
		} catch (Exception e) {
			throw new BusServiceException("Invalid entry, no buses are available for source :" + src +" and destination : " + dest);
		}
	}
	
	@Override
	public BusRoute getRouteDetails(int busNo, String source, String destination) {
		try {
			return searchRepo.getRouteDetails(busNo, source, destination);
		}catch (Exception e) {
			throw new BusServiceException("Bus-Route details not found...!!");
		}
	}

	@Override
	public BusRoute getRouteDetails(int pathNo) {
		try {
			return searchRepo.getRouteDetails(pathNo);
		} catch (Exception e) {
			throw new BusServiceException("Bus-Route details not found");
		}
	}

	@Override
	public int updateBusRoute(BusRoute busRoute) {
		try {
			searchRepo.updateBusRoute(busRoute);
			return 1;
		} catch (Exception e) {
			throw new BusServiceException("Cannot update bus route");
		}
	}
	
	@Override
	public List<BusRoute> getAllBusRoute(){
		try {
			return searchRepo.getAllBusRoute();
		}catch (Exception e) {
			throw new BusServiceException("No Bus-Route Found");
		}
	}

}
