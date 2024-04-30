package com.cognixia.jump.advancedjava.junit;

public class OverdraftException extends Exception {

	private static final long serialVersionUID = 9182879691346971845L;

	public OverdraftException(int amount, int balance) {
		super("Can't withdraw $" + amount + ", account only has $" + balance);
	}
	
}
