package com.microservice.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entity.Registration;
import com.microservice.exception.BusServiceException;
import com.microservice.repository.RegistrationRepo;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationRepo repo;
	
	@Autowired
	Registration registration;

	@Override
	public int addRegistration(Registration newRegister) {
		try {
			repo.addRegistration(newRegister);
			return 1;
		}catch (Exception e) {
			throw new BusServiceException("User already exist!!");
		}
	}

	@Override
	public double getWalletAmount(String email,String password) {
		try {
			repo.userRegistrationDetails(email);
			return repo.getWalletAmount(email);
		}catch (Exception e) {
			throw new BusServiceException("Cannot fetch wallet amount");
		}
	}

	@Override
	public void depositMoney(String email,String password, double amount) {
		try {
			double curAmount = getWalletAmount(email,password);
			curAmount += amount;
			repo.updateWallet(email, curAmount);
		}catch (Exception e) {
			throw new BusServiceException("Cannot add money to wallet");
		}
	}
	
	@Override
	public void withdrawMoney(String email,String password, double amount) {
		try {
			double curAmount = getWalletAmount(email,password);
			curAmount -= amount;
			if(curAmount>=0) {
				repo.updateWallet(email, curAmount);
			}else {
				throw new BusServiceException("Low wallet amount");
			}
		}catch (Exception e) {
			throw new BusServiceException("Low wallet amount");
		}
	}

	@Override
	public void updateUserDetails(String email,String password, LocalDate dob) {
		try {
			repo.userRegistrationDetails(email);
			repo.updateUserDetails(email, dob);
		}catch (Exception e) {
			throw new BusServiceException("Cannot update DateOfBirth...Error Occured!!");
		}
	}

	@Override
	public void updateUserDetails(String email,String password, String gender) {
		try {
			repo.userRegistrationDetails(email);
			repo.updateUserDetails(email, gender);
		}catch (Exception e) {
			throw new BusServiceException("Cannot update Gender...Error Occured!!");
		}
	}

	@Override
	public void updateUserDetails(String email,String password, LocalDate dob, String gender) {
		try {
			repo.userRegistrationDetails(email);
			repo.updateUserDetails(email, dob,gender);
		}catch (Exception e) {
			throw new BusServiceException("Cannot update DateOfBirth & Gender...Error Occured!!");
		}
	}

	@Override
	public void updateUserPhone(String email,String password, String phone) {
		try {
			repo.userRegistrationDetails(email);
			repo.updateUserPhone(email, phone);
		}catch (Exception e) {
			throw new BusServiceException("Cannot update PhoneNo...Error Occured!!");
		}
	}
	
	@Override
	public String setDefaultPassword(String email,String phone) {
		try {
			registration = repo.userRegistrationDetails(email);
			if(phone.equalsIgnoreCase(registration.getPhone())) {
				return repo.setDefaultPassword(email);
			}else {
				throw new BusServiceException("Phone no didn't match");
			}
		}catch (Exception e) {
			throw new BusServiceException("Cannot update Password...Error Occured!!");
		}
	}


	@Override
	public void updatePassword(String email, String oldPassword, String newPassword) {
		try {
			registration = userRegistrationDetails(email, oldPassword);
			if(oldPassword.equalsIgnoreCase(registration.getPassword())) {
				repo.updatetPassword(email, newPassword);
			}else {
				throw new BusServiceException("Phone no didn't match");
			}
		}catch (Exception e) {
			throw new BusServiceException("Cannot update Password...Error Occured!!");
		}
	}

	@Override
	public boolean isRegistered(String email, String password) {
		try {
			registration = userRegistrationDetails(email, password);
			return true;
		}catch (Exception e) {
			throw new BusServiceException("User Not Registered!!");
		}
	}

	@Override
	public Registration userRegistrationDetails(String email, String password) {
		try {
			registration = repo.userRegistrationDetails(email);
			if(registration==null) {
				throw new BusServiceException("User "+ email +" Not Registered");
			}
			if(registration.getPassword().equals(password)) {
				return registration;
			}else {
				throw new BusServiceException("Userid & Password did Not match!!");
			}
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public void updateBookedTicket(String email, String password, int noOfTicket) {
		try {
			registration = userRegistrationDetails(email, password);
			registration.setNoOfBookedTicket(noOfTicket + registration.getNoOfBookedTicket());
			repo.updateBookedTicket(email, noOfTicket);
		}catch (Exception e) {
			throw new BusServiceException("Error occurred in updating NoOfTickets!!");
		}
	}


}
