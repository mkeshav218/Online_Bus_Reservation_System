package com.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dto.DefaultPassword;
import com.microservice.dto.Deposit;
import com.microservice.dto.Login;
import com.microservice.dto.UpdateBookedTicket;
import com.microservice.dto.UpdatePassword;
import com.microservice.dto.UserDetails;
import com.microservice.dto.UserDob;
import com.microservice.dto.UserGender;
import com.microservice.dto.UserPhone;
import com.microservice.entity.Registration;
import com.microservice.service.RegistrationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin
public class RegistrationController {


	private Logger logger=LoggerFactory.getLogger(this.getClass());  

	@Autowired
	RegistrationService service;

	
	public ResponseEntity fallbackRegistration() {
		return new ResponseEntity("Handled By Hystrix",HttpStatus.OK);
	}

	
	@PostMapping("/getRegisteredUser")
	@HystrixCommand(fallbackMethod = "fallbackRegistration")
	public ResponseEntity<Registration> getRegisteredUser(@RequestBody Login login){
		try {
			Registration registration = service.userRegistrationDetails(login.getUserName(),login.getPassword());
			return new ResponseEntity<Registration>(registration, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/addregistration")
	public ResponseEntity<String> addRegistration(@RequestBody Registration newRegister) {
		try{
			int res = service.addRegistration(newRegister);
			if(res == 1) {
				logger.info("{}", newRegister);  
				return new ResponseEntity<String>("Registration Successful", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("User already exist", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getbalance")
	public ResponseEntity<Double> getWalletAmount(@RequestBody Login login) {
		try {
			double amount = service.getWalletAmount(login.getUserName(), login.getPassword());
			return new ResponseEntity<Double>(amount, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> depositMoney(@RequestBody Deposit deposit) {
		try {
			service.depositMoney(deposit.getUserName(),deposit.getPassword(),deposit.getAmount());
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<String> withdrawMoney(@RequestBody Deposit deposit) {
		try {
			service.withdrawMoney(deposit.getUserName(),deposit.getPassword(),deposit.getAmount());
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/dob")
	public ResponseEntity<String> updateUserDob(@RequestBody UserDob userDob) {
		try {
			service.updateUserDetails(userDob.getUserName(), userDob.getPassword(), userDob.getDob());
			return new ResponseEntity<String>("Date-of-Bith updated successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/gender")
	public ResponseEntity<String> updateUserGender(@RequestBody UserGender userGender) {
		try {
			service.updateUserDetails(userGender.getUserName(), userGender.getPassword(), userGender.getGender());
			return new ResponseEntity<String>("Gender updated successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/dob/gender")
	public ResponseEntity<String> updateUser(@RequestBody UserDetails userDetails) {
		try {
			service.updateUserDetails(userDetails.getUserName(), userDetails.getPassword(),userDetails.getDob(), userDetails.getGender());
			return new ResponseEntity<String>("Date-of-Bith & Gender updated successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/phone")
	public ResponseEntity<String> updateUserPhone(@RequestBody UserPhone userPhone) {
		try {
			service.updateUserDetails(userPhone.getUserName(), userPhone.getPassword(),userPhone.getPhone());
			return new ResponseEntity<String>("Phone number updated successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/defaultpassword")
	public ResponseEntity<String> setDefaultPassword(@RequestBody DefaultPassword defaultPassword) {
		try {
			String curPassword = service.setDefaultPassword(defaultPassword.getUserName(),defaultPassword.getPhone());
			return new ResponseEntity<String>("Default password set successfully, Your password is : " + curPassword, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/password")
	public ResponseEntity<String> setPassword(@RequestBody UpdatePassword updatePassword) {
		try {
			service.updatePassword(updatePassword.getUserName(),updatePassword.getOldPassword(),updatePassword.getNewPassword());
			return new ResponseEntity<String>("Password updated successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping("/update/ticketcount")
	public ResponseEntity<String> updateBookedTicket(@RequestBody UpdateBookedTicket updateBookedTicket){
		try {
			service.updateBookedTicket(updateBookedTicket.getUserName(), updateBookedTicket.getPassword(), updateBookedTicket.getNoOfTicket());
			return new ResponseEntity<String>("Ticket count updated successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
