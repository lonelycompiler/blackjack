package Blackjack;

import java.util.ArrayList;

public class Player {
	
	private ArrayList<String> cards;
	private int points;
	
	//Constructor
	public Player() {
		
		cards = new ArrayList<String>();
		points = 0; //player starts off with zero points
		
	}

	//displays the cards in the player's hand
	public String displayHand() {
		String deck = "";
		
		for(int i = 0; i < cards.size(); i++) {
			deck +=( "\n" + cards.get(i));
		}
		
		return deck;
	}
	
	//puts a card into the player's hand
	public void addToHand(String card) {
		cards.add(card);
		points += getCardPoints(card);
	}
	
	public int getPoints() {
		return points;
	}
	
	//TODO: ADD SPLIT METHOD
	
	
	//PRE-CONDITIONS: CARD MUST NOT BE CONVERTED
	private int getCardPoints(String card) {
		//TODO: Move to deck and change everything in player. For the future.
		
		//If ace was given last turn, then return 1
		int points = getPoints();
		String rank = card.substring(0,1);
		
		//if player gets an Ace and points would go above 21 points, then return 1
		//also if player already had ace (11 points) then it counters by returning 1
		if(points + 11 > 21 && rank.equals("A")) { 
			return 1;
		}else {
			if(rank.equals("A")) {//if an ace, return 11
				return 11;
			}else if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
				//if not a number, return 11
				return 10;
			}else {
				String number = "10";
				int result = Integer.valueOf(number);		
				return result;
			}
		}
	}
}
