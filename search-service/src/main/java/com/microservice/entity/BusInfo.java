package com.microservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="Bus_Info")
@Component
public class BusInfo {
	@Id
	@Column(length=20)
	private String busName;
	@Column(length=20)
	private String busType;
	@Column(length=20)
	private String busStatus;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,orphanRemoval = true) 
	private Set<BusDetails> busDetails;

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusStatus() {
		return busStatus;
	}

	public void setBusStatus(String busStatus) {
		this.busStatus = busStatus;
	}

	public Set<BusDetails> getBusDetails() {
		return busDetails;
	}

	public void setBusDetails(Set<BusDetails> busDetails) {
		this.busDetails = busDetails;
	}

	@Override
	public String toString() {
		return "BusType [busName=" + busName + ", busType=" + busType + ", busStatus=" + busStatus + ", busDetails="
				+ busDetails + "]";
	}


	
}
