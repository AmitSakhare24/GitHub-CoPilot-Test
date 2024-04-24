package com.cognixia.jump.junit;

// A simple calculator class which we will test with JUnit
public class Calculator {

	// add two int values
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	// add the absolute values of two ints
	public int sumAbsolutes(int num1, int num2) {
		return Math.abs(num1) + Math.abs(num2);
	}

	// subtract two int values
	public int sub(int num1, int num2) {
		return num1 - num2;
	}

	// multiply two int values
	public int mult(int num1, int num2) {
		return num1 * num2;
	}

	// divide two int values
	public double div(int num1, int num2) throws DivideByZeroException {

		if (num2 == 0) {
			throw new DivideByZeroException();
		}

		return num1 / num2;
	}

}
