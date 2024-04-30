package com.cognixia.jump.advancedjava.poker;

/*
 * CODE ALONG: ADVANCED JAVA - SINGLETON, MEMBER CLASS, ANONYMOUS CLASS
 * Use this project for an at length code along with students to demonstrate the above topics
 * This interface will define the status of a poker player.
 */

// Brief Interface to define the status of a poker player during a game.
public interface Poker {
	enum PlayerStatus {PLAYING, BUSTED, DEALER, BIGBLIND, LITTLEBLIND};
}

//**END OF POKER INTERFACE**
//**GO TO POKER PLAYER CLASS**
