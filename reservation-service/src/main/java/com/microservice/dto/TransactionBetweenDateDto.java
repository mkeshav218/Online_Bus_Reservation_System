package com.microservice.dto;

import com.microservice.pojo.Login;
import com.microservice.pojo.TransactionBetweenDate;

public class TransactionBetweenDateDto {
	Login login;
	TransactionBetweenDate transactionBetweenDate;
	public TransactionBetweenDateDto() {
		super();
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public TransactionBetweenDate getTransactionBetweenDate() {
		return transactionBetweenDate;
	}
	public void setTransactionBetweenDate(TransactionBetweenDate transactionBetweenDate) {
		this.transactionBetweenDate = transactionBetweenDate;
	}
	@Override
	public String toString() {
		return "TransactionBetweenDateDto [login=" + login + ", transactionBetweenDate=" + transactionBetweenDate + "]";
	}
	
}
