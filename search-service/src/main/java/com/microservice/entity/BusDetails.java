package com.microservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Bus_Details")
@Component
public class BusDetails {
	
	@Id
	@Column(length=20)
	private int routeNo;
	
	@ManyToOne(cascade = CascadeType.DETACH) 
	@JoinColumn(name="new_bus_name")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BusInfo newBusName;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) 
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

	public BusInfo getNewBusName() {
		return newBusName;
	}

	public void setNewBusName(BusInfo newBusName) {
		this.newBusName = newBusName;
	}

	public Set<BusRoute> getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(Set<BusRoute> busRoute) {
		this.busRoute = busRoute;
	}

	@Override
	public String toString() {
		return "BusDetails [routeNo=" + routeNo + ", newBusName=" + newBusName + ", busRoute=" + busRoute + "]";
	}
	
	
	
}

