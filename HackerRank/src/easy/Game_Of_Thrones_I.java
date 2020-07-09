package easy;

//https://www.hackerrank.com/challenges/game-of-thrones/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

public class Game_Of_Thrones_I {
	static String gameOfThrones(String s) {
		int countOdd = 0;
		int[] chars = new int[26];
		
		for (char c: s.toCharArray() ) {
			chars[c - 'a'] ++;
			countOdd += (chars[c - 'a'] % 2 == 0)? -1: 1;
		}
		
		return (countOdd == 1 && s.length() % 2 != 0 || countOdd == 0)? "YES":"NO";
	}
}
