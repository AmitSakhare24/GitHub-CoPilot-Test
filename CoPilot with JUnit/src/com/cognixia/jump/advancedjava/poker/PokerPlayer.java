package com.cognixia.jump.advancedjava.poker;

import java.util.ArrayList;

/*
 * CODE ALONG: ADVANCED JAVA - SINGLETON, MEMBER CLASS, ANONYMOUS CLASS
 * Use this project for an at length code along with students to demonstrate the above topics
 * Demo this class AFTER: Deck Of Cards, Playing Card, Player, and Poker
 */


// This class implements Player and Poker Interfaces defines everything we need to have a Player interact
// With our deck of cards and play poker.
public class PokerPlayer implements Player, Poker {
	
	// Static player counter
	private static int count = 0;
	private int id;
	private String name;
	// This array list of playing cards stores the cards in the player object's hand.
	protected ArrayList<PlayingCard> hand;
	// Status will be reflected from the Poker Interface
	private PlayerStatus status;
	// The money the player has on hand
	private double funds; 
	
	// Create the PokerPlayer Object by setting their name and game buy in
	public PokerPlayer(String PlayerName, double buyIn) {
		hand = new ArrayList<PlayingCard>();
		this.name = PlayerName;
		this.funds = buyIn;
		// Player ID is the current player count when the player enters the game
		this.id = ++count;
	}
	
	// Getters, Setters, to String and Print for Poker Player Attribues
	public double getFunds() {
		return funds;
	}

	protected void setFunds(double funds) {
		this.funds = funds;
	}
	
	public void printFunds(){
		System.out.println("$" + getFunds());
	}

	// Iterates though the players Playing Card hand to show all cards
	public void showHand() {
		System.out.println("Player: " + id);
		for (PlayingCard p : hand) {
			System.out.println(p.toString());
		}
		System.out.println();
	}
	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void printStatus() {
		System.out.println(status);
	}

	public PlayerStatus getStatus() {
		return status;
	}

	// Important for changing the Player status as the poker game develops
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
}

//**END POKERPLAYER CLASS**
//**GO TO TexasHoldEm Class**