package com.cognixia.jump.advancedjava.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cognixia.jump.advancedjava.junit.BankAccount.AccountType;

class BankAccountTest {

	@Test
	void testDeposit() {
		//fail("Not yet implemented"); // run test at first without deleting fail
		
		// test first if you can properly make a deposit
		
		BankAccount account = new BankAccount(); // new account is set to zero if no balance given
		
		// get balance...
		int beforeDeposit = account.getBalance();
		
		//...set amount to deposit...
		int depositAmount = 100;
		
		//...deposit that amount...
		account.deposit(depositAmount);
		
		//...this should be the expected amount in the bank...
		int expected = beforeDeposit + depositAmount;
		
		//...this is the actual balance we have
		int actual = account.getBalance();
		
		// this will return true or false if the amount we expected is the same
		// as the actual amount for our balance
		assertEquals(expected, actual);
		
	}

	@Test
	void testWithdraw() throws OverdraftException {
		//fail("Not yet implemented"); // comment or delete this after running it the first time
		
		BankAccount account = new BankAccount(100, AccountType.SAVINGS);
		
		// get balance...
		int beforeDeposit = account.getBalance();

		// ...set amount to withdraw...
		int withdrawAmount = 20;

		// ...withdraw that amount...
		account.withdraw(withdrawAmount);

		// ...this should be the expected amount in the bank...
		int expected = beforeDeposit - withdrawAmount;

		// ...this is the actual balance we have
		int actual = account.getBalance();

		// this will return true or false if the amount we expected is the same
		// as the actual amount for our balance
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testDepositNegativeAmount() {
		
		BankAccount account = new BankAccount(500, AccountType.CHECKING);
		
		// get our balance before deposit...
		int beforeDeposit = account.getBalance();
		
		// ...deposit some negative amount...
		account.deposit(-100);
		
		// ...check that negative amount wasn't deposited, should still be same
		// balance before deposit happened
		assertEquals(account.getBalance(), beforeDeposit); // will get error first time, fix
														   // code so it doesn't deposit negative
														   // amount
		
	}
	
	
	// here we will check to see when we create a balance we don't set it to a negative
	// number at the start
	@Test
	void testAccountNegativeStartingBalance() {
		
		BankAccount account = new BankAccount(-100, AccountType.CHECKING);
		
		// this method checks if the condition we pass to it is true
		// first run will be false, go back to BankAccount class and fix code
		// so it doesn't set a negative balance
		assertTrue(account.getBalance() >= 0);
		
	}
	
	// we deal with cases when a negative number is passed to withdraw()
	// method and make sure we don't withdraw a negative number and 
	// add to the balance instead of removing from it
	@Test
	void testNegativeWithdrawal() throws OverdraftException {
		BankAccount account = new BankAccount(100, AccountType.CHECKING);
		
		// get balance before withdrawal...
		int beforeWithdrawal = account.getBalance();
		
		// ...withdraw some negative amount...
		account.withdraw(-20);

		// ...assert negative number cannot be passed to withdraw, or
		// else this will actually deposit to the account
		assertEquals(beforeWithdrawal, account.getBalance());
	}
	
	// this tests that we check if an overdraft doesn't happen with a
	// custom exception 
	@Test
	void testOverdraft() {
		
		BankAccount account = new BankAccount(100, AccountType.CHECKING);
		
		// will check that an OverdraftException is thrown when we try to withdraw too much
		assertThrows(OverdraftException.class, () -> {
			account.withdraw(200);
		});
		
		
	}

}
