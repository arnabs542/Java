package blackJackGame;

import java.util.Random;

//A class to create object representing a card
class Card {
	private Suits suit;
	private Value value;
		
	//Constructor taking 2 integer values to represent suit and value, for indexes refer Suits enum
	public Card(int s, int v) {
		this.suit = Suits.enumOf(s);
		this.value = Value.enumOf(v);
	}
	
	@Override
	public String toString() {
		return String.format("%s of %s", suit, value);
	}
	
	public Suits getSuit() {
		return suit;
	}
	
	public Value getValue() {
		return value;
	}
	
}		//end of card class

//------------------------------------------------------------------------------------------------------------------------------

public class CardDispenser {
	
	//boolean to show if certain card is already dispensed
	private boolean[][] isDispensed = new boolean[4][13];
	Random rand = new Random();
	int trySuit, tryValue;
	
	//Dispenses a card at random, but the dispensed card will not be repeated unless resetted using reset()
	public Card dispense() {
		if (isFinish()) {
			System.out.println("The deck is finished");
			return null;
		}
		else {
			do {
				trySuit = rand.nextInt(4);
				tryValue = rand.nextInt(13);
				
			} while (isDispensed[trySuit][tryValue]);
			
			isDispensed[trySuit][tryValue] = true;
			return new Card(trySuit,tryValue);
		}
	}
	
	//Resets the deck so that all cards returned
	public void reset() {
		isDispensed = new boolean[4][13];
	}
	
	//private method, To check if the deck is finished
	private boolean isFinish() {
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 13; j ++) {
				if (!isDispensed[i][j])
					return false;
			}
		}
		return true;
	}
	
}	//end of CardDispenser class
