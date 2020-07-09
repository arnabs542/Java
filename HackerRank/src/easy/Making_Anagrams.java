package easy;

//https://www.hackerrank.com/challenges/making-anagrams/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * First obtain all the character frequency from both strings. This probably cannot be done in a same loop as the string length may vary.
 * Now, compare the frequency of the same characters from both strings. If one has extra than the other, then the extra must be deleted. The same goes for
 * those who character doesn't exist in another string, all those characters must be eliminated. Therefore it can be just Math.abs( charfreq1 - charfreq2)
 * 
 */

public class Making_Anagrams {
	
	static int makingAnagrams(String s1, String s2) {
		int[] bucket1 = new int[26];
		int[] bucket2 = new int[26];
		
		for (char c: s1.toCharArray() ) {
			bucket1[c - 'a'] ++;
		}
		for (char c: s2.toCharArray() ) {
			bucket2[c - 'a'] ++;
		}
		
		int sum = 0;
		for (int i = 0; i < 26; i ++ ) {
			sum += Math.max(bucket1[i], bucket2[i] ) - Math.min(bucket1[i], bucket2[i] );
		}
		return sum;
	}
}
