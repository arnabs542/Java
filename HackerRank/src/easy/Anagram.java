package easy;

//https://www.hackerrank.com/challenges/anagram/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	The string has to be split into two part of length l/2, therefore if string is odd in length it's impossible to form anagram
 * 
 * 	Then obtain a character mapping from character as key to integer as frequency.
 * 	To convert them both to be anagrams, it's enough to just try to convert one to another, as for every different characters meet, it's either change it in string1
 * 	or change it in string2, both will lead to the same minimal actions taken.
 * 	Therefore just use one string as sample and compare each character frequency to another. If it is more than the other, ignore; If less, then add the difference
 * 	to the sum.
 */

public class Anagram {
	
	static int anagram(String s) {
		if (s.length() % 2 != 0) return -1;
		
		String sub1 = s.substring(0, s.length() / 2);
		String sub2 = s.substring(s.length() / 2 );
		
		int[] char1 = new int[26];
		int[] char2 = new int[26];
		
		for (int i = 0; i < sub1.length(); i ++ ) {
			char1[sub1.charAt(i) - 'a'] ++;
			char2[sub2.charAt(i) - 'a'] ++;
		}
		
		int changes = 0;
		for (int i = 0; i < 26; i++ ) {
			if (char1[i] < char2[i] ) {
				changes += char2[i] - char1[i];
			}
		}
		return changes;
	}
	
}
