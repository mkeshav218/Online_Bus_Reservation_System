package com.microservice.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Reservation")
@Component
//@Scope("prototype")
public class Reservation {

	@Id 
	@Column(length=5)
	@GeneratedValue
	private int ticketNo;
	//@Temporal(TemporalType.DATE)
	private LocalDate bookingDate;
	//@Temporal(TemporalType.DATE)
	private LocalDate dateOfJourney;
	@Column(length=10)
	private int seatNo;
	@Column(length=10)
	private String ticketStatus;
	//@Temporal(TemporalType.DATE)
	private LocalDate cancellationDate;
	@Column(length=10)
	private double refundAmount;
	
	@Column(length=10)
	private int pathNo;
	
	@Column(length=30)
	private String email;
	
	public Reservation() {
		super();
	}


	public int getTicketNo() {
		return ticketNo;
	}


	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}


	public LocalDate getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}


	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}


	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}


	public int getSeatNo() {
		return seatNo;
	}


	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}


	public String getTicketStatus() {
		return ticketStatus;
	}


	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}


	public LocalDate getCancellationDate() {
		return cancellationDate;
	}


	public void setCancellationDate(LocalDate cancellationDate) {
		this.cancellationDate = cancellationDate;
	}


	public double getRefundAmount() {
		return refundAmount;
	}


	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}


	public int getPathNo() {
		return pathNo;
	}


	public void setPathNo(int pathNo) {
		this.pathNo = pathNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Reservation [ticketNo=" + ticketNo + ", bookingDate=" + bookingDate + ", dateOfJourney=" + dateOfJourney
				+ ", seatNo=" + seatNo + ", ticketStatus=" + ticketStatus + ", cancellationDate=" + cancellationDate
				+ ", refundAmount=" + refundAmount + ", pathNo=" + pathNo + ", email=" + email + "]";
	}	
	
	
	
}	