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
import com.microservice.dto.BusInfoDto;
import com.microservice.dto.GetBusRoute;
import com.microservice.dto.GetDeleteBusDetails;
import com.microservice.dto.GetDeleteBusRoute;
import com.microservice.dto.GetDeleteBusType;
import com.microservice.dto.SearchBus;
import com.microservice.dto.UpdateBusDto;
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
	
	@GetMapping("/search")
	public String searchS() {
		return "Search-Service";
	}
	
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
	public ResponseEntity<BusInfo> updateBusStatus(@RequestBody UpdateBusDto updateBusDto ) {
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
			BusInfo bus = searchService.updateBusStatus(updateBusDto.getUpdateBus().getBusName(), updateBusDto.getUpdateBus().getBusStatus());
			return new ResponseEntity<BusInfo>(bus, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/delete/businfo")
	public ResponseEntity<String> removeBus(@RequestBody GetDeleteBusType busType){
		try {
			int res = searchService.deleteBus(busType.getBusName());
			if(res == 1) {
				return new ResponseEntity<String>("Bus info removed successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occured in removing bus info", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/businfo")
	public ResponseEntity<BusInfo> getBus(@RequestBody GetDeleteBusType busType){
		try {
			return new ResponseEntity<BusInfo>(searchService.getBusInfo(busType.getBusName()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/getallbustype")
	public ResponseEntity<List<BusInfo>> getAllBusType(){
		try {
			return new ResponseEntity<List<BusInfo>>(searchService.getAllBusType(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add/busdetails")
	public ResponseEntity<String> addBusDetails(@RequestBody BusDetails busDetails){
		try {
			int res = searchService.addBusDetails(busDetails);
			if(res == 1) {
				return new ResponseEntity<String>("Bus Details added successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Cannot add bus details",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@DeleteMapping("/delete/busdetails")
	public ResponseEntity<String> removeBus(@RequestBody GetDeleteBusDetails deleteBusDetails){
		try {
			int res = searchService.deleteBusDetails(deleteBusDetails.getRouteNo());
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
	public ResponseEntity<String> updateBusDetails(@RequestBody BusDetails updateBusDetails) {
		try {
			int res = searchService.updateBusDetails(updateBusDetails);
			if(res == 1) {
				return new ResponseEntity<String>("Bus-Details updated successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Bus-Details can't be updated...!!",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getbusdetails")
	public ResponseEntity<BusDetails> getBus(@RequestBody GetDeleteBusDetails getBusDetails){
		try {
			return new ResponseEntity<BusDetails>(searchService.getBus(getBusDetails.getRouteNo()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getallbusdetails")
	public ResponseEntity<List<BusDetails>> getAllBusDetails(){
		try {
			return new ResponseEntity<List<BusDetails>>(searchService.getAllBusDetails(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add/busroute")
	public ResponseEntity<String> addBusRoute(@RequestBody BusRoute busRoute){
		try {
			int res = searchService.addRoute(busRoute);
			if(res == 1) {
				return new ResponseEntity<String>("Bus Route added successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Bus-Route Already Present...!!",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/busroute")
	public ResponseEntity<String> removeBusRoute(@RequestBody GetDeleteBusRoute deleteBusRoute){
		try {
			int res = searchService.deleteRoute(deleteBusRoute.getPathNo());
			if(res == 1) {
				return new ResponseEntity<String>("Bus Route removed successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Error occured in removing bus route", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	//TODO::::::::::
	@GetMapping("/searchbus")
	public ResponseEntity<List<String[]>> searchBus(@RequestBody SearchBus searchBus){
		try {
			return new ResponseEntity<List<String[]>>(searchService.searchBus(searchBus.getSrc(), searchBus.getDest(), searchBus.getFromTime(), searchBus.getToTime(), searchBus.getTypeOfUser()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getBusroute")
	public ResponseEntity<BusRoute> getBus(@RequestBody GetDeleteBusRoute getBusRoute){
		try {
			return new ResponseEntity<BusRoute>(searchService.getRouteDetails(getBusRoute.getPathNo()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/get/BusRoute")
	public ResponseEntity<BusRoute> getBus(@RequestBody GetBusRoute getBusRoute){
		try {
			return new ResponseEntity<BusRoute>(searchService.getRouteDetails(getBusRoute.getBusNo(),getBusRoute.getSource(),getBusRoute.getDestination()), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/busroute")
	public ResponseEntity<String> updateBusRoute(@RequestBody BusRoute updateBusRoute) {
		try {
			int res = searchService.updateBusRoute(updateBusRoute);
			if(res == 1) {
				return new ResponseEntity<String>("Bus-Route updated successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Bus-Route can't be updated...!!",HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getallbusroute")
	public ResponseEntity<List<BusRoute>> getAllBusRoute(){
		try {
			return new ResponseEntity<List<BusRoute>>(searchService.getAllBusRoute(), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
 
}
