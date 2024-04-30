package com.cognixia.jump.advancedjava.poker;

import java.util.ArrayList;
import java.util.Random;

/*
 * CODE ALONG: ADVANCED JAVA - SINGLETON, MEMBER CLASS, ANONYMOUS CLASS
 * Use this project for an at length code along with students to demonstrate the above topics
 * Begin with this DeckOfCards Class below.
 */

// Deck Of Cards is Our Singleton, we only need one deck that reflects changes of many players using it
public class DeckOfCards {  
	// The Deck is an array list of Playing Card Objects.  We can go back and create this object after we create the deck
	private ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	// Optional for jokers, can be set true or false
	private boolean hasJokers = false;
	// Static Composition of Self Object is first step of our Singleton class.  We set it to null, and static gives access to all
	// class instances.
	private static DeckOfCards instance = null;
	
	// Setting the default constructor to Private is critical to disabling object creation of the singleton after an instance already
	// exists.
	private DeckOfCards() {};
	
	// GetInstance() is the standard naming convention for creating and/or retrieving a singleton object.  This method will create
	// The object of deck of cards, if and only if the static composed deck is set to null.  Otherwise a deck already exists, and
	// then this method will instead retrieve the instance of the deck.
    public static DeckOfCards getInstance(){
    	// If the deck == null, then one is initialized and built.
    	if (instance == null) {
			instance = new DeckOfCards();
			instance.buildDeck();
		}
    	// The static deck object is returned
        return DeckOfCards.instance;
    }
    
    // **PAUSE HERE** Go to playing card object
    
    //**RESUME HERE** after building the playing card class
    
    // This method builds a card deck from the specified values that are stored inside the Playing Card Object.
    // We iterate through the nested member class of Playing card, which is just CARD, and build the deck based on those values.
    // This method is private, and only called once inside our get instance method for creating a new deck.
    private synchronized void buildDeck() {
    	for (String s : PlayingCard.Card.suites) {
			for (String v : PlayingCard.Card.values) {
				PlayingCard c = new PlayingCard(v,s);
				// Add the playing cards to our deck object
				deck.add(c);
			}
		}
    }
    
    // This method sets jokers into the deck if required.
    public synchronized void setJokers(Boolean hasJokers) {
    	if(this.hasJokers == false) {
	    	if (hasJokers) {
	    		this.hasJokers = true;
				for (String j : PlayingCard.Card.jokers) {
					PlayingCard joker = new PlayingCard("Joker", j);
					deck.add(joker);
				}
			}
		}
    }
    
    // This method is tied to the Deck of Cards Object, so when called will show the current deck and all of its cards.
    // If cards have been removed, this method will accurately reflect the updated deck, using toString.
    public void showDeck() {
    	System.out.println("The " + deck.size() +" Cards in the remaining deck are:");
    	for (PlayingCard p : deck) {
    		System.out.println(p.toString());
		}
    	System.out.println();
    }

    // This method is tied to the deck of cards. We randomly select a card from the deck by randomizing the index number of the 
    // array list. We return a playing card, which can be picked up by another object, usually a PLAYER.  We remove the returned 
    // playing card from the array list, thus discarding it from the deck.
    public synchronized PlayingCard dealCard() {
    	if(instance != null) {
	    	Random rand = new Random();
	    	PlayingCard deal = deck.get(rand.nextInt(deck.size()));
	    	this.deck.remove(deck.indexOf(deal));
	    	return deal;
    	}
    	return null;
    }
    
    public DeckOfCards resetDeck() {
    	instance = null;
    	return instance = getInstance();
    }
}

//**END OF DECK OF CARDS** (Singleton and Nested Member Class have been created at this point in demo.)
//**GO TO PLAYER INTERFACE**
