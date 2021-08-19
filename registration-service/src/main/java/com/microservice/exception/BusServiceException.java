package com.microservice.exception;
public class BusServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public BusServiceException() {
		super();
	}
	public BusServiceException(String msg) {
		super(msg);
	}
}
