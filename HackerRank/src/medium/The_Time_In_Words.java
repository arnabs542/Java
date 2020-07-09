package medium;

import java.util.HashMap;

//https://www.hackerrank.com/challenges/the-time-in-words/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

//Build it to refresh my concept on enums

public class The_Time_In_Words {
	
	public enum TimeWords {
		ONE("one"), TWO("two"), THREE("three"), FOUR("four"), FIVE("five"), SIX("six"), SEVEN("seven"), EIGHT("eight"), NINE("nine"), TEN("ten"),
		ELEVEN("eleven"), TWELVE("twelve"), THIRTEEN("thirteen"), FOURTEEN("fourteen"), FIFTEEN("quarter"), SIXTEEN("sixteen"), SEVENTEEN("seventeen"),
		EIGHTEEN("eighteen"), NINETEEN("nineteen"), TWENTY("twenty"), THIRTY("half");
		
		private final String word;
		
		TimeWords(String str) {
			this.word = str;
		}
		
		public String getWord() {
			return word;
		}
	}
	
	public static final HashMap<Integer, String> HOUR = new HashMap<>(), MINUTE = new HashMap<>();
	
	static {
		//Array to represent the enum values
		TimeWords[] arr = TimeWords.values();
		
		//Hour hash map is straight forward
		for (int i = 1; i <= 12; i ++ ) {
			HOUR.put(i, arr[i-1].getWord() );
		}
		
		//with 0, it shall be called "o' clock"
		MINUTE.put(0, " o' clock ");
		//with 1, it shall be singular for "minute", not "minutes"
		MINUTE.put(1, TimeWords.ONE.getWord() + " minute");
		
		for (int i = 2; i <= 14; i++ ) {
			MINUTE.put(i, arr[i-1].getWord() + " minutes");
		}
		
		//Since 15 is quarter, we can't include "minutes" inside
		MINUTE.put(15, TimeWords.FIFTEEN.getWord() );
		
		for (int i = 16; i <= 20; i++ ) {
			MINUTE.put(i, arr[i-1].getWord() + " minutes");
		}
		//Twenty onwards, it is just "twenty" appended to the last digit
		for (int i = 21; i < 30; i ++ ) {
			int tail = i % 10;
			MINUTE.put(i, TimeWords.TWENTY.getWord() + " " + arr[tail-1].getWord() + " minutes");
		}
		
		MINUTE.put(30, TimeWords.THIRTY.getWord() );
	}	
	
	
	static String timeInWords(int h, int m) {
		if (m == 0)
			return HOUR.get(h) + MINUTE.get(0);
		else if (m <= 30) 
			return MINUTE.get(m) + " past " + HOUR.get(h);
		else
			return MINUTE.get(60 - m) + " to " + HOUR.get( (h+1) % 12 );
	}
	
	public static void main(String[]args) {
		for (int i = 1; i <= 12; i ++ ) {
			for (int j = 0; j < 60; j ++ ) {
				System.out.printf("%02d:%02d, %s\n", i, j, timeInWords(i,j) );
			}
		}
	}
	
}
