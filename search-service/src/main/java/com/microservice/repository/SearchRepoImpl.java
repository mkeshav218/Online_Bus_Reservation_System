package com.microservice.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.entity.BusDetails;
import com.microservice.entity.BusRoute;
import com.microservice.entity.BusInfo;

@Repository
@Transactional
public class SearchRepoImpl implements SearchRepo {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	BusInfo busType;
	
	@Autowired 
	BusDetails busDetails;
	
	@Autowired
	BusRoute busRoute;

	@Override
	public BusInfo addNewBusDetails(BusInfo newBus) {
		entityManager.persist(newBus);
		return newBus;
	}

	@Override
	public BusInfo updateBusStatus(String busName, String busStatus) {
		busType = getBusInfo(busName);
		busType.setBusStatus(busStatus);
		return entityManager.merge(busType);
	}

	@Override
	public void deleteBus(String busName) {
//		System.out.println("Bus Name to be deleted ...>>>> " + busName);
//		String hql = "SELECT R.routeNo FROM BusDetails R WHERE R.newBusName =:busName";
//		System.out.println(1);
//		Query query = entityManager.createQuery(hql);
//		System.out.println("Query = "+ query);
//		query.setParameter("busName",busName);
//		System.out.println(3);
//
//		List<Integer> routes = query.getResultList();
//
//		System.out.println(4);
//
//		for(int route:routes) {
//			System.out.println("Route No >>>>>>>> "+route);
//			deleteBusDetails(route);
//		}	
		busType = getBusInfo(busName);
		entityManager.remove(busType);
	}

	@Override
	public BusInfo getBusInfo(String busName) {
		return entityManager.find(BusInfo.class, busName);
	}
	
	@Override
	public List<BusInfo> getAllBusType(){
		String hql = "FROM BusType";
		Query query = entityManager.createQuery(hql);
		List<BusInfo> busTypeList = query.getResultList();
		return busTypeList;
	}

	@Override
	public void addBusDetails(BusDetails busObj) {
		busType = getBusInfo(busObj.getNewBusName().getBusName());
		if(busType == null) {
			addNewBusDetails(busObj.getNewBusName());
		}
		entityManager.persist(busObj);
	}

	@Override
	public void deleteBusDetails(int routeNo) {
//		String hql = "SELECT R.pathNo FROM BusRoute R WHERE R.route_no =:routeNo";
//		Query query = entityManager.createQuery(hql);
//		query.setParameter("routeNo",routeNo);
//		List<Integer> paths = query.getResultList();
//		for(int path:paths) {
//			deleteRoute(path);
//		}		
		busDetails = getBus(routeNo);
		entityManager.remove(busDetails);
	}

	@Override
	public void updateBusDetails(BusDetails busDetails) {
		busType = getBusInfo(busDetails.getNewBusName().getBusName());
		if(busType == null) {
			addNewBusDetails(busType);
		}
		entityManager.merge(busType);
	}

	@Override
	public BusDetails getBus(int routeNo) {
		return entityManager.find(BusDetails.class, routeNo);
	}
	
	@Override
	public List<BusDetails> getAllBusDetails(){
		String hql = "FROM BusDetails";
		Query query = entityManager.createQuery(hql);
		List<BusDetails> busDetailsList = query.getResultList();
		return busDetailsList;
	}

	@Override
	public int addRoute(BusRoute newRoute) {
		try {
			busDetails = getBus(newRoute.getNewBusDetails().getRouteNo());
			if(busDetails == null) {
				addBusDetails(newRoute.getNewBusDetails());
			}
			busRoute = getRouteDetails(newRoute.getPathNo());
			if(busRoute == null) {
				entityManager.merge(newRoute);// .persist(newRoute); 
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void deleteRoute(int pathNo) {
		System.out.println("Removing route no --->>>> " + pathNo);
		busRoute = getRouteDetails(pathNo);
		System.out.println("Removing route --->>>> " + busRoute.toString());
		entityManager.remove(busRoute);
	}

	@Override
	public List<String[]> searchBus(String src, String dest) {
		String hql = "SELECT R.pathNo FROM BusRoute R WHERE R.source =:src and R.destination=:dest";
		Query query = entityManager.createQuery(hql);
		query.setParameter("src",src);
		query.setParameter("dest", dest);
		List<Integer> paths = query.getResultList();
		List<String[]> availableBus = new ArrayList<String[]>();
		for(int path:paths) {
			BusRoute busRoute = entityManager.find(BusRoute.class, path);
			String[] str = new String[12];
			str[0]=""+path;
			str[1]=busRoute.getSource();
			str[2]=busRoute.getDestination();
			str[3]=""+busRoute.getNewBusDetails().getRouteNo();
			str[4]=""+busRoute.getDistance();
			str[5]=busRoute.getStartTime();
			str[6]=busRoute.getReachTime();
			str[7]=""+busRoute.getFare();
			str[8]=""+busRoute.getBusNo();
			str[9]=busRoute.getNewBusDetails().getNewBusName().getBusName();
			str[10]=busRoute.getNewBusDetails().getNewBusName().getBusType();
			str[11]=busRoute.getNewBusDetails().getNewBusName().getBusStatus();
			availableBus.add(str);
		}
		return availableBus;
	}

	@Override 
	public BusRoute getRouteDetails(int pathNo) {
		return entityManager.find(BusRoute.class, pathNo);
	}
	
	@Override
	public BusRoute getRouteDetails(int busNo, String source, String destination) {
		String hql = "FROM BusRoute R where R.source =: src and R.destination=:dest and R.busNo =: busNo";
		Query query = entityManager.createQuery(hql);
		query.setParameter("src",source);
		query.setParameter("dest", destination);	
		query.setParameter("busNo", busNo);
		return (BusRoute)query.getSingleResult();
	}

	@Override
	public void updateBusRoute(BusRoute busRoute) {
		busDetails = getBus(busRoute.getNewBusDetails().getRouteNo());
		if(busDetails == null) {
			addBusDetails(busDetails);
		}
		entityManager.merge(busRoute);
	}
	
	@Override
	public List<BusRoute> getAllBusRoute(){
		String hql = "FROM BusRoute";
		Query query = entityManager.createQuery(hql);
		List<BusRoute> busRouteList = query.getResultList();
		return busRouteList;
	}

	
}
