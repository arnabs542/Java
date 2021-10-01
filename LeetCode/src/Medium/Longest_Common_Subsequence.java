package Medium;

//https://leetcode.com/problems/longest-common-subsequence/
/*
 * 	A classical DP problem.
 * 
 * 	The idea is having a 2D array of row length = text1.length and column length = text2.length.
 * 	dp[i][j] represents the longest common subsequence given 2 substrings: text1 up to index i and text2 up to index j.
 * 
 * 	Initially, the longest common subsequence length is 0 if both substrings are length 0.
 * 	Transition function:
 * 
 * 	> If at index i and j, characters from both text match, then we could see the dp[i-1][j-1]: The longest common
 * 	  subsequence excluding current pair of characters, since we use it to match. The longest common subsequence is now
 * 	  dp[i-1][j-1] + 1    <<- Current character contributes to length 1
 * 
 * 	> Otherwise, current pair of characters don't match. Simply take the longest common subseqeunce when length is 1 less,
 * 	  either from text1 or text2. Take max( dp[i-1][j], dp[i][j-1] )
 */

public class Longest_Common_Subsequence {
	
	// O(MN) space and time solution
	public int longestCommonSubsequence(String text1, String text2) {
		final int l1 = text1.length(), l2 = text2.length();
		int[][] dp = new int[l1+1][l2+1];
		
		for (int i = 0; i < l1; ++i) {
			for (int j = 0; j < l2; ++j) {
				// If character match, can take diagonal + 1
				if (text1.charAt(i) == text2.charAt(j)) 
					dp[i+1][j+1] = dp[i][j] + 1;
				else dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
			}
		}
		return dp[l1][l2];
    }
	
	// O(N) space optimized solution
	public int longestCommonSubsequence2(String text1, String text2) {
		String longer = text1.length() > text2.length()? text1: text2;
		String shorter = text1.length() > text2.length()? text2: text1;
		final int l1 = longer.length(), l2 = shorter.length();
		int[] dp = new int[l2+1];
		
		for (int i = 0; i < l1; ++i) {
			int prevdiag = 0;
			for (int j = 0; j < l2; ++j) {
				int curr = dp[j+1];
				
				// If character match, can take diagonal + 1
				if (longer.charAt(i) == shorter.charAt(j)) 
					dp[j+1] = prevdiag + 1;
				else dp[j+1] = Math.max(dp[j+1], dp[j]);
				
				prevdiag = curr;
			}
		}
		return dp[l2];
    }
	
}
