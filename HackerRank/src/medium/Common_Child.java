package medium;

//https://www.hackerrank.com/challenges/common-child/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	A dynamic programming question. It is asking to find the longest common subsequence in the string.
 * 	On the base idea, it is to keep two pointers which initially on the start of the string. If the character of the two pointers equal, we will increment the 
 * 	count of matches and move both the two pointers one step to the right.
 * 	If it is not equal, we have to try two choices of moving the left pointer to the right or moving the right pointer to the right.
 * 	This process continues until either one of the pointers had moved to the end of the string (null).
 * 	Since we need to find the maximum length, this is a optimization problem which needs to use Math.max() on the two choices.
 * 
 * 	Note this solution is O(2^n), and has repeated calculations. In the example of string pair (HARRY, SALLY), it repeat the calculations of (ARRY,ALLY) two times, as 
 * 	well as any process after that. Memoization can be used to store the results of the calculation, which probably stores the result of "The longest subsequence" answer
 * 	based on the pointer's position. In this case, pointer position is (1,1)
 * 	
 * 	A dynamic programming solution is bottom-up, which starts from asking what is the longest common subsequence from the starting substring. For (HARRY, SALLY), we
 * 	will start from (H,S), (HA, S), (HAR, S) and so on.... On the next row, we will start asking the question by expanding the second string (SA), (SAL)....
 * 
 * 	A grid on the dp represents the longest subsequence. The row represents a position on str1, and column represents a position on str2.
 * 
 * 	If at the grid, the characters are equal for the positions, then that means we are going to use both the characters to match. Thus, we have to store
 * 	1 + the longest subsequence when the pointers are 1 step behind both the strings, which is the adjacent/ row - 1, col -1
 * 
 * 	If the characters are not equal however, then we shall take the maximum of prev row, which is longest common subsequence length without current str2 character,
 * 	or prev col, longest common subsequence length without current str1 character.
 * 
 * 	We can do initialization to make the process easier, which is the row and column for empty string, which shall be 0 in value.
 * 
 * 	FOR MORE DETAILED EXPLAINATION, TRY GOING TO PACKAGE Topics > Algorithms > Largest_Common_Subsequence
 */

public class Common_Child {
	static int commonChild(String s1, String s2) {
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		
		for (int r = 1; r <= s1.length(); r ++ ) {
			for (int c = 1; c <= s2.length(); c ++ ) {
				if (s1.charAt(r - 1) == s2.charAt(c - 1) ) {
					dp[r][c] = dp[r-1][c-1] + 1;
				}
				else {
					dp[r][c] = Math.max(dp[r][c-1], dp[r-1][c] );
				}
			}
		}
		
		return dp[s1.length()][s2.length() ];
	}
}
