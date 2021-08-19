package com.microservice.service;

import java.time.LocalDate;
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
	public void addReservation(Reservation obj) {
		try {
			repo.addReservation(obj);
		} catch (Exception e) {
			throw new BusServiceException("Ticket cannot be booked!!");
		}
	}

	@Override
	public void cancelReservation(int ticketNo, LocalDate cancelDate, double refundAmt) {
		try {
			Reservation reservation = repo.getReservation(ticketNo);
			if(reservation.getTicketStatus().equals("cancelled")){
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
	public List<Integer> lastMonthProfit(LocalDate date1,LocalDate date2){
		try {
			return repo.lastMonthProfit(date1, date2);
		}catch (Exception e) {
			throw new BusServiceException("No Reservation done till yet...so no profit....!!");
		}
	}

	@Override
	public int mostPreferredBus() {
		try {
			return repo.mostPreferredBus(); 
		} catch (Exception e) {
			throw new BusServiceException("No Reservation done till yet!!");
		}
	}

	@Override
	public List<Reservation> dailyBooked(LocalDate date) {
		try {
			return repo.dailyBooked(date);
		}catch (Exception e) {
			throw new BusServiceException("No Reservation done on "+date);
		}
	}

	@Override
	public List<Reservation> weeklyBooked(LocalDate date1, LocalDate date2) {
		try {
			return repo.weeklyBooked(date1, date2);
		}catch (Exception e) {
			throw new BusServiceException("No Reservation done between "+date1 +" and " + date2);
		}
	}

	@Override
	public List<Integer> availableSeats(int busNo, String doj, String src, String dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation searchTicket(int ticketNo) {
		try {
			return repo.searchTicket(ticketNo);
		}catch (Exception e) {
			throw new BusServiceException("No Ticket Found");
		}
	}



}
