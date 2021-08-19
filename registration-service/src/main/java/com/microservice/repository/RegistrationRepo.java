package com.microservice.repository;


import java.time.LocalDate;

import com.microservice.entity.Registration;

public interface RegistrationRepo {
	public void addRegistration(Registration newRegister);
	public double getWalletAmount(String email);
	public void updateWallet(String email, double amount);
	public void updateUserDetails(String email, LocalDate dob);
	public void updateUserDetails(String email, String gender);
	public void updateUserDetails(String email, LocalDate dob, String gender);
	public void updateUserPhone(String email, String phone);
	public void updatetPassword(String email, String newPassword);
	public String setDefaultPassword(String email);
	//public List<Registration> noBookedTicket();
		//public String login(String emailId);
	public Registration userRegistrationDetails(String email);
	public void updateBookedTicket(String email, int noOfTicket);

}
