package Easy;

import java.util.Arrays;

//https://leetcode.com/problems/valid-anagram/
/*
 * 	This is a Hash map / Sorting problem.
 * 
 * 	Determine if it is anagram simply means to check if frequency of characters in both strings are equal.
 * 	Thus, there are ways to do this:
 * 
 * 	1. Sorting
 * 
 * 	After sort, if the frequency are really identical, both sorted strings shall be identical. Time complexity is
 * 	O(N log N) where N is length of string. Although merge or quick sort takes O(log N) space, usually assumed is O(1) 
 * 
 * 
 * 	2. Hash Table.
 * 
 * 	Use a Hash table to record frequency of characters in both string. At the end, frequency shall match correspondingly.
 *	Furthermore, since there is only 2 strings, we can make one string be adding and the other subtracting. If the
 *	frequencies truly match, at the end the frequencies should be 0.
 */

public class Valid_Anagram {
    
	//	Sorting solution - After sort, both string shall be same
	//	Works on large character set like Unicode. Since taking only O(1) space assumed.
	//	Sorting depends on string length instead
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length() ) return false;
		
		char[] s1 = s.toCharArray();
		char[] s2 = t.toCharArray();
		Arrays.parallelSort( s1 );
		Arrays.parallelSort( s2 );
		
		return Arrays.equals( s1, s2 );
    }
	
	
	//	Mapping solution - Since lowercase only, it only need to consider 26 possible
	//	characters. Make a array of 26. Even further, every character encounter in s
	//	+1, every character encounter in t -1. At the end should all be 0
	public boolean isAnagram2(String s, String t) {
		if (s.length() != t.length() ) return false;
		
		int[] counter = new int[26];
		for (int i = 0; i < s.length(); ++i) {
			++counter[ s.charAt(i) - 'a' ];
			--counter[ t.charAt(i) - 'a' ];
		}
		
		for (int i: counter) if (i != 0) return false;
		return true;
	}
	
	
}
