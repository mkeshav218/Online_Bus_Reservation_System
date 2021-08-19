package com.microservice.repository;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.entity.Registration;

@Repository
@Transactional
public class RegistrationRepoImpl implements RegistrationRepo {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	Registration registration;

	@Override
	public void addRegistration(Registration newRegister) {
		entityManager.persist(newRegister);
	} 

	@Override
	public double getWalletAmount(String email) {
		registration = userRegistrationDetails(email);
		return registration.getWallet();
	}

	@Override
	public void updateWallet(String email, double amount) {
		registration = userRegistrationDetails(email);
		registration.setWallet(amount);
		entityManager.merge(registration);
	}

	@Override
	public void updateUserDetails(String email, LocalDate dob) {
		registration = userRegistrationDetails(email);
		registration.setDateOfBirth(dob);
		entityManager.merge(registration);
	}

	@Override
	public void updateUserDetails(String email, String gender) {
		registration = userRegistrationDetails(email);
		registration.setGender(gender);
		entityManager.merge(registration);
	}

	@Override
	public void updateUserDetails(String email, LocalDate dob, String gender) {
		registration = userRegistrationDetails(email);
		registration.setDateOfBirth(dob);
		registration.setGender(gender);
		entityManager.merge(registration);
	}

	@Override
	public void updateUserPhone(String email, String phone) {
		registration = userRegistrationDetails(email);
		registration.setPhone(phone);
		entityManager.merge(registration);
	}

	@Override
	public void updatetPassword(String email, String newPassword) {
		registration = userRegistrationDetails(email);
		registration.setPassword(newPassword);
		entityManager.merge(registration);
	}

	@Override
	public String setDefaultPassword(String email) {
		registration = userRegistrationDetails(email);
		registration.setPassword("12345678");
		entityManager.merge(registration);
		return "12345678";
	}
	

	@Override
	public Registration userRegistrationDetails(String email) {
		return entityManager.find(Registration.class, email);
	}

	@Override
	public void updateBookedTicket(String email, int noOfTicket) {
		registration = userRegistrationDetails(email);
		registration.setNoOfBookedTicket(noOfTicket);
		entityManager.merge(registration);
	}
	
}
