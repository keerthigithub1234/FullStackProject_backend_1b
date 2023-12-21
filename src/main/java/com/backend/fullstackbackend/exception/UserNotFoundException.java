package com.backend.fullstackbackend.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(Long id) {
		super("cound not found the user data with id: " + id);
		
	}

	
	}
	
	
	


