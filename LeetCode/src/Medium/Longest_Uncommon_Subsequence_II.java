package Medium;

//https://leetcode.com/problems/longest-uncommon-subsequence-ii
/*
 * This is a String, Two pointers problem.
 * 
 * Since the input size is small, we could brute force our way.
 * To check whether a string is subsequence of another string, we could use two pointers technique.
 * Pointer l is on the potential subsequence string sub, while pointer r is on the longer string s.
 * Whenever character on l is same as the one on the r pointer, move the l pointer forward.
 * When pointer l successfully passed all the characters in sub, that means sub is indeed a valid subsequence.
 * 
 * For each of the string in array, it could be the longest uncommon subsequence. Therefore, we run another
 * iteration to check if it is indeed the case.
 * 
 * This solution runs in O(N^2 * L) time, where N = number of strings in array, L = avg length of words
 * 
 * -----------------------
 * 
 * To allow for early termination, we may choose to sort the strings by length first. Iterating from longest
 * string to shortest, once we found such string that is uncommon, we can return early.
 * 
 * 
 */

public class Longest_Uncommon_Subsequence_II {
	public int findLUSlength(String[] strs) {
		int res = -1;
		
		for (String s: strs) {
			boolean isUncommon = true;
			
			for (String check: strs) {
				if (s == check) continue;
				
				if (isSubsequence(s, check)) {
					isUncommon = false;
					break;
				};
			}
			if (isUncommon) res = Math.max(res, s.length() );
		}
		return res;
    }
	
	private boolean isSubsequence(String sub, String s) {
		if (sub.length() > s.length() ) return false;
		
		// Two pointers in both string
		int l = 0, r = 0;
		
		while (l < sub.length() && r < s.length()) {
			if (sub.charAt(l) == s.charAt(r))
				++l;
			++r;
		}
		return l == sub.length();
	}
}
