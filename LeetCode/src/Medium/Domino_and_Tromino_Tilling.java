package Medium;


//https://leetcode.com/problems/domino-and-tromino-tiling/
/*
 * 	This is a dynamic programming problem.
 * 
 * 	----------------------------------------------------------------------------	
 * 
 * 	Let's get an intuition of the problem first:
 * 	
 * 	The domino board is strictly of height 2 only. Therefore, when filling out the board, I can always end up in 2
 * 	states in each column:
 * 
 * 		In each column, I can have 2 vacant space (STATE 0), or 1 vacant space (STATE 1)
 * 
 * 	Think of this board of size 3:
 * 	[ ][ ][ ]
 * 	[ ][ ][ ]
 * 
 * 	If i place a domino straight up, I will proceed to next column in state 0 (Column 1 has 2 vacant space)
 * 	[X][ ][ ]
 *  [X][ ][ ]
 *  	^
 *  
 *  If i place a tromino instead, I will proceed to next column in state 1 (Column 1 has 1 vacant space)
 *  [X][X][ ]
 *  [X][ ][ ]
 *  	^
 *  
 *  You get the idea.
 *  
 *  Therefore, by observing how each states can transition to next column with either state 1 or state 2, we can solve
 *  the problem.
 *  Let recurse function f( colNo, state ) returns the number of ways to complete the board when provided starting point
 *  at colNo with current state.
 *  
 *  
 *  If state == 0 (2 Vacant slot)
 *  
 *  1. 	Place domino upright [X]     ===> f( colNo+1, 0 )
 *  			     	     [X]
 *  
 *  2.  Place domino horizontal     [X][X]   =====> f( colNo+2, 0 )
 *		(We must put 2)				[X][X]
 *
 *	3.  Place trimino (2 ways to place)   [X][X]
 *										  [X]
 *											or			====> f( colNo+1, 1 )
 *										  [X]
 *										  [X][X]
 *
 *	If state == 1 (1 Vacant slot)
 *
 *	1. 	Place domino horizontal    [-][X][X]			====> f( colNo+1, 1 )
 *								   [-][-]
 *
 *	2.  Place trimino (either one)	[-][X][X]
 *									[-][-][X]			====> f( colNo+2, 0 )
 *									or
 *									[-][-][X]
 *									[-][X][X]
 * 	
 * 	
 * 	Therefore, you can see that if we have board of size N, then we need to determine at most 2N possible states, which
 * 	isn't so bad. We should use dynamic programming here so that we don't end up recomputing the states, which reduces
 * 	the time complexity greatly
 * 
 * 
 *  =================================
 *  
 *  To make the space optimization possible, we need to reverse the thinking:
 *  
 *  Instead of looking into the future (Asking how many ways to solve given colNo and state), we need to look back
 *  into the past - (How many ways to form colNo-1 or colNo-2 with state 0 or 1). Since now it only requires us to
 *  look at colNo-1 and colNo-2, we can optimize space complexity to O(1)
 */

public class Domino_and_Tromino_Tilling {

	public int numTilings(int n) {
		long[][] dp = new long[n][2];
		for (int i = 0; i < n; ++i) {
			dp[i][0] = -1;
			dp[i][1] = -1;
		}
		return (int) recurse(n, 0, 0, dp);
    }
	
	
	private long recurse(int n, int i, int state, long[][] dp) {
		if (n == i && state == 0) return 1;
		if (i >= n) return 0;
		// Memoization
		if (dp[i][state] != -1) return dp[i][state];
		
		long res;
		// General case 1 - We have 2 empty slots here.
		if (state == 0)
			 res = ( recurse(n, i+1, 0, dp) + recurse(n, i+2, 0, dp) + recurse(n, i+1, 1, dp) * 2 ) % 1000000007;
		// General case 2 - We have 1 empty slot here.
		else 
			res = ( recurse(n, i+1, 1, dp) + recurse(n, i+2, 0, dp) ) % 1000000007;
		
		dp[i][state] = res;
		return res;
	}
	
}
