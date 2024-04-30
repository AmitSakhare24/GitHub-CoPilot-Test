package com.cognixia.jump.advancedjava.poker;

/*
 * CODE ALONG: ADVANCED JAVA - SINGLETON, MEMBER CLASS, ANONYMOUS CLASS
 * Use this project for an at length code along with students to demonstrate the above topics
 * Part 2 Class of Project
 */

// The deck of cards class will compose a collection of objects of this class.
// The Playing Card class has a member nested interface where we define the values and suits of our card deck
public class PlayingCard{
	
	// Attributes for a single playing card
	protected String value;
	protected String suit;
	
	// Construct the playing card
	protected PlayingCard(String value, String suit){
		this.value = value;
		this.suit = suit;
	}
	
	// Member nested Interface that stores the possible values into 3 arrays: Values, Suits, and Jokers
	public interface Card {
		String[] values = {"ACE", "2" , "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"};
		String[] suites = {"Diamonds", "Hearts", "Clubs", "Spades"};
		String[] jokers = {"Black", "Red"};
	}
	
	// Override toString Method
	public String toString() {
		return "Card: " + value + "-" + suit;
	}
	
	//**RETURN TO DECK OF CARDS CLASS** pick up at the buildDeck() method
}
