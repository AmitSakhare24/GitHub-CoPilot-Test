package com.cognixia.jump.advancedjava.junit;

public class BankAccount {
	
	// create enum and store it here for account type
	
	public enum AccountType {
		CHECKING, SAVINGS
	}
	
	private int balance;
	private AccountType accountType;
	
	
	// generate constructor with fields, then create a default constructor
	
	public BankAccount() {
		this(0, AccountType.CHECKING);
	}
	
	public BankAccount(int balance, AccountType accountType) {
		
		if(balance < 0) {
			this.balance = 0;
		}
		else {
			this.balance = balance;
		}
		
		//this.balance = balance;
		this.accountType = accountType;
	}

	
	// only generate getters for balance and account
	
	public int getBalance() {
		return balance;
	}

	public AccountType getAccountType() { // no setter, once an account is set
		return accountType;				  // we don't want to change it
	}
	
	
	// will have a deposit and withdraw methods instead to change balance
	
	public void deposit(int amount) {
		//this.balance += amount;
		
		if(amount > 0) {
			this.balance += amount;
		}
	}
	
	public void withdraw(int amount) throws OverdraftException {
		//this.balance -= amount;
		
		if(amount > balance) {
			throw new OverdraftException(amount, balance);
		}
		else if(amount > 0) {
			this.balance -= amount;
		}
	}
	
	
	

}
