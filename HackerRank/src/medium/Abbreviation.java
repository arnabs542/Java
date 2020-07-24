package medium;

//https://www.hackerrank.com/challenges/abbr/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This is a DP question regarding strings.
 * 	Given String b which is all upper case to match, We could perform operations on string a as follows:
 * 		- We can transform a character to UPPER CASE in string a
 * 		- All the lower case characters in string a will be removed
 * 
 * 	Therefore by DP, we divide the problem into subproblems, separating the original String a and b to start from its prefix (Initially of
 * 	empty string, then include characters one by one), and try to match a and b
 * 
 * 	Using DP, we will build a A x B size grid, each cell representing whether this matching success or fails, where at cell (x,y), is the
 * 	matching of substring a of size x and substring b of size y (The more you go down or right, the longer the substring of a or b)
 * 
 * 	Let's assume we are currently trying to match a last most character in string a with the last most character in string b. See:
 * 		
 * 		----IN CASE WHERE A'S CHARACTER IS LOWERCASE----------
 * 		>	If it matches (When transformed to uppercase), We could do two things: 1. Make it uppercase, then check previous state (Where
 * 																					  newest A char and B char is not included, dp's upper left)
 * 																					  if it's true
 * 																				   2. Still delete it, check the upper grid where we are assuming
 * 																					  this newest character was still not added
 * 		>	If it doesn't match, We could only delete it (Check upper grid as well)
 * 
 * 		----IN CASE WHERE A'S CHARACTER IS UPPERCASE----------
 * 		>	If it matches, We could check if the previous state (Where newest A char and B char is not included, which is dp's upper left
 * 			adjacent cell) is true. If it is false, there's still no way we could match this
 * 		>	If it doesn't, We could not delete it so the match fails (Only false)
 * 
 * 		
 *	Optimization: Since in DP we only use the last row's information, we could just keep 2 rows and alternate using each other. This
 *	saves the space complexity to just about O(2b)
 *
 */

public class Abbreviation {
	
	static String abbreviation(String a, String b) {
		int lenA = a.length(), lenB = b.length();
		
		boolean[] dp1 = new boolean[lenB + 1], dp2 = new boolean[lenB + 1];
		boolean useDp2 = false;
		
		dp2[0] = true;
		
		for (int r = 0; r < lenA; r ++ ) {
			
			boolean[] dp = (useDp2)? dp2: dp1;
			boolean[] lastdp = (useDp2)? dp1: dp2;
			char c1 = a.charAt(r);
			boolean upper1 = Character.isUpperCase(c1);
			c1 = Character.toUpperCase(c1);
			
			dp[0] = !upper1 && lastdp[0];
			
			for (int c = 0; c < lenB; c ++ ) {
				char c2 = b.charAt(c);
				
				if (upper1) {
					dp[c + 1] = (c1 == c2)? lastdp[c]: false;
				}
				else {
					dp[c + 1] = (c1 == c2)? lastdp[c] || lastdp[c + 1]: lastdp[c + 1];
				}
			}
			
			useDp2 = !useDp2;
			
//			for (boolean bool: dp) {
//				System.out.print(bool + " ");
//			}
//			System.out.println();
		}
		
		
		boolean res = (useDp2)? dp1[lenB] : dp2[lenB];
		return res? "YES": "NO";
		
    }
	
}
