package com.microservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.microservice.controller.RegistrationController;
import com.microservice.dto.Deposit;
import com.microservice.dto.Login;
import com.microservice.dto.UserGender;
import com.microservice.entity.Registration;

@SpringBootTest
class RegistrationServiceApplicationTests {
	
	@Autowired
	RegistrationController controller;
	
	@Autowired
	Registration registration;

	@Test
	void test1() {   //new registration
		registration.setEmail("mkeshav218@gmail.com");
		registration.setFirstName("Keshav");
		registration.setLastName("Mishra");
		registration.setPassword("12345678");
		registration.setPhone("7005965589");
		//Date date = new Date();
		//registration.setDateOfBirth(date);
		registration.setGender("Male");
		registration.setWallet(100);
		
		ResponseEntity<String> addRegistration = controller.addRegistration(registration);
		System.out.println("----------------------Test1 Result----------------------");
		System.out.println(addRegistration.getBody());
	}
	
	@Test
	void test2() {  //get Wallet amount
		test1();
		Login login = new Login();
		login.setUserName("mkeshav218@gmail.com");
		login.setPassword("12345678");
		ResponseEntity<Double> res = controller.getWalletAmount(login);
		System.out.println("----------------------Test2 Result----------------------");
		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
	}
	
	@Test
	void test3() {
		test2();
		Deposit deposit = new Deposit();
		deposit.setUserName("mkeshav218@gmail.com");
		deposit.setPassword("12345678");
		deposit.setAmount(500);
		ResponseEntity<String> res = controller.depositMoney(deposit);
		System.out.println("----------------------Test3 Result----------------------");
		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
	}
	
	@Test
	void test4() {
		test3();
		Deposit deposit = new Deposit();
		deposit.setUserName("mkeshav218@gmail.com");
		deposit.setPassword("12345678");
		deposit.setAmount(200);
		ResponseEntity<String> res = controller.withdrawMoney(deposit);
		System.out.println("----------------------Test4 Result----------------------");
		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
	}
	
	@Test
	void test5() {
		test4();
		UserGender userGender = new UserGender();
		userGender.setUserName("mkeshav218@gmail.com");
		userGender.setPassword("12345678");
		userGender.setGender("Male");
		ResponseEntity<String> res = controller.updateUserGender(userGender);
		System.out.println("----------------------Test5 Result----------------------");
		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
	}

}