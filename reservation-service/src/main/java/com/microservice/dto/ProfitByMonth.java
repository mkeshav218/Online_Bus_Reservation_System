package com.microservice.dto;

import java.time.LocalDate;

public class ProfitByMonth {
	LocalDate date1;
	LocalDate date2;
	public ProfitByMonth() {
		super();
	}
	public LocalDate getDate1() {
		return date1;
	}
	public void setDate1(LocalDate date1) {
		this.date1 = date1;
	}
	public LocalDate getDate2() {
		return date2;
	}
	public void setDate2(LocalDate date2) {
		this.date2 = date2;
	}
	
	
}
