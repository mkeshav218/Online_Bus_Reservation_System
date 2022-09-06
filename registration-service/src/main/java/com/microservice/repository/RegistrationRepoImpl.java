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
	public double updateWallet(String email, double amount) {
		registration = userRegistrationDetails(email);
		registration.setWallet(amount);
		entityManager.merge(registration);
		return amount;
	}

	@Override
	public Registration updateUserDetails(String email, LocalDate dob) {
		registration = userRegistrationDetails(email);
		registration.setDateOfBirth(dob);
		return entityManager.merge(registration);
	}

	@Override
	public Registration updateUserDetails(String email, String gender) {
		registration = userRegistrationDetails(email);
		registration.setGender(gender);
		return entityManager.merge(registration);
	}

	@Override
	public Registration updateUserDetails(String email, LocalDate dob, String gender) {
		registration = userRegistrationDetails(email);
		registration.setDateOfBirth(dob);
		registration.setGender(gender);
		return entityManager.merge(registration);
	}

	@Override
	public Registration updateUserPhone(String email, String phone) {
		registration = userRegistrationDetails(email);
		registration.setPhone(phone);
		return entityManager.merge(registration);
	}

	@Override
	public Registration updatetPassword(String email, String newPassword) {
		registration = userRegistrationDetails(email);
		registration.setPassword(newPassword);
		return entityManager.merge(registration);
	}

	@Override
	public Registration setDefaultPassword(String email) {
		registration = userRegistrationDetails(email);
		registration.setPassword("12345678");
		return entityManager.merge(registration);
	}
	

	@Override
	public Registration userRegistrationDetails(String email) {
		return entityManager.find(Registration.class, email);
	}

	@Override
	public Registration updateBookedTicket(String email, int noOfTicket) {
		registration = userRegistrationDetails(email);
		registration.setNoOfBookedTicket(noOfTicket + registration.getNoOfBookedTicket());
		return entityManager.merge(registration);
	}
	
}
