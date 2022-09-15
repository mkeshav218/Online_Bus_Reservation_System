package com.microservice.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.microservice.entity.Reservation;

public interface ReservationService {
	public Reservation addReservation(Reservation obj);
	public void cancelReservation(int ticketNo, LocalDate cancelDate, double refundAmt);
	public Reservation getReservation(int ticketNo);

	public int frequentTravelRoute(); // return pathNo
	public HashMap<Integer, Integer> transactionBetweenDate(LocalDate date1,LocalDate date2);
	public List<Reservation> particularDayReservationList(LocalDate date); //return all current day bookings
	public List<Integer> getAvailableSeats(int pathNo,LocalDate doj);
}
