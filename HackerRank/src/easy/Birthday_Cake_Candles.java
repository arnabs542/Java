package easy;

public class Birthday_Cake_Candles {
	//	https://www.hackerrank.com/challenges/birthday-cake-candles/problem
	
	public static void main(String[]args) {
		
	}
	
	static int birthdayCakeCandles(int[] ar) {
		int highest = 0;
		for (int h: ar) {
			if (h > highest) highest = h;
		}
		
		int counter = 0;
		for (int h: ar) {
			if (h == highest) counter ++;
		}
		return counter;
    }
}
