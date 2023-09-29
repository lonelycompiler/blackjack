package Blackjack;

import java.util.*;

public class Deck {
	
	ArrayList<String> cards;
	
	//constructor
	public Deck() {
		//creates new ArrayList of cards
		cards = new ArrayList<String>();
	}
	
	public Deck(Deck deck) {
		cards = new ArrayList<String>();
		
		for(int i = 0; i < deck.deckSize(); i++) {
			cards.add(deck.getCard(i));
		}
		
	}
	
	// Any cards there before removed and replaced with the default 52 card deck
	//fully organized in an ArrayList
	public void refillDeck() {
		System.out.println("\nDECK IS EMPTY! Refilling deck!");
		int suit = 1; 
		boolean inLoop = true;
		String cardSuit = "", clubs = "CB", diamonds = "DR", hearts = "HR", spades = "SB";
		
		//first remove all cards so that there are zero cards inside
		for(int i = 0; i < cards.size(); i++)
			cards.clear();
		
		while(inLoop) {
			if(suit == 1)
				cardSuit = clubs;
			else if(suit == 2)
				cardSuit = diamonds;
			else if(suit == 3)
				cardSuit = hearts;
			else
				cardSuit = spades;
			
			cards.add("A" + cardSuit);
			cards.add("1" + cardSuit);
			cards.add("2" + cardSuit);
			cards.add("3" + cardSuit);
			cards.add("4" + cardSuit);
			cards.add("5" + cardSuit);
			cards.add("6" + cardSuit);
			cards.add("7" + cardSuit);
			cards.add("8" + cardSuit);
			cards.add("9" + cardSuit);
			cards.add("10" + cardSuit);
			cards.add("J" + cardSuit);
			cards.add("Q" + cardSuit);
			cards.add("K" + cardSuit);
			
			suit++;
			if(suit == 5) //breaks loop
				inLoop = false;
		}
	}
	
	//Shuffles the deck randomly
	public void shuffleDeck() {
		System.out.println("Shuffling Deck!");
		Collections.shuffle(cards);
	}
	
	//Removes a card from the bottom of the deck and returns the name of the card
	public String removeCard() {
		if(cards.size() <= 1) {
			refillDeck();
			shuffleDeck();
		}
		
		return cards.remove( cards.size()-1 );
	}
	
	//THIS IS FOR DEBUGGING PURPOSES
	//this will println all cards in the arraylist
	/*public String toString() {
		String var = "";
		var += "There are [" + cards.size() + "] in the deck right now.\n";
		for(int i = 0; i < cards.size()-1; i++) {
			var += deck.get(i) + "\n";
		}
		return var;
	}*/

	//if deck is empty then
	private boolean checkDeckEmpty() {
		return cards.isEmpty();
	}
	
	private int deckSize() {
		return cards.size();
	}
	
	private String getCard(int index) {
		return cards.get(index);
	}
}
