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
import com.microservice.dto.UpdateWallet;
import com.microservice.dto.Login;
import com.microservice.dto.UpdateBookedTicket;
import com.microservice.dto.UpdatePassword;
import com.microservice.dto.UserDetails;
import com.microservice.dto.UserDob;
import com.microservice.dto.UserGender;
import com.microservice.dto.UserPhone;
import com.microservice.entity.Registration;
import com.microservice.service.RegistrationService;

@RestController
@CrossOrigin
public class RegistrationController {


	private Logger logger=LoggerFactory.getLogger(this.getClass());  

	@Autowired
	RegistrationService service;

	@PostMapping("/addregistration")
	public ResponseEntity<String> addRegistration(@RequestBody Registration newRegister) {
		try{
			service.addRegistration(newRegister);
			return new ResponseEntity<String>("Registration Successful", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/getRegisteredUser")
	public ResponseEntity<Registration> getRegisteredUser(@RequestBody Login login){
		try {
			Registration registration = service.userRegistrationDetails(login.getEmail(),login.getPassword());
			return new ResponseEntity<Registration>(registration, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(null, HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Registration> login(@RequestBody Login loginDto){
		try {
			Registration registeredUser = service.isRegistered(loginDto.getEmail(), loginDto.getPassword());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getbalance")
	public ResponseEntity<Double> getWalletAmount(@RequestBody Login login) {
		try {
			double amount = service.getWalletAmount(login.getEmail(), login.getPassword());
			return new ResponseEntity<Double>(amount, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/addtowallet")
	public ResponseEntity<String> depositMoney(@RequestBody UpdateWallet deposit) {
		try {
			double updatedAmount = service.depositMoney(deposit.getEmail(),deposit.getPassword(),deposit.getAmount());
			return new ResponseEntity<String>(deposit.getAmount() + " added to wallet, Updated amount : " + updatedAmount, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<String> withdrawMoney(@RequestBody UpdateWallet deposit) {
		try {
			service.withdrawMoney(deposit.getEmail(),deposit.getPassword(),deposit.getAmount());
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/dob")
	public ResponseEntity<Registration> updateUserDob(@RequestBody UserDob userDob) {
		try {
			Registration registeredUser = service.updateUserDetails(userDob.getEmail(), userDob.getPassword(), userDob.getDob());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/gender")
	public ResponseEntity<Registration> updateUserGender(@RequestBody UserGender userGender) {
		try {
			Registration registeredUser = service.updateUserDetails(userGender.getEmail(), userGender.getPassword(), userGender.getGender());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/dob/gender")
	public ResponseEntity<Registration> updateUser(@RequestBody UserDetails userDetails) {
		try {
			Registration registeredUser = service.updateUserDetails(userDetails.getEmail(), userDetails.getPassword(),userDetails.getDob(), userDetails.getGender());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/phone")
	public ResponseEntity<Registration> updateUserPhone(@RequestBody UserPhone userPhone) {
		try {
			Registration registeredUser = service.updateUserPhone(userPhone.getEmail(), userPhone.getPassword(),userPhone.getPhone());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/resetpassword")
	public ResponseEntity<Registration> setDefaultPassword(@RequestBody DefaultPassword defaultPassword) {
		try {
			Registration registeredUser = service.setDefaultPassword(defaultPassword.getEmail(),defaultPassword.getPhone());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/password")
	public ResponseEntity<Registration> setPassword(@RequestBody UpdatePassword updatePassword) {
		try {
			Registration registeredUser = service.updatePassword(updatePassword.getEmail(),updatePassword.getOldPassword(),updatePassword.getNewPassword());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/update/ticketcount")
	public ResponseEntity<Registration> updateBookedTicket(@RequestBody UpdateBookedTicket updateBookedTicket){
		try {
			Registration registeredUser = service.updateBookedTicket(updateBookedTicket.getEmail(), updateBookedTicket.getPassword(), updateBookedTicket.getNoOfTicket());
			return new ResponseEntity<Registration>(registeredUser, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
//	public ResponseEntity fallbackRegistration() {
//		return new ResponseEntity("Handled By Hystrix",HttpStatus.OK);
//	}
	
//	@GetMapping("/fault")
//	@HystrixCommand(fallbackMethod = "fallbackRegistration")
//	public ResponseEntity getRegisteredUser(){
//		throw new RuntimeException();
//	}

}
