package com.microservice.repository;


import java.time.LocalDate;

import com.microservice.entity.Registration;

public interface RegistrationRepo {
	public void addRegistration(Registration newRegister);
	public double getWalletAmount(String email);
	public double updateWallet(String email, double amount);
	public Registration updateUserDetails(String email, LocalDate dob);
	public Registration updateUserDetails(String email, String gender);
	public Registration updateUserDetails(String email, LocalDate dob, String gender);
	public Registration updateUserPhone(String email, String phone);
	public Registration updatetPassword(String email, String newPassword);
	public Registration setDefaultPassword(String email);
	//public List<Registration> noBookedTicket();
		//public String login(String emailId);
	public Registration userRegistrationDetails(String email);
	public Registration updateBookedTicket(String email, int noOfTicket);

}
