package Medium;

//https://leetcode.com/problems/unique-paths/

/*
 * 	A Dynamic Programming Question (Although can possibly be solved using mathematics)
 * 
 *	By having a 2D grid of DP array representing the board itself, each grid can record the following information:
 *		- Assuming this grid is the destination (bottom right grid), how many unique paths are there to arrive at the current
 *		  grid?
 *
 *	The transition function is that, to reach the current grid, there is only two possibilities:
 *		>	Coming from grid above
 *		>	Coming from grid to the left
 *	Since these two grids are precomputed due to DP, we can lookup and sum these two DP grid and get the answer to the current
 *	DP grid.
 *	
 *	At the end, the answer would be at the bottom right grid of DP.
 *
 *	-------------------------
 *
 *	Since the transition function only uses the row above and the grid to the left, we can optimize the solution to be O(N) space
 *	only.
 */

public class Unique_Paths {
	
	public int uniquePaths(int rows, int cols) {
		int[][] dp = new int[rows][cols];
		for (int i = 0; i < cols; i ++ ) {
			dp[0][i] = 1;
		}
		
		
		for (int r = 1; r < rows; ++r ) {
			dp[r][0] = 1;
			
			for (int c = 1; c < cols; ++c )
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
		}
		
		return dp[rows-1][cols-1];
	}
	
	public int uniquePathsSave(int rows, int cols) {
		int[] dp = new int[cols];
		for (int i = 0; i < cols; i ++)
			dp[i] = 1;
		
		for (int r = 1; r < rows; ++r ) {
			for (int c = 1; c < cols; ++c) 
				dp[c] = dp[c - 1] + dp[c];
		}
		return dp[ cols - 1 ];
	}

}
