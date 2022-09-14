package com.microservice.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ServiceProxy;
import com.microservice.dto.BusRouteDto;
import com.microservice.dto.ReservationDto;
import com.microservice.dto1.Deposit;
import com.microservice.dto1.GetBusRoute;
import com.microservice.dto1.GetDeleteBusRoute;
import com.microservice.entity.Reservation;
import com.microservice.pojo.BusRoute;
import com.microservice.pojo.Login;
import com.microservice.pojo.Registration;
import com.microservice.pojo.UpdateWallet;
import com.microservice.service.ReservationService;

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
	public ResponseEntity<Reservation> addReservation(@RequestBody ReservationDto reservationDto){
		try {
			Login login = reservationDto.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.BAD_REQUEST);
			}
			System.out.println(registration);

			BusRoute busRoute = new BusRoute();
			busRoute.setPathNo(reservationDto.getReservationInfo().getPathNo());
			BusRouteDto busRouteDto = new BusRouteDto();
			busRouteDto.setLogin(login);
			busRouteDto.setBusRoute(busRoute);
			ResponseEntity<BusRoute> busRouteEntity = proxy.getBus(busRouteDto);
			BusRoute busRoute2 = busRouteEntity.getBody();
			if(busRoute2==null) {
				return new ResponseEntity("Bus Route Not found",HttpStatus.BAD_REQUEST);
			}
			System.out.println(busRoute2);
			if(registration.getWallet()< busRoute2.getFare()) {
				return new ResponseEntity("Low Wallet Amount",HttpStatus.BAD_REQUEST);
			}
			UpdateWallet updateWallet = new UpdateWallet();
			updateWallet.setEmail(reservationDto.getLogin().getEmail());
			updateWallet.setPassword(reservationDto.getLogin().getPassword());
			updateWallet.setAmount(busRoute2.getFare());
			ResponseEntity<String> responseAfterWithdraw = proxy.withdraw(updateWallet);
			Reservation resv = reservationService.addReservation(reservationDto.getReservationInfo());
			return new ResponseEntity<Reservation>(resv,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/cancelticket")
	public ResponseEntity<String> cancelTicket(@RequestBody ReservationDto reservationDto) {
		try {
			Reservation reservation = reservationService.getReservation(reservationDto.getReservationInfo().getTicketNo());
			if(reservation==null) {
				return new ResponseEntity<String>("Ticket Not Found", HttpStatus.BAD_REQUEST);
			}
			BusRoute busRoute = new BusRoute();
			busRoute.setPathNo(reservation.getPathNo());
			BusRouteDto busRouteDto = new BusRouteDto();
			busRouteDto.setLogin(reservationDto.getLogin());
			busRouteDto.setBusRoute(busRoute);
			ResponseEntity<BusRoute> busRouteEntity = proxy.getBus(busRouteDto);
			BusRoute busRoute2 = busRouteEntity.getBody();
			int fare = busRoute2.getFare();
			UpdateWallet updateWallet = new UpdateWallet();
			updateWallet.setEmail(reservationDto.getLogin().getEmail());
			updateWallet.setPassword(reservationDto.getLogin().getPassword());
			updateWallet.setAmount(fare);
			LocalDate now = LocalDate.now();
			reservationService.cancelReservation(reservationDto.getReservationInfo().getTicketNo(),now , fare);
			ResponseEntity<String> responseAfterDeposit = proxy.addToWallet(updateWallet);
			return new ResponseEntity<String>("Ticket cancelled successfully, Deposited Amount = "+fare, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
//	
//	@GetMapping("/get/frequentroute")
//	public ResponseEntity<BusRoute> getMostFrequestRoute() {
//		
//		try {
//			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
//			int frequentRoute = reservationService.frequentTravelRoute();
//			getBusRoute.setPathNo(frequentRoute);
//			ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
//			if(response.getBody()!=null) {
//				return response;
//			}else {
//				return new ResponseEntity("No Reservation done till yet...!!",HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
//		}
//	} 
//	
//	
//	@GetMapping("/get/lastmonthprofit")
//	public ResponseEntity<Double> getLastMonthProfit(@RequestBody ProfitByMonth profitByMonth){
//		try {
//			List<Integer> routes = reservationService.lastMonthProfit(profitByMonth.getDate1(),profitByMonth.getDate2());
//			Double amount = 0.0;
//			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
//			for(int route:routes) {
//				getBusRoute.setPathNo(route);
//				amount += proxy.getBus(getBusRoute).getBody().getFare();
//			}
//			return new ResponseEntity<Double>(amount,HttpStatus.OK);
//		}catch (Exception e) {
//			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
//		}
//	}
//	
//	@GetMapping("/get/mostprefferedbus")
//	public ResponseEntity<String> getMostPrefferedBus() {
//		
//		try {
//			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
//			int frequentRoute = reservationService.mostPreferredBus();
//			getBusRoute.setPathNo(frequentRoute);
//			ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
//			if(response.getBody()!=null) {
//				return new ResponseEntity<String>(response.getBody().getNewBusDetails().getNewBusName().getBusName(), HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>("No Reservation done till yet...!!",HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
//		}
//	}
//	
//	@GetMapping("/get/dailyBooked")
//	public ResponseEntity<List<Reservation>> dailyBooked(@RequestBody DailyBooked dailyBooked){
//		try {
//			List<Reservation> reservations = reservationService.dailyBooked(dailyBooked.getDate());
//			return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
//		}catch (Exception e) {
//			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
//		}
//	}
//	
//	@GetMapping("/get/weeklyBooked")
//	public ResponseEntity<List<Reservation>> weeklyBooked(@RequestBody ProfitByMonth bookedByMonth) {
//		try {
//			List<Reservation> reservations = reservationService.weeklyBooked(bookedByMonth.getDate1(),bookedByMonth.getDate2());
//			return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
//		}catch (Exception e) {
//			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
//		}
//	}
//	
//	//todo:api for available seats
//	@GetMapping("/get-ticket-details")
//	public ResponseEntity<TicketDetails> getTicketDetails(@RequestBody CancelDto getTicketInfo){
//		try {
//			reservation = reservationService.getReservation(getTicketInfo.getTicketNo());
//			GetDeleteBusRoute getBusRoute = new GetDeleteBusRoute();
//			getBusRoute.setPathNo(reservation.getPathNo());
//			ResponseEntity<BusRoute> response  = proxy.getBus(getBusRoute);
//			TicketDetails ticketDetails = new TicketDetails();
//
//			ticketDetails.setTicketNo(reservation.getTicketNo());
//			ticketDetails.setSeatNo(reservation.getSeatNo());
//			ticketDetails.setSource(response.getBody().getSource());
//			ticketDetails.setDestination(response.getBody().getDestination());
//			ticketDetails.setDoj(reservation.getDateOfJourney());
//			ticketDetails.setBookingDate(reservation.getBookingDate());
//			ticketDetails.setEmail(reservation.getEmail());
//			ticketDetails.setFare(response.getBody().getFare());
//			ticketDetails.setBusName(response.getBody().getNewBusDetails().getNewBusName().getBusName());
//			ticketDetails.setBusNo(response.getBody().getBusNo());
//			ticketDetails.setStartTime(response.getBody().getStartTime());
//			ticketDetails.setReachTime(response.getBody().getReachTime());
//			ticketDetails.setBusType(response.getBody().getNewBusDetails().getNewBusName().getBusType());
//			
//			return new ResponseEntity<TicketDetails>(ticketDetails, HttpStatus.OK);
//		}catch (Exception e) {
//			return new ResponseEntity(e.getMessage(),HttpStatus.OK);
//		}
//	}
		

}
