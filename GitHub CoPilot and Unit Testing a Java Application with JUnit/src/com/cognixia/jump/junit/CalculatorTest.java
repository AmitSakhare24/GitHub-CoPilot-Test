package com.cognixia.jump.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* 
	Test class that performs set of unit tests on Calculator class using the JUnit library
	
	If you are getting errors in this class with any of the test code, check if you have the JUnit library referenced
	in this project. Follow these steps:
		1. Right click on your project
		2. Select Build Path -> Add Libraries
		3. Select JUnit --> click Next
		4. For the option "JUnit Library Version", select the option JUnit 5
		5. Click Finish
		6. You should see the JUnit 5 library listed under your project now and the errors should be gone
*/
class CalculatorTest {
	
	// variables we'll reuse with each of our tests
	private static Calculator calc;
	private static int testCount;
	
	// run once before any of the tests
	@BeforeAll
	public static void setup() {
		
		// initialize values
		calc = new Calculator();
		testCount = 0;
		
		System.out.println("Attributes have been initialized");
	}
	
	// run once after all the tests
	@AfterAll
	public static void cleanup() {
		
		System.out.println("All tests have been run, total tests run is " + testCount);
	}
	
	// run before each test
	@BeforeEach
	public void beforeTest() {
		System.out.println("Running test...");
	}
	
	// run after each test
	@AfterEach
	public void afterTest() {
		testCount++;
		System.out.println("Finished running test, testCount = " + testCount);
	}
	
	

	@Test
	void testAdd() {
		
		// setup
		int num1 = 4;
		int num2 = 6;
		
		// execute
		int actual = calc.add(num1, num2);
		int expected = num1 + num2;
		
		// assert
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testSub() {
		
		// setup
		int num1 = 4;
		int num2 = 6;

		// execute
		int actual = calc.sub(num1, num2);
		int expected = num1 - num2;

		// assert
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testSumAbsolutes() {
		
		// setup
		int num1 = 4;
		int num2 = 6;

		// execute
		int actual = calc.sumAbsolutes(num1, num2);
		int expected = Math.abs(num1) + Math.abs(num2);

		// assert
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testMult() {

		// setup
		int num1 = 4;
		int num2 = 6;

		// execute
		int actual = calc.mult(num1, num2);
		int expected = num1 * num2;

		// assert
		assertEquals(expected, actual);

	}
	
	@Test
	void testDiv() throws DivideByZeroException {
		
		// setup
		int num1 = 4;
		int num2 = 6;

		// execute
		double actual = calc.div(num1, num2);
		double expected = num1 / num2;

		// assert
		assertEquals(expected, actual);
		
	}
	
	// unlike the previous test, want to make sure when we execute this one
	// the exception will be thrown
	@Test
	void testDivByZero() {
		
		// setup
		int num1 = 4;
		int num2 = 0;

		// execute & assert
		assertThrows(DivideByZeroException.class, () ->{
			calc.div(num1, num2);
		});
		
	}


}










