package Medium;

//https://leetcode.com/problems/palindromic-substrings/
/*
 * 	As usual, this is a Dynamic Programming problem / Palindrome technique problem
 * 
 * 	First solution to use DP, is to observe a palindrome pattern and relate how to determine if a substring is a
 * 	palindrome based on previously computed results
 * 
 * 	>	A substring is palindrome if:
 * 			-	First and last character is same
 * 			-	The middle part (exclude the first and last) is also a palindrome.
 * 
 * 	If we already determined if the middle part is palindrome before, we could solve it in DP in O(N^2) time
 * 
 * 	Thus we use a DP array of size 2D. Rows indicate the start index of substring, and column indicate the end index
 * 	of substring. 
 * 	To fill in DP array, we make nested loop - Outer one loops length, and inner loops start index (and thereby identify
 * 	end index as well). 
 * 	Base case is that all substring of len 1 is a palindrome. Then we loop length 2, 3...
 * 
 * 	-------------------------------------------------------------------------------------
 * 
 * 	"Palindrome is like a onion. Remove head and tail character, and end up with another smaller palindrome"
 * 
 * 	Therefore, we can iterate over all centers in the string, and attempt to extend the window and see if it is still a
 * 	palindrome or not.
 * 
 * 	For this we have to consider whether the palindrome is odd length and even length. Thus, iterate all indices and
 * 	find out whether this is a center of palindrome at [i,i] and also [i,i+1] (Even length palindrome)
 */

public class Palindromic_Substrings {
	
	public int countSubstrings(String s) {
		final int len = s.length();
		boolean[][] dp = new boolean[len][len];
		int res = 0;
		
		//	Initialization run - All start index with len 1 is a palindrome
		for (int i = 0; i < len; ++i) {
			dp[i][i] = true;
			++res;
		}
		
		//	General case - Iterate substrings of different lengths
		for (int l = 2; l <= len; ++l) {
			//	Iterate all start indices, while it dont go overbound
			for (int st = 0; st + l <= len; ++st) {
				int end = st + l - 1;
				
				//	Only possible to be palindrome if both end are same
				if (s.charAt(st) == s.charAt(end) ) {
					//	Both are same. Palindrome only if len = 2 or middle part is palindrome as well
					if (l == 2 || dp[st+1][end-1] ) {
						dp[st][end] = true;
						++res;
					}
				}
			}
		}
		
		return res;
	}
	
	
	//	Extend from potential center solution
	public int countSubstrings2(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); ++i) {
			res += countFromCenter(s, i, i);
			res += countFromCenter(s, i, i+1);
		}
		return res;
	}
	
	private int countFromCenter(String s, int left, int right) {
		final int len = s.length();
		int res = 0;
		while (left >= 0 && right < len && s.charAt(left) == s.charAt(right) ) {
			++res;
			--left;
			++right;
		}
		return res;
	}
	
}
