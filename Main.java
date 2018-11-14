package Blackjack;

public class Main {

	public static void main(String[] args) {
		
		//creates the dealer and player objects

		Player dealer = new Player();
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		Player player5 = new Player();

		Deck cards = new Deck();
		
		Player[] players = new Player[6];
		players[0] = dealer;
		players[1] = player1;
		players[2] = player2;
		players[3] = player3;
		players[4] = player4;
		players[5] = player5;
		
		cards.refillDeck();
		cards.shuffleDeck();
		System.out.println(cards);
		
		/* chips */
		int[] playerChips = {0, 0, 0, 0, 0, 10000};
		
		round(cards, players, playerChips);
	}
	
	//amount of chips each player has. dealer has infinity
	public static void round(Deck cards, Player[] players, int[] playerChips) { 
		Deck deck = new Deck(cards);
		
		//deal first turn:
		for(int i = 1; i <= 5; i++) {
			String card = cards.removeCard();
			players[i].addToHand(card);
		}
		//dealer deals hidden card
		players[0]
		
		
	}

}
