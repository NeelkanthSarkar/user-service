package com.demoapp.exceptionsmapp;

@SuppressWarnings("serial")
public class UserByIdNotFoundException extends Exception {


	public UserByIdNotFoundException(String message) {
		super(message);
	}

}
