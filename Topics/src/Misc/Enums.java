package Misc;

/*
 * 	Enumerations is a special data type that only allow a variable of this data type to contain specific, certain values. 
 * 	It allow a variable to hold one value of a set of predefined Constants.
 * 
 * 	We define enums like how we do when defining classes. The constant values must be at the top of the enum block, seperated by commas,
 * 	and if there's more code (like methods), then it must be ended by a semicolon
 * 	
 * 	Since enums are constants, we put the name in all UPPERCASE
 * 
 */

//Defines an enum Seasons containing a set of 4 constants. 
enum Seasons {
	SPRING, SUMMER, AUTUMN, WINTER;
}


/* -------------------------------------------------------------------------------------------------------------
 * 	Each constant itself is like a class, where will have some methods defined in the enum class itself. Therefore when declaring
 * 	enums, we can pass in arguments and when the enum is used, those methods can return the correct and responding value of the constant
 * 	itself
 */



/*	Creates a enum of Suits of a card. It contains a private variable asString to represent the name in more friendlier form, which can
*	be obtained through calling toString() method (overridden).
*
*/
enum Suit {
	HEART("Heart"), SPADES("Spade"), CLUB("Club"), DIAMOND("Diamond");
	
	private String asString;
	
	//Constructor for each of the constants
	Suit(String str) {
		this.asString = str;
	}
	
	@Override
	public String toString() {
		return this.asString;
	}
}

/*
 * 	Creates an enum of card values. Pass in the respective value as arguments, which can be obtained through calling the getter method
 * 	getValue()
 * 
 * 	Notice the toString() just uses string manipulation to return a user friendly name
 */
enum Value {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
	
	private int value;
	
	Value(int val) {
		this.value = val;
	}
	
	@Override
	public String toString() {
		return String.format("%c%s", Character.toUpperCase(this.name().charAt(0) ), this.name().substring(1).toLowerCase() );
	}
	
	public int getValue() {
		return this.value;
	}
	
}


class Card {
	private Suit suit;
	private Value value;
	
	Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}
	
	@Override
	public String toString() {
		if (this.value.equals(Value.ACE ) ) {
			return String.format("%s of %s (value %s)", this.suit.toString(), this.value.toString(), "1, 10 or 11 (Without hitting)");
		}
		return String.format("%s of %s (value %s)", this.suit.toString(), this.value.toString(), this.value.getValue() );
	}
}


public class Enums {
	
	static void describeSeasons(Seasons e) {
		switch (e) {
			case SPRING: System.out.println("Chill breezes blow through swiftly in cherry blossom");
						break;
			case SUMMER: System.out.println("Even the refridgerators are complaining about the heat");
						break;
			case AUTUMN: System.out.println("Scenery of falling leaves and orangish vibes");
						break;
			case WINTER: System.out.println("Spine chilling wind and cute snowmans!");
						break;
		}
	}
	
	
	public static void main(String[]args) {
		//Using the enum name as the type, just like using a class
		Seasons sea1 = Seasons.AUTUMN;
		Seasons sea2 = Seasons.SPRING;
		
		//Returns 2, like the index of the constant in the enum
		System.out.println(sea1.ordinal() );
		//Just print the name of the constant, unless the method is overridden in the enum declaration itself
		System.out.println(sea1.toString() );
		
		//Compares the enums using its comparator. Uses ordinal as comparing value
		System.out.println(sea1.compareTo(sea2) );
		
		//Iterates through the values in enum.
		for (Seasons e: Seasons.values() ) {
			System.out.println(e.toString() );
		}
	}
	
}
