package com.microservice.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entity.Reservation;
import com.microservice.exception.BusServiceException;
import com.microservice.repository.ReservationRepo;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationRepo repo;

	@Override
	public Reservation addReservation(Reservation obj) {
		try {
			return repo.addReservation(obj);
		} catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public void cancelReservation(int ticketNo, LocalDate cancelDate, double refundAmt) {
		try {
			Reservation reservation = repo.getReservation(ticketNo);
			if(reservation.getTicketStatus().equals("CANCELLED")){
				throw new BusServiceException("Ticket already cancelled...!!");
			}
			if(reservation.getDateOfJourney().compareTo(cancelDate)>0) {
				repo.cancelReservation(ticketNo, cancelDate, refundAmt);
			}else {
				throw new BusServiceException("Ticket can't be cancelled...!!");
			}
		}catch(Exception e){
			throw new BusServiceException(e.getMessage());
		}
	}
	
	@Override
	public Reservation getReservation(int ticketNo) {
		try {
			return repo.getReservation(ticketNo);
		}catch (Exception e) {
			throw new BusServiceException("No Reservation Found");
		}
	}

	@Override
	public int frequentTravelRoute() {
		try {
			return repo.frequentTravelRoute(); 
		} catch (Exception e) {
			throw new BusServiceException("No Reservation done till yet!!");
		}
	}

	@Override
	public HashMap<Integer, Integer> transactionBetweenDate(LocalDate date1,LocalDate date2){
		try {
			return repo.transactionBetweenDate(date1, date2);
		}catch (Exception e) {
			throw new BusServiceException("No Reservation done till yet...so no profit....!!");
		}
	}

	@Override
	public List<Reservation> particularDayReservationList(LocalDate date) {
		try {
			return repo.particularDayReservationList(date);
		}catch (Exception e) {
			throw new BusServiceException("No Reservation done on "+date);
		}
	}

	@Override
	public List<Integer> getAvailableSeats(int pathNo,LocalDate doj){
		try {
			return repo.getAvailableSeats(pathNo, doj);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}


}
