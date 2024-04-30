package com.cognixia.jump.advancedjava.poker;

import com.cognixia.jump.advancedjava.poker.Poker.PlayerStatus;

/*
 * CODE ALONG: ADVANCED JAVA - SINGLETON, MEMBER CLASS, ANONYMOUS CLASS
 * Use this project for an at length code along with students to demonstrate the above topics
 * This is the Driver Class to demo all the code we have so far.
 * At this point we can create a game and deal the cards and see how the Singleton deck is manipulated, but no betting logic
 * is coded yet.
 */

// Driver Class, this class can be changed to call various methods to demo the classes we created.
public class PlayWithCards {
	// MAIN Method
	public static void main(String[] args) {
		
		// Create a new game. (Number Of Players, max bet, minimum buy in, max buy in) 
		TexasHoldEm game = new TexasHoldEm(4, 5, 15, 25);
		// The game has been created, which means players have been generated, and a deck of cards has been built
		// We can use the show all hands to show that each player has a unique card with no collisions, because we used
		// the singleton design pattern for the card deck.
		game.showAllHands();
		// Progress the game to the flop
		game.showFlop();
		
		// ANONYMOUS CLASS DEMO - use Betting Abstract Class
		// The Anonymous betting class will be instantiated here by defining the class logic on the fly below
		
		// We define our logic into object bets
		Betting bets = new Betting() {
			// This override abstract method will subtract $5 from each players funds
			@Override
			public void takeBets() {
				for(PokerPlayer p : game.getPlayers()) {
					p.setFunds(p.getFunds()-5);
				}
				
			}	
		};
		// End of Anonymous function definition
		
		// Execute our Anonymous function
		bets.takeBets();
		// Print each player and their funds to show execution of anonymous function
		game.showAllHands();
		// Progress the game to the river
		game.showTurn();
		// progress the game to the run
		game.showRiver();
		// Show the deck to show that cards have been removed, and the missing cards should match the cards that the players have
		// In their hand
		game.getDeck().showDeck();
		// Call these methods again, to show the logic that should state this step was already completed.
		game.showFlop();
		game.showTurn();

		// Start a new round, show the deck has been re-shuffled and players have new hands, and the dealer, etc. has rotated left
		game.newRound();
		game.showAllHands();
		game.getDeck().showDeck();
	}

}
//**END OF POKER DEMO!!!**