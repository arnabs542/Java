package Easy;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/539/week-1-june-1st-june-7th/3350/

/*
 * 	A simple question which simply asks to reverse a string in place (meaning do not make use of other data structures, only manipulate
 * 	datas on the original copy)
 * 
 * 	In place doesn't necessary mean O(1) space complexity, as it can also be done with recursion, which also uses stack memory
 * 
 * 	This is a two pointer problem, we have to keep swapping characters, first one swap last one, second one swap last two, etc... until the midpoint
 * 	
 * 	Either we use left and right pointer with condition (left < right), or just finding the half of the length of string and loop that many times
 */

public class Reverse_String {
	//Iterative method
	public void reverseString(char[] s) {
		for (int i = 0; i < s.length / 2; i ++ ) {
			swap(s, i, s.length - 1 - i);
		}
    }
	
	public static void swap(char[]s, int i, int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}
	
	public void reverseStringRecursive(char[] s ) {
		if (s.length >= 1)
			recurse(s, 0);
	}
	
	public static void recurse(char[] s, int i) {
		char temp = s[i];
		s[i] = s[s.length - i -1];
		s[s.length - i - 1] = temp;
		
		if (i+1 < s.length / 2)
			recurse(s, i+1);
	}
}
