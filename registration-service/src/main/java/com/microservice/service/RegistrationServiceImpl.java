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
	public void addRegistration(Registration newRegister) {
		try {
			repo.addRegistration(newRegister);
		}catch (Exception e) {
			System.out.println(e);
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public double getWalletAmount(String email,String password) {
		try {
			userRegistrationDetails(email,password);
			return repo.getWalletAmount(email);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public double depositMoney(String email,String password, double amount) {
		try {
			double curAmount = getWalletAmount(email,password);
			curAmount += amount;
			return repo.updateWallet(email, curAmount);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
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
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public Registration updateUserDetails(String email,String password, LocalDate dob) {
		try {
			userRegistrationDetails(email,password);
			return repo.updateUserDetails(email, dob);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public Registration updateUserDetails(String email,String password, String gender) {
		try {
			userRegistrationDetails(email,password);
			return repo.updateUserDetails(email, gender);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public Registration updateUserDetails(String email,String password, LocalDate dob, String gender) {
		try {
			userRegistrationDetails(email,password);
			return repo.updateUserDetails(email, dob,gender);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public Registration updateUserPhone(String email,String password, String phone) {
		try {
			userRegistrationDetails(email,password);
			return repo.updateUserPhone(email, phone);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}
	
	@Override
	public Registration setDefaultPassword(String email,String phone) {
		try {
			registration = repo.userRegistrationDetails(email);
			if(registration==null) {
				throw new BusServiceException("User "+ email +" Not Registered");
			}
			if(phone.equalsIgnoreCase(registration.getPhone())) {
				return repo.setDefaultPassword(email);
			}else {
				throw new BusServiceException("Phone no didn't match");
			}
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}


	@Override
	public Registration updatePassword(String email, String oldPassword, String newPassword) {
		try {
			userRegistrationDetails(email, oldPassword);
			return repo.updatetPassword(email, newPassword);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public Registration isRegistered(String email, String password) {
		try {
			return userRegistrationDetails(email, password);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
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
				throw new BusServiceException("Email-Id & Password did Not match!!");
			}
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}

	@Override
	public Registration updateBookedTicket(String email, String password, int noOfTicket) {
		try {
			userRegistrationDetails(email, password);
			return repo.updateBookedTicket(email, noOfTicket);
		}catch (Exception e) {
			throw new BusServiceException(e.getMessage());
		}
	}


}
