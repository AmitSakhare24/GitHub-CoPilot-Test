package com.cognixia.jump.bank;

// Custom exception to use when an account attempts to overdraft (withdraw more than is available)
public class OverdraftException extends Exception {

	private static final long serialVersionUID = 9182879691346971845L;

	public OverdraftException(int amount, int balance) {
		super("Can't withdraw $" + amount + ", account only has $" + balance);
	}
	
}
