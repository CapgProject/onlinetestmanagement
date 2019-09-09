package com.cg.TestManagement.Exception;

public class UserException extends Exception{

	String exceptionMessage;
	public UserException() {
		
	}
	
	public UserException(String exceptionMessage) {
		super(exceptionMessage);
		this.exceptionMessage = exceptionMessage;
	}
}
