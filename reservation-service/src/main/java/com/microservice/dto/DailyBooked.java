package com.microservice.dto;

import java.time.LocalDate;

public class DailyBooked {
	LocalDate date;

	public DailyBooked() {
		super();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
