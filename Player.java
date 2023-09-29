package Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	private ArrayList<String> cards;
	private int points, intelPoints, firstSecPts;
	private boolean blackjack, isDealer, split;
	private String playerName;
	
	//Constructor
	public Player(String name) {
		
		cards = new ArrayList<String>();
		points = 0; //player starts off with zero points
		firstSecPts = 0;
		blackjack = false;
		playerName = name.substring(0);
		split = false;
		
		/*
		 * 150-150 Points = Player
		 * 100-149 Points = Hit if below 18
		 * 026-099 Points = Hit if below 15
		 * 000-025 Points = Always hit
		 */

		isDealer = false;
		
		if(name.equals("Player3")) {
			intelPoints = 150;
		} else if(name.equals("Dealer")) {
			isDealer = true;
			intelPoints = 100;
		} else {
			Random rnd = new Random();
			int random = rnd.nextInt(51);
			if(random > 25)
				intelPoints = 25;
			else
				intelPoints = 0;
		}
		
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
	public void addToHand(String card, boolean hiddenCard) { //times played first second dealt hands
		
		if(!blackjack) { //if player hasn't gotten blackjack then can add cards
			cards.add(card);
			points += getCardPoints(card);
			System.out.println(cardDealt(card, hiddenCard)); //displays cards dealt
			if(points == 21) {
				blackjack = true;
				System.out.println("\n=======================================");
				System.out.println(playerName + " HAS GOTTEN BLACKJACK! YEAAAAH!");
				System.out.println("=======================================\n");
			}
			if(!split) {
				firstSecPts+=points;
				if(firstSecPts - firstSecPts == firstSecPts)
					split = true;
			}

			if(playerName.equals("Player3"))
				System.out.println(playerStats());
			//##################
			delay(1200);
			//##################
		}
		
		//else nothing happens -_-
		
	}
	
	//AI hit or step: if 1 HIT, if 2 STEP, if 3 PLAYER
	/*
	 * 150-150 Points = Player
	 * 100-149 Points = Hit if below 18
	 * 026-099 Points = Hit if below 15
	 * 000-025 Points = Always hit
	 */
	public int hitOrStep() {
		if(intelPoints == 150) {
			return 3;
		} else if(intelPoints >= 100 && intelPoints <= 149) {
			if(points <= 18)
				return 1;
			else
				return 2;
		} else if(intelPoints >= 26 && intelPoints <= 99) {
			if(points <= 15)
				return 1;
			else
				return 2;
		} else {
			return 1; //total dumbass
		}
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getIntelPoints() {
		return intelPoints;
	}
	
	public boolean getBlackjack() {
		return blackjack;
	}
	
	public boolean getSplit() {
		return split;
	}
	
	//gets intel points from players
	private void playersIntelligence() {
		
	}
	
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
				String number = card.substring(0,1);
				int result = Integer.valueOf(number);		
				return result;
			}
		}
	}
	
	private String cardDealt(String card, boolean hiddenCard) {
		Card cards = new Card();
		String value = "";
		if(isDealer) {
			if(hiddenCard) {
				value = "Dealer has given himself a hidden card!";
			} else {
				value = "Dealer has given himself a " + cards.convertCard(card);
			}
		} else {
			value = "Dealer has dealt " + playerName + " a " + cards.convertCard(card) + ".";
		}
		
		return value;
	}
	
	private String playerStats() {
		String value = "";
		
		value += "\n=======================================\n";
		value += playerName + " STATS:\n";
		value += "POINTS: " + points + "!\n";
		value += "CHIPS:  " + "to be implemented.\n";
		value += "=======================================\n";
		
		return value;
	}
	
	private void delay(int seconds) {
		try {
		    Thread.sleep(seconds);
		}
		catch(InterruptedException ex) {
			System.out.println("Thread inturrupted");
		    Thread.currentThread().interrupt();
		}
	}
}
