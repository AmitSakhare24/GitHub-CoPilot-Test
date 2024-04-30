package com.cognixia.jump.advancedjava.poker;

import java.util.Scanner;

//  This class will simply draw cards from the deck.
public class DealCards {
	
	public static void main(String[] args) {
		
//		The Constructor of the deck is private, so we must use the getInstance method to get 
//		access to the singleton class
		DeckOfCards deck = DeckOfCards.getInstance();
		
		for (int i = 0; i < 55; i++) {
			System.out.println(i);
			deck.dealCard();
		}
		
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("\nOptions: \n"
					+ "1: Draw a card\n"
					+ "2: See the deck\n"
					+ "3: Reset the deck\n"
					+ "4: Exit\n");
			String choice = scan.nextLine();
			switch (choice) {
				case "1":
					try {
						System.out.println("you drew a " + deck.dealCard());
					} catch (IllegalArgumentException e) {
						System.out.println("The deck is empty, you must reset the deck");
					}
					
					break;
				case "2":
					deck.showDeck();
					break;
				case "3":
					System.out.println("Let's shuffel these back in!");
					deck.resetDeck();
					break;
				case "4":
					System.out.println("Bye!");
					loop = false;
					break;
				default:
					System.out.println("Sorry, I don't recognize that command");
					break;
			}
		}
		
		scan.close();
	}
}
