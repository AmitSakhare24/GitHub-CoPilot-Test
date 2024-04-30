package com.cognixia.jump.advancedjava.poker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/*
 * CODE ALONG: ADVANCED JAVA - SINGLETON, MEMBER CLASS, ANONYMOUS CLASS
 * Use this project for an at length code along with students to demonstrate the above topics
 * Last Class to build before the Driver Class Demo
 */

// This class sets the game and rules used for Texas Hold Em Poker
// Here we will utilize our Singleton Deck Of Cards, and how a Player interacts with the deck, through the game rules,
// are defined.

public class TexasHoldEm extends Betting implements Poker{
	// This is the initial deck. It is a singleton, here the singleton implementation is abstracted.
	private DeckOfCards deck;
	
	// The set of players that will play are stored in an array of PokerPlayers
	// Poker Players have their own playing card array for their hand.
	private PokerPlayer[] players;
	
	// The Cards on the table, that have been drawn from the deck
	private PlayingCard[] tableCards = new PlayingCard[5];
	
	// Track The Rounds Played in the game
	private int roundsPlayed = 0; 
	
	// References to Players to track betting rotations, used in bettingRotation() method;
	PokerPlayer dealer;
	PokerPlayer littleBlind;
	PokerPlayer bigBlind;
	
	// Initialize the game object. We need the number of players, and the betting parameters. 
	TexasHoldEm(int numberOfPlayers, double maxBet, double minBuyIn, double maxBuyIn){
		super.maxBet = maxBet;
		super.minBuyIn = minBuyIn;
		super.maxBuyIn = maxBuyIn;
		// Create the array of players based on the number of players from the constructor
		players = new PokerPlayer[numberOfPlayers];
		for (int i = 0; i < players.length; i++) {
			Random rand = new Random();
			// Randomly create the cash value for the player's buyin to the game, within the range
			double randBuyIn = minBuyIn + (rand.nextDouble() * (maxBuyIn - minBuyIn));
			BigDecimal bd = new BigDecimal(randBuyIn).setScale(2, RoundingMode.HALF_UP);
			randBuyIn = bd.doubleValue();
			players[i] = new PokerPlayer("Player: " + i, randBuyIn);
			
			// Check that the player buy in is within criteria 
			if(players[i].getFunds() >= minBuyIn && players[i].getFunds() <= maxBuyIn) {
				players[i].setStatus(PlayerStatus.PLAYING);
			}
		}
		// Call the Singleton getInstance() method of the deck, which in this case should create and build the deck.
		deck = deck.getInstance();
		// Deal the initial hands to the players.
		this.dealPlayers();
		this.bettingRotation();
	}
	
	// Getter to see the deck of cards
	public DeckOfCards getDeck() {
		return deck;
	}
	
	public PokerPlayer[] getPlayers() {
		return players;
	}
	
	// Private method that deals the initial 2 cards from the singleton deck to each player, randomized.
	private void dealPlayers() {
		// Loop through 2 cards to deal to a player
		for (int i = 0; i < 2; i++) {
			// loop through each player, so each player gets 2 cards, but each player gets 1 card before getting the second
			for (int p = 0; p < players.length; p++) {
				// The Deck dealCard() method returns a PlayingCard that is added to the player hand, and discarded from 
				// the deck.
				if(!this.players[p].getStatus().equals(PlayerStatus.BUSTED)) {
					this.players[p].hand.add(deck.dealCard());			
				}
			}
		}
	}
	
	// Deal the flop onto the table cards array (the 3 first cards visible on the table)
	// Private method to protect game integrity, it will be called by the showFlop() method.
	private void dealFlop() {
		for (int i = 0; i < 3; i++) {
			if(i>tableCards.length) break;
			tableCards[i] = deck.dealCard();
		}
	}
	
	// Deal the 4th card to the table.
	// Private method to protect game integrity, it will be called by the showRiver() method.
	private void dealTurn() {
		if(tableCards[3]==null && tableCards[2] != null) tableCards[3] = deck.dealCard();
		else System.out.println("The Turn has been dealt!");
	}
	
	// Deal the 5th card to the table.
	// Private method to protect game integrity, it will be called by the showRun() method.
	private void dealRiver() {
		if(tableCards[4]==null && tableCards[3] != null) tableCards[4] = deck.dealCard();
		else System.out.println("The River has been dealt!");
	}
	
	// This method prints the flop that is on the table, and calls the deal flop method, as well as checking if
	// the flop has already been dealt.
	public void showFlop() {
		if(tableCards[2] == null) {
			dealFlop();
			System.out.println("The Flop:");
			System.out.println(Arrays.toString(tableCards) + "\n");
		}
		else System.out.println("Flop is already on the table!");
	}
	
	// Same logic as the showFlop() above, deals and checks if already dealt.
	public void showTurn() {
		dealTurn();
		System.out.println("The Turn:");
		System.out.println(Arrays.toString(tableCards) + "\n");
	}
	
	// Same logic as flop and river above
	public void showRiver() {
		dealRiver();
		System.out.println("The River:");
		System.out.println(Arrays.toString(tableCards) + "\n");
	}
	
	// Prints the hands of each player in the current Texas Hold Em game
	public void showAllHands(){
		System.out.println("Round " + roundsPlayed + ":");
		for (PokerPlayer pokerPlayer : players) {
			pokerPlayer.printStatus();
			pokerPlayer.printFunds();
			pokerPlayer.showHand();
		}
	}
	
	// Player Status shift to Left (Move Big Blind, Little Blind, and Dealer 1 player to left)
	// Private method, this is called by the bettingRotation() to simplify assignments moving between players
	private PokerPlayer playerShiftLeft(PokerPlayer specialPlayer, PlayerStatus status) {
			// See if player is element 0 in array, if so, shift to end of array
			if(specialPlayer.getID() == 1) {
				specialPlayer = players[players.length-1];
				if(!specialPlayer.getStatus().equals(PlayerStatus.BUSTED)) {
					if(players[0].getStatus().equals(status)) players[0].setStatus(PlayerStatus.PLAYING);
					specialPlayer.setStatus(status);
					return specialPlayer;
				}
				else {
					return playerShiftLeft(specialPlayer, status);
				}
			}
			// Player is not element 0, decrement selected status 1 player down (to the left)
			else {
				specialPlayer = players[specialPlayer.getID()-2];
				if(!specialPlayer.getStatus().equals(PlayerStatus.BUSTED)) {
					if(players[specialPlayer.getID()].getStatus().equals(status)) { 
						players[specialPlayer.getID()].setStatus(PlayerStatus.PLAYING);
					}
					specialPlayer.setStatus(status);
					return specialPlayer;
					}
				else {
					return playerShiftLeft(specialPlayer, status);
				}
			}
		}

	// Sets players status for Big Blind, little blind, and dealer
	// Private method, this is only called during newRound() or game creation
	private void bettingRotation() {
		// Start Of Game, set positions
		if(roundsPlayed < 1) {
			dealer = players[0];
			dealer.setStatus(PlayerStatus.DEALER);
			littleBlind = playerShiftLeft(dealer, PlayerStatus.LITTLEBLIND);
			// status is double up when only 2 players are on table
			if(players.length < 3) {
				bigBlind = players[0];
				bigBlind.setStatus(PlayerStatus.BIGBLIND);
			}
			else {
				bigBlind = playerShiftLeft(littleBlind, PlayerStatus.BIGBLIND);
			}
		}
		// At least 1 round played, positions shift to the left
		else {
			dealer = playerShiftLeft(dealer, PlayerStatus.DEALER);
			littleBlind = playerShiftLeft(littleBlind, PlayerStatus.LITTLEBLIND);
			bigBlind = playerShiftLeft(bigBlind, PlayerStatus.BIGBLIND);
		}
	}
	
	// Round completed, reset player hands, the table, and shuffle the deck
	public void newRound() {
		roundsPlayed++;
		bettingRotation();
		deck = deck.resetDeck();
		for (PokerPlayer p : players) {
			p.hand = new ArrayList<PlayingCard>();
		}
		tableCards = null;
		dealPlayers();
	}
	
	// Unimplemented method. We will use betting for the anonymous class example. 
	// Students can expand here later on their own additionally.
	@Override
	protected void takeBets() {
		
	}
}

//**End of Texas Hold EM**
//**Go to PlayWithCards**