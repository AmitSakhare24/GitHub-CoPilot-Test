package com.cognixia.jump.junit;

// Custom Exception used within Calculator if there is an attempt to divide by zero
public class DivideByZeroException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DivideByZeroException() {
		super("Cannot divide number by zero");
	}

}
