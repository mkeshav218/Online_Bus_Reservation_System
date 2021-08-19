package com.microservice.dto1;

import java.util.Date;

public class Registration {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private Date dateOfBirth;
	private String gender;
	private int noOfBookedTicket;
	private double wallet;

	public Registration() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getNoOfBookedTicket() {
		return noOfBookedTicket;
	}

	public void setNoOfBookedTicket(int noOfBookedTicket) {
		this.noOfBookedTicket = noOfBookedTicket;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

}
