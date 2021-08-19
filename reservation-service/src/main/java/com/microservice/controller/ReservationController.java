package com.microservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ServiceProxy;
import com.microservice.dto.CancelDto;
import com.microservice.dto.DailyBooked;
import com.microservice.dto.ProfitByMonth;
import com.microservice.dto.ReservationDto;
import com.microservice.dto.TicketDetails;
import com.microservice.dto1.BusRoute;
import com.microservice.dto1.Deposit;
import com.microservice.dto1.GetBusRoute;
import com.microservice.dto1.GetDeleteBusRoute;
import com.microservice.dto1.Login;
import com.microservice.dto1.Registration;
import com.microservice.entity.Reservation;
import com.microservice.service.ReservationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin
public class ReservationController {

	@Autowired
	ServiceProxy proxy;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	GetDeleteBusRoute getBusRoute;
	
	@Autowired
	GetBusRoute getRoute;
	
	@Autowired
	Reservation reservation;
	
	@Autowired
	Deposit deposit;
	
	@PostMapping("/add/reservation")
	public ResponseEntity<String> addReservation(@RequestBody ReservationDto reservationDto){
		try {
			Login login = new Login();
			login.setUserName(reservationDto.getEmail());
			login.setPassword(reservationDto.getPassword());
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			//fetch pathNo
			getRoute.setBusNo(reservationDto.getBusNo());
			getRoute.setSource(reservationDto.getSource());
			getRoute.setDestination(reservationDto.getDestination());
			ResponseEntity<BusRoute> response  = proxy.getBus(getRoute);
			Reservation resv = new Reservation();
			LocalDate now = LocalDate.now();
			resv.setBookingDate(now);
			resv.setDateOfJourney(reservationDto.getDoj());
			resv.setSeatNo(reservationDto.getSeatNo());
			resv.setTicketStatus("Booked");
			resv.setPathNo(response.getBody().getPathNo());
			resv.setEmail(user.getBody().getEmail());
			reservationService.addReservation(resv);
			//todo : send mail
			return new ResponseEntity<String>("Reservation Successful",HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/cancelticket")
	public ResponseEntity<String> cancelTicket(@RequestBody CancelDto cancelDto) {
		try {
			Login login = new Login();
			login.setUserName(cancelDto.getEmail());
			login.setPassword(cancelDto.getPassword());
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Reservation reservation = reservationService.getReservation(cancelDto.getTicketNo());
			if(user.getBody().getEmail().equals(reservation.getEmail())) {
				getBusRoute.setPathNo(reservation.getPathNo());
				ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
				int fare = response.getBody().getFare();
				LocalDate now = LocalDate.now();
				reservationService.cancelReservation(cancelDto.getTicketNo(),now , fare);
				deposit.setUserName(cancelDto.getEmail());
				deposit.setPassword(cancelDto.getPassword());
				deposit.setAmount(fare);
				proxy.depositMoney(deposit);
				return new ResponseEntity<String>("Reservation cancelled successfully, Deposited Amount = "+fare, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Invalid user id", HttpStatus.OK);
			}

			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/frequentroute")
	public ResponseEntity<BusRoute> getMostFrequestRoute() {
		
		try {
			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
			int frequentRoute = reservationService.frequentTravelRoute();
			getBusRoute.setPathNo(frequentRoute);
			ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
			if(response.getBody()!=null) {
				return response;
			}else {
				return new ResponseEntity("No Reservation done till yet...!!",HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
		}
	} 
	
	
	@GetMapping("/get/lastmonthprofit")
	public ResponseEntity<Double> getLastMonthProfit(@RequestBody ProfitByMonth profitByMonth){
		try {
			List<Integer> routes = reservationService.lastMonthProfit(profitByMonth.getDate1(),profitByMonth.getDate2());
			Double amount = 0.0;
			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
			for(int route:routes) {
				getBusRoute.setPathNo(route);
				amount += proxy.getBus(getBusRoute).getBody().getFare();
			}
			return new ResponseEntity<Double>(amount,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/mostprefferedbus")
	public ResponseEntity<String> getMostPrefferedBus() {
		
		try {
			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
			int frequentRoute = reservationService.mostPreferredBus();
			getBusRoute.setPathNo(frequentRoute);
			ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
			if(response.getBody()!=null) {
				return new ResponseEntity<String>(response.getBody().getNewBusDetails().getNewBusName().getBusName(), HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("No Reservation done till yet...!!",HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/dailyBooked")
	public ResponseEntity<List<Reservation>> dailyBooked(@RequestBody DailyBooked dailyBooked){
		try {
			List<Reservation> reservations = reservationService.dailyBooked(dailyBooked.getDate());
			return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/weeklyBooked")
	public ResponseEntity<List<Reservation>> weeklyBooked(@RequestBody ProfitByMonth bookedByMonth) {
		try {
			List<Reservation> reservations = reservationService.weeklyBooked(bookedByMonth.getDate1(),bookedByMonth.getDate2());
			return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
		}
	}
	
	//todo:api for available seats
	@GetMapping("/get-ticket-details")
	public ResponseEntity<TicketDetails> getTicketDetails(@RequestBody CancelDto getTicketInfo){
		try {
			reservation = reservationService.getReservation(getTicketInfo.getTicketNo());
			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
			getBusRoute.setPathNo(reservation.getPathNo());
			ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
			TicketDetails ticketDetails = new TicketDetails();

			ticketDetails.setTicketNo(reservation.getTicketNo());
			ticketDetails.setSeatNo(reservation.getSeatNo());
			ticketDetails.setSource(response.getBody().getSource());
			ticketDetails.setDestination(response.getBody().getDestination());
			ticketDetails.setDoj(reservation.getDateOfJourney());
			ticketDetails.setBookingDate(reservation.getBookingDate());
			ticketDetails.setEmail(reservation.getEmail());
			ticketDetails.setFare(response.getBody().getFare());
			ticketDetails.setBusName(response.getBody().getNewBusDetails().getNewBusName().getBusName());
			ticketDetails.setBusNo(response.getBody().getBusNo());
			ticketDetails.setStartTime(response.getBody().getStartTime());
			ticketDetails.setReachTime(response.getBody().getReachTime());
			ticketDetails.setBusType(response.getBody().getNewBusDetails().getNewBusName().getBusType());
			
			return new ResponseEntity<TicketDetails>(ticketDetails, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
		}
	}
		

}
