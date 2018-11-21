package Blackjack;

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {

	private static boolean blackjack, split;
	
	public static void main(String[] args) {
		
		System.out.println("DEBUG1: Runs the main class");
		
		blackjack = false;
		split = false;
		
		//creates the dealer and player objects

		Player dealer = new Player("Dealer");
		Player player1 = new Player("Player1");
		Player player2 = new Player("Player2");
		Player player3 = new Player("Player3");
		Player player4 = new Player("Player4");
		Player player5 = new Player("Player5");
		
		System.out.println("DEBUG2: Dealer and Players are created.");
		
		Deck cards = new Deck();

		System.out.println("DEBUG3: Deck is created.");
		
		Player[] players = new Player[6];
		players[0] = dealer;
		players[1] = player1;
		players[2] = player2;
		players[3] = player3;
		players[4] = player4;
		players[5] = player5;
		
		cards.refillDeck();
		cards.shuffleDeck();
		//System.out.println(cards);

		System.out.println("DEBUG4: Deck is refilled and shuffled.");
		
		/* chips */
		int[] playerChips = {0, 0, 0, 0, 0, 10000};
		
		round(cards, players, playerChips);
	}
	
	//amount of chips each player has. dealer has infinity
	public static void round(Deck cards, Player[] players, int[] playerChips) { 
		System.out.println("\n=======================================\nNEW ROUND YEAAAH\n=======================================\n");
		
		
		System.out.println("DEBUG5: Round is run.");
		
		Deck deck = new Deck(cards);

		System.out.println("DEBUG6: Deck is created with existing cards.");
		
		//deal first turn:
		for(int i = 1; i <= 5; i++) {
			String card = cards.removeCard();
			players[i].addToHand(card, false);
		}
		//dealer deals hidden card
		players[0].addToHand(deck.removeCard(), true);
		
		System.out.println("DEBUG7: First cards and dealer's hidden card are dealt.");
		
		//deal first turn:
		for(int i = 1; i <= 5; i++) {
			String card = cards.removeCard();
			players[i].addToHand(card, false);
		}
		//dealer deals face up card
		players[0].addToHand(deck.removeCard(), false);
		
		if(players[0].getBlackjack()) {
			System.out.println("DEALER HAS WON! GAME ENDS");
			
			//TODO: whichever player didn't get blackjack will lose.
			round(cards, players, playerChips);
		}

		//TODO: Implement Split here
		
		//checks if players have gotten blackjack
		for(int i = 1; i <= 5; i++) {
			if(players[i].getBlackjack()) {
				//player will win chips
			}
		}
		
		System.out.println("DEBUG7: Second cards and dealer's face up card are dealt.");
		
		//each player hit or step
		for(int i = 1; i <= 5; i++) {
			/*
			 * 150-150 Points = Player
			 * 100-149 Points = Hit if below 18
			 * 026-099 Points = Hit if below 15
			 * 000-025 Points = Always hit
			 */
			
			//if its the player
			if(i == 3) {
				int hitStep = 3;
				do { //can continue playing if below 16
					while(hitStep != 1 && hitStep != 2) { //loops if not hit or step
						System.out.println("Enter 1 to hit, 2 to step: ");
						Scanner scanner = new Scanner(System.in);
						hitStep = scanner.nextInt();
						
						if(hitStep == 1) {
							String card = cards.removeCard();
							players[i].addToHand(card, false);
							
							System.out.println("You chose to hit!");
						}
						if(hitStep == 2) {
							System.out.println("You chose to step!");
							break;
						}
					}
				}while(hitStep != 2 && players[3].getPoints() <= 16);
			}
			
			if(players[i].hitOrStep() == 1) { //if hit
				String card = cards.removeCard();
				players[i].addToHand(card, false);
			}
				
			
		}
	}
}
