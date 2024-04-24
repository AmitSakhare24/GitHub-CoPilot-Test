package com.cognixia.jump.annotations;


class Parent {
	
	public void myMethod() {
		System.out.println("This method comes from Parent class");
	}
}

class Child extends Parent {
	
	// can remove this annotation and myMethod() will still be overridden,
	// just there to double check that the method you declared is actually a
	// method from the parent class
	@Override
	public void myMethod() {
		System.out.println("This method comes from the Child class");
	}
	
	// uncomment the Override annotation below, will notice an error
	// and will cause a compile time error when you attempt to run this file
	// @Override
	public void otherMethod() {
		System.out.println("Some other method");
	}

}

public class Annotations {
	
	public static void main(String[] args) {
		
		// can run file if you want to see the methods executed
		
		Parent parent = new Parent();
		parent.myMethod();
		
		Child child = new Child();
		child.myMethod();
		child.otherMethod();
		
	}

}
