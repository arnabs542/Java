package Easy;

//https://leetcode.com/problems/remove-palindromic-subsequences/
/*
 * 	This problem is... unobvious, hidden, but once you realize the trick, you realize
 * 	how easy it is.
 * 
 * 	We can select a palindrome subsequence and remove it. However, how are we going to select that and
 * 	reach to minimum removals?
 * 	Think: The string has only 2 types of character: a and b.
 * 	What qualifies as a valid palindrome? Does a string consisting of only a, and b, qualifies as palindrome?
 * 	YES!
 * 
 * 	Therefore, At worst we will perform 2 operations: Remove all a, and remove all b!
 * 	However, at what time we will do only 1 operation? When the string itself is a palindrome!
 * 	Also, if it is empty string, no operation needs to be done
 */

public class Remove_Palindromic_Subsequences {
	
	public int removePalindromeSub(String s) {
		if (s.length() == 0) return 0;
		return checkPalindrome(s)? 1: 2;
    }
	
	private boolean checkPalindrome(String s) {
		int l = 0, r = s.length() -1;
		while (l < r)
			if (s.charAt(l++) != s.charAt(r--) ) return false;
		return true;
	}
	
}
