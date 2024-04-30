package com.cognixia.jump.advancedjava.poker;

public abstract class Betting {
	protected double maxBet;
	protected double minBuyIn;
	protected double maxBuyIn;
	
	protected abstract void takeBets();
	public double getMaxBet() {
		return maxBet;
	}
}
