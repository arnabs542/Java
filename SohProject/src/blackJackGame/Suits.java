package blackJackGame;

public enum Suits {
	
	SPADE("Black"),
	HEART("Red"),
	DIAMOND("Red"),
	CLOVER("Black");
	
	private String colour;
	
	private Suits(String colour) {
		this.colour = colour;
	}
	
	public static Suits enumOf(int ordinal) {
		switch (ordinal) {
			case 0: return Suits.SPADE;
			case 1: return Suits.HEART;
			case 2: return Suits.DIAMOND;
			case 3: return Suits.CLOVER;
		}
		return null;
	}
	
}

enum Value {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);
	
	private int value;
	
	private Value(int v) {
		this.value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public static Value enumOf(int ordinal) {
		switch (ordinal) {
			case 0: return Value.ACE;
			case 1: return Value.TWO;
			case 2: return Value.THREE;
			case 3: return Value.FOUR;
			case 4: return Value.FIVE;
			case 5: return Value.SIX;
			case 6: return Value.SEVEN;
			case 7: return Value.EIGHT;
			case 8: return Value.NINE;
			case 9: return Value.TEN;
			case 10: return Value.JACK;
			case 11: return Value.QUEEN;
			case 12: return Value.KING;
		}
		return null;
	}
	
}
