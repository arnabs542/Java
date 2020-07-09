package blackJackGame;

import java.util.ArrayList;
import java.util.Scanner;
public class Player {

	ArrayList<Card> hand = new ArrayList<Card>();
	
	void addCard (Card c) {
		hand.add(c);
	}
	
	
	ArrayList<Integer> getValues () {
		
		//Note for A: if only 2 cards then can be 1 or 11, otherwise 1 or 10;
		
		//Count number of A
		int noOfA = 0;
		int noOf10 = 0;
		for (Card c: hand) {
			if (c.getValue() == Value.ACE)
				noOfA ++;
			if (c.getValue().getValue() == 10)
				noOf10 ++;
		}
		
		ArrayList<Integer> values = new ArrayList<Integer>(noOfA + 1);
		
		//If it is (A,A)
		if (hand.size() == 2 && noOfA == 2) {
			values.add(100);
			return values;
		}
		//If it is (K,A) or similar sort
		else if (hand.size() == 2 && noOfA == 1 && noOf10 == 1) {
			values.add(50);
			return values;
		}
		//If 2 card in hand and consist of 1 Ace, count as 1 or 11 only
		else if (hand.size() == 2 && noOfA == 1) {
			Card theOther = null;
			for (Card c: hand) if (c.getValue() != Value.ACE) theOther = c;
			
			values.add( 11 + theOther.getValue().getValue() );
			values.add( 1 + theOther.getValue().getValue() );
			return values;
		}
		else {
			for (int oneAs10 = noOfA; oneAs10 >= 0; oneAs10 --) {
				int thisValue = 0;
				int thisOneAs10 = oneAs10;
				
				for (Card c: hand) {
					if (c.getValue() == Value.ACE) {
						if (thisOneAs10 != 0) {
							thisValue += 10;
							thisOneAs10 --;
						}
						else {thisValue += 1;}
					}
					else {
						thisValue += c.getValue().getValue();
					}
				}
				values.add(thisValue);
			}
		}
		
		//Eliminate the impossible values. If all are over 21, the values will be -999 indicating you've burst
		for (int i = 0; i < values.size(); i ++ ) {
			if (values.get(i) > 21) {
				values.remove(i);
			}
			
			if (values.isEmpty())
				values.add(-999);
		}
		
		return values;
		
		
	}		//end of getValues
	
	boolean canEscape () {
		return (hand.size() == 2 && getValues().contains(15));
	}
	
	boolean isAA () {
		return (getValues().get(0) == 100);
	}
	
	boolean isDirectBlackJack() {
		return (getValues().get(0) == 50);
	}
	
	boolean isEnough () {
		return (getLargest() > 15);
	}
	
	int getLargest () {
		return getValues().get(0);
	}
	
}		//end of Player
