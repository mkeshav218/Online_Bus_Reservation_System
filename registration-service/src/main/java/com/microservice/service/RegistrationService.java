package com.microservice.service;

import java.time.LocalDate;

import com.microservice.entity.Registration;

public interface RegistrationService {
	public void addRegistration(Registration newRegister);
	public double getWalletAmount(String email,String password);//dashboard functionality
	public double depositMoney(String email,String password, double amount);//dashboard functionality
	public void withdrawMoney(String email,String password, double amount);
	public Registration updateUserDetails(String email,String password, LocalDate dob);//dashboard functionality
	public Registration updateUserDetails(String email,String password, String gender);//dashboard functionality
	public Registration updateUserDetails(String email,String password, LocalDate dob, String gender);//dashboard functionality
	public Registration updateUserPhone(String email,String password, String phone);//dashboard functionality
	
	//As of now, set password as "12345678" & notify user to update it
	public Registration setDefaultPassword(String email,String phone);
	public Registration updatePassword(String email, String oldPassword, String newPassword);

	public Registration isRegistered(String email, String password);
	//public List<Registration> noBookedTicket();
	
	public Registration userRegistrationDetails(String email,String password);
	public Registration updateBookedTicket(String email, String password, int noOfTicket);

}
