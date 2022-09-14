package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ServiceProxy;
import com.microservice.dto.BusDetailsDto;
import com.microservice.dto.BusInfoDto;
import com.microservice.dto.BusRouteDto;
import com.microservice.dto.GetBusRoute;
import com.microservice.dto.SearchBus;
import com.microservice.entity.BusDetails;
import com.microservice.entity.BusInfo;
import com.microservice.entity.BusRoute;
import com.microservice.pojo.Login;
import com.microservice.pojo.Registration;
import com.microservice.service.SearchService;

@RestController
@CrossOrigin
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	ServiceProxy proxy;
	
	@PostMapping("/add/bus")
	public ResponseEntity<BusInfo> addNewBusDetails(@RequestBody BusInfoDto busInfoDto){
		try {
			Login login = busInfoDto.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			BusInfo bus = searchService.addNewBusDetails(busInfoDto.getBusInfo());
			return new ResponseEntity<BusInfo>(bus, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PutMapping("/update/bus/status")
	public ResponseEntity<BusInfo> updateBusStatus(@RequestBody BusInfoDto updateBusDto ) {
		try {
			Login login = updateBusDto.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			BusInfo bus = searchService.updateBusStatus(updateBusDto.getBusInfo().getBusName(), updateBusDto.getBusInfo().getBusStatus());
			return new ResponseEntity<BusInfo>(bus, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/delete/businfo")
	public ResponseEntity<String> removeBus(@RequestBody BusInfoDto deleteBusInfo){
		try {
			Login login = deleteBusInfo.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			int res = searchService.deleteBus(deleteBusInfo.getBusInfo().getBusName());
			if(res == 1) {
				return new ResponseEntity<String>("Bus info removed successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occured in removing bus info", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/fetch/businfo")
	public ResponseEntity<BusInfo> getBus(@RequestBody BusInfoDto getBus){
		try {
			Login login = getBus.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<BusInfo>(searchService.getBusInfo(getBus.getBusInfo().getBusName()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/getallbusinfo")
	public ResponseEntity<List<BusInfo>> getAllBusType(@RequestBody BusInfoDto userInfo){
		try {
			Login login = userInfo.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<List<BusInfo>>(searchService.getAllBusType(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("/add/busdetails")
	public ResponseEntity<BusDetails> addBusDetails(@RequestBody BusDetailsDto busDetailsDto){
		try {
			Login login = busDetailsDto.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			BusDetails busDetails = searchService.addBusDetails(busDetailsDto.getBusDetails());
			return new ResponseEntity<BusDetails>(busDetails, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/delete/busdetails")
	public ResponseEntity<String> removeBus(@RequestBody BusDetailsDto deleteBusDetailsDto){
		try {
			Login login = deleteBusDetailsDto.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			int res = searchService.deleteBusDetails(deleteBusDetailsDto.getBusDetails().getRouteNo());
			if(res == 1) {
				return new ResponseEntity<String>("Bus info removed successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occured in removing bus info", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/busdetails")
	public ResponseEntity<BusDetails> updateBusDetails(@RequestBody BusDetailsDto updateBusDetailsDto) {
		try {
			Login login = updateBusDetailsDto.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			BusDetails busDetailsObj = searchService.updateBusDetails(updateBusDetailsDto.getBusDetails());
			return new ResponseEntity<BusDetails>(busDetailsObj, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/fetch/busdetail")
	public ResponseEntity<BusDetails> getBus(@RequestBody BusDetailsDto getBusDetails){
		try {
			Login login = getBusDetails.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<BusDetails>(searchService.getBus(getBusDetails.getBusDetails().getRouteNo()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getallbusdetails")
	public ResponseEntity<List<BusDetails>> getAllBusDetails(@RequestBody BusDetailsDto getBusDetails){

		try {
			Login login = getBusDetails.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<List<BusDetails>>(searchService.getAllBusDetails(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add/busroute")
	public ResponseEntity<BusRoute> addBusRoute(@RequestBody BusRouteDto addNewRoute){
		try {
			Login login = addNewRoute.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			BusRoute busRoute = searchService.addRoute(addNewRoute.getBusRoute());
			return new ResponseEntity<BusRoute>(busRoute, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/busroute")
	public ResponseEntity<String> removeBusRoute(@RequestBody BusRouteDto deleteBusRoute){
		try {
			Login login = deleteBusRoute.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			int res = searchService.deleteRoute(deleteBusRoute.getBusRoute().getPathNo());
			if(res == 1) {
				return new ResponseEntity<String>("Bus Route removed successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occured in removing bus route", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/searchbus")
	public ResponseEntity<List<String[]>> searchBus(@RequestBody SearchBus searchBus){
		try {
			return new ResponseEntity<List<String[]>>(searchService.searchBus(searchBus.getSrc(), searchBus.getDest(), searchBus.getFromTime(), searchBus.getToTime()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getBusroute")
	public ResponseEntity<BusRoute> getBus(@RequestBody BusRouteDto getBusRoute){
		try {
			Login login = getBusRoute.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<BusRoute>(searchService.getRouteDetails(getBusRoute.getBusRoute().getPathNo()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/get/BusRoute")
	public ResponseEntity<List<BusRoute>> getBus(@RequestBody GetBusRoute getBusRoute){
		try {
			return new ResponseEntity<List<BusRoute>>(searchService.getRouteDetails(getBusRoute.getSource(),getBusRoute.getDestination()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/busroute")
	public ResponseEntity<BusRoute> updateBusRoute(@RequestBody BusRouteDto updateBusRoute) {
		try {
			Login login = updateBusRoute.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			BusRoute res = searchService.updateBusRoute(updateBusRoute.getBusRoute());
			return new ResponseEntity<BusRoute>(res, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getallbusroute")
	public ResponseEntity<List<BusRoute>> getAllBusRoute(@RequestBody BusRouteDto getAllRoutes){
		try {
			Login login = getAllRoutes.getLogin();
			ResponseEntity<Registration> user = proxy.getRegisteredUser(login);
			Registration registration = user.getBody();
			if(registration==null) {
				return new ResponseEntity("User is not registered",HttpStatus.UNAUTHORIZED);
			}
			if(!registration.getRole().equalsIgnoreCase("ADMIN")) {
				return new ResponseEntity("User is not authorized",HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<List<BusRoute>>(searchService.getAllBusRoute(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
 
}
