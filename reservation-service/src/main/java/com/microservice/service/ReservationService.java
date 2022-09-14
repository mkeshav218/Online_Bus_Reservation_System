package com.microservice.service;

import java.time.LocalDate;
import java.util.List;

import com.microservice.entity.Reservation;

public interface ReservationService {
	public Reservation addReservation(Reservation obj);
	public void cancelReservation(int ticketNo, LocalDate cancelDate, double refundAmt);
	public Reservation getReservation(int ticketNo);

	public int frequentTravelRoute(); // return pathNo
	public List<Integer> lastMonthProfit(LocalDate date1,LocalDate date2);
	public int mostPreferredBus();
	public List<Reservation> dailyBooked(LocalDate date); //return all current day bookings
	public List<Reservation> weeklyBooked(LocalDate date1, LocalDate date2);
	public List<Integer> availableSeats(int busNo, String doj,String src, String dest);
	public Reservation searchTicket(int ticketNo);
}
