package Medium;

import java.util.List;

//https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
/*
 * 	This is a backtracking (optional), string problem.
 * 
 * 	Since input size is small, we could easily get away with backtracking (2^N) solution.
 * 	Every string, we could make an attempt to include it or not.
 * 
 * 	- If we include it, then we mark the characters as existed, then proceed with the rest of strings (recurse)
 * 	- If we don't include it, we proceed to the other strings as usual.
 * 
 * 	We will use a boolean array (Actually you can use bitmask) to mark whether characters already existed in the
 * 	string or not, so we will ignore those who have characters already in the string so far.
 * 
 * 	Also, we'll directly ignore the strings that already have duplicate character in itself. Those strings will never
 * 	be considered even.
 */

public class Maximum_Length_of_a_Concatenated_String_With_Unique_Characters {
	public int maxLength(List<String> arr) {
		arr.removeIf((x)-> !isUnique(x));
        return recurse(arr, 0, 0, new boolean[26]);
    }
	
	// For each of the string, if it is unique characters and can fit inside string so far, we can try to include it.
	private int recurse(List<String> arr, int from, int len, boolean[] occupied) {
		int max = len;
		
		for (int i = from; i < arr.size(); ++i) {
			// Check if string can fit
			String s = arr.get(i);
			if (!canFit(s, occupied)) continue;
			
			// String can fit. We will try include it and continue
			for (char c: s.toCharArray())
				occupied[c - 'a'] = true;
			// Recurse
			max = Math.max(max, recurse(arr, i+1, len + s.length(), occupied) );
			// Backtrack
			for (char c: s.toCharArray())
				occupied[c - 'a'] = false; 
		}
		
		return max;
	}
	
	// Checks if a string is made up of characters only once: no dupicates
	private boolean isUnique(String s) {
		boolean[] contain = new boolean[26];
		for (char c: s.toCharArray()) {
			if (contain[c - 'a']) return false;
			contain[c - 'a'] = true;
		}
		return true;
	}
	
	// Given a occupied array and a string, checks if the string can fit inside without duplicate character
	private boolean canFit(String s, boolean[] occupied) {
		for (char c: s.toCharArray())
			if (occupied[c - 'a']) return false;
		return true;
	}
}
