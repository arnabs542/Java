package Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
/*
 * 	This is a Sliding Window, HashSet / HashMap.
 * 
 * 	For Brute force-y approach, note that every character could possibly be the ending character for a longest substring
 * 	without repeating characters. Therefore we will verify that by iterate backwards from current position to see how
 * 	far it extends.
 * 
 * 	Luckily for us, our backwards iteration would be limited by K, the number of unique characters in the string. This
 * 	is due to pigeonhole principle.
 * 
 * 	How can we know if a character existed in the substring already or not? When iterating backwards we could fix the
 * 	character and once again iterate forward to check if the character exists in the substring covered.
 * 	However doing that will result in O(NK^2) == O(N^3) solution (Although O(1) space though)
 * 	
 * 	A better way is to use HashSet to store the visited characters.
 * 
 * 	-----------------------------------------------------------
 * 
 * 	Instead of having to iterate backwards, we would use sliding window. Keep a head pointer that points to the starting
 * 	point of the substring. When we detect there is a repeated character when we extend the window, we basically have
 * 	to shrink the window from behind character-by character and find out the repeated character to discard so the substring
 * 	remains valid.
 * 
 * 	At most, each character will be visited 2 times, thus solution is O(2N) == O(N) and space is O(K)
 * 
 * 	-----------------------------------------------------------
 * 
 * 	Using HashMap, we could also immediately skip the head pointer to last visited same character when we hit the duplicate
 * 	character.
 * 	You might ask how about the intermediate characters skipped? For that we will have to check the position of the tail
 * 	pointer each time then.
 */

public class Longest_Substring_Without_Repeating_Characters {

	//	Brute force-y way of doing this problem. Worst case is O(NK) where k is number of unique characters available in string
	public static int lengthOfLongestSubstring(String s) {
		final int len = s.length();
		int longest = 0;
		Set<Character> exists = new HashSet<>();
		
		for (int i = 0; i < len; ++i ) {
			exists.clear();
			
			int backwardsPt = i;
			while (backwardsPt >= 0 && exists.add(s.charAt(backwardsPt) ) )
				--backwardsPt;
			
			longest = Math.max(longest, exists.size() );
		}
		
		return longest;
	}
	
	
	
	//	Sliding window HashSet Solution
	public static int lengthOfLongestSubstring2(String s) {
		final int len = s.length();
		int longest = 0;
		Set<Character> exists = new HashSet<>();
		
		int headPt = 0;
		for (int i = 0; i < len; ++i ) {
			if (!exists.add(s.charAt(i) ) ) {
				while ( s.charAt(headPt) != s.charAt(i) )
					exists.remove( s.charAt(headPt++) );
				++headPt;		//	We stopped at the repeated character. Move forward to discard the repeated Character
			} else {
				longest = Math.max(longest, exists.size() );
			}
		}
		
		return Math.max(longest, exists.size() );
	}
	
}
