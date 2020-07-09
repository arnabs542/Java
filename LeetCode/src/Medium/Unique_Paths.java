package Medium;

//https://leetcode.com/problems/unique-paths/

/*
 * 	A Dynamic Programming Question (Although can possibly be solved using mathematics)
 * 	The sub question to be asked is: On my current grid, how many ways can i go to the lower right corner?
 * 	The obvious answer to the lowest row and rightmost column is always 1, since the robot can only go right or go down.
 * 	Then for any intermediate grids, the answer will be the sum of no of ways if going to right, plus no of ways if going downwards
 * 	Lastly return the answer at the original starting grid. The question become equivalent to asking:
 * 		From the starting grid, how many ways can i go to the lower right corner?
 * 
 * 
 * 	Although using a 2D array is very straightforward, we actually can solve the question using a 1D DP array.
 * 	The 1D DP array will represent a specific row. Notice on each grid, the operation is the sum of its right grid and its below.
 * 	Therefore we can just ensure that the DP array's rightmost value is always kept as value 1. Notice before overwriting, the value on the specific grid
 * 	actually keeps the value of the grid below itself! Therefore we can use this property and take only 1D array space to solve the question
 */

public class Unique_Paths {
	
	public int uniquePaths(int rows, int cols) {
		int[][] dp = new int[rows][cols];
		for (int i = 0; i < cols; i ++ ) {
			dp[rows - 1][i] = 1;
		}
		for (int i = 0; i < rows; i ++ ) {
			dp[i][cols - 1] = 1;
		}
		
		for (int r = rows - 2; r >= 0; r -- ) {
			for (int c = cols - 2; c >= 0; c -- ) {
				dp[r][c] = dp[r + 1][c] + dp[r][c + 1];
			}
		}
		
		return dp[0][0];
	}
	
	public int uniquePathsSave(int rows, int cols) {
		//Initially the row column will represent the deepest row, with all value as 1
		int[] dp = new int[cols];
		for (int i = 0; i < cols; i ++)
			dp[i] = 1;
		
		//Deepest row is all 1 and initialized. So start looping from the second deepest
		for (int r = rows - 2; r >= 0; r -- ) {
			//Notice i start loop from second last element. This way the value 1 at rightmost is kept
			for (int c = cols - 2; c >= 0; c --) {
				dp[c] = dp[c + 1] + dp[c];
			}
		}
		return dp[0];
	}

}
