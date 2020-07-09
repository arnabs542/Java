package easy;

//https://www.hackerrank.com/challenges/alternating-characters/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	Check from the second character. If the character is same as the previous character (AA or BB), then you need to delete one of it.s
 */

public class Alternating_Characters {
	static int alternatingCharacters(String s) {
		if (s.length() <= 1) return s.length();
		
		int count = 0;
		for (int i = 1; i < s.length(); i ++ ) {
			count += (s.charAt(i) == s.charAt(i - 1))? 1: 0;
		}
		return count;
	}
}
