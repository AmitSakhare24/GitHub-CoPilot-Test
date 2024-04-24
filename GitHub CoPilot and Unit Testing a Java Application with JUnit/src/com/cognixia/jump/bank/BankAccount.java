package com.cognixia.jump.bank;

/*
	Bank Account class manages the balance for several types of accounts. You will create a JUnit test file for this class.
	Follow the instructions laid out in the README.md, but keep in mind you may need to update the code in this file in order
	to get your test to pass. As well as add in the custom exception found in this package.
 */
public class BankAccount {
	
	public static enum AccountType {
		CHECKING, SAVINGS
	}
	
	private int balance;
	private AccountType accountType;

	
	public BankAccount() {
		this(0, AccountType.CHECKING);
	}
	
	public BankAccount(int balance, AccountType accountType) {
		
		this.balance = balance;
		this.accountType = accountType;
	}

	
	public int getBalance() {
		return balance;
	}

	public AccountType getAccountType() { // no setter, once an account is set
		return accountType;				  // we don't want to change it
	}
	
	// will have a deposit and withdraw methods instead to change balance
	
	public void deposit(int amount) {
		this.balance += amount;
	}
	
	public void withdraw(int amount) throws OverdraftException {
		this.balance -= amount;
	}
}
