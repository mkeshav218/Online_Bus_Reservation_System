package com.microservice.pojo;

import java.time.LocalDate;

public class TransactionBetweenDate {
	LocalDate date1;
	LocalDate date2;
	public TransactionBetweenDate() {
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
	@Override
	public String toString() {
		return "TransactionBetweenDate [date1=" + date1 + ", date2=" + date2 + "]";
	}
}
