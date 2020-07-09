package Medium;

//https://leetcode.com/problems/unique-paths-ii/

/*
 * Similar concept as the unique path I question. Except for this one, if there is any obstacle in the deepest row or rightmost column, then any grid to the left
 * or above, will end up as 0 ways.
 * 
 * As for intermediate grids, if we see an obstacle, we will simply mark this path as 0 instead of sum of right + below. Therefore when the grid sees the obstacle,
 * it knows there are 0 ways if he choose to move to the obstacle
 * 
 * 	This one we can make a 2D array, or better 1D array to solve as in unique path I solution. However, since the obstacleGrid itself is passed into the function,
 * 	we can just use that 2D grid passed in and resulting in O(1) space complexity solution
 */

public class Unique_Paths_II {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		
		int[][] dp = new int[rows][cols];
		for (int i = cols - 1; i >= 0; i -- ) {
			if (obstacleGrid[rows - 1][i] == 1)
				break;
			dp[rows - 1][i] = 1;
		}
		for (int i = rows - 1; i >= 0; i -- ) {
			if (obstacleGrid[i][cols - 1] == 1)
				break;
			dp[i][cols - 1] = 1;
		}
		
		for (int r = rows - 2; r >= 0; r -- ) {
			for (int c = cols - 2; c >= 0; c -- ) {
				if (obstacleGrid[r][c] == 1)
					dp[r][c] = 0;
				else
					dp[r][c] = dp[r+1][c] + dp[r][c+1];
			}
		}
		return dp[0][0];
	}
	
	public int uniquePath1D(int[][] obstacleGrid) {
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		
		int[] dp = new int[cols];
		
		for (int i = cols - 1; i >= 0; i-- ) {
			if (obstacleGrid[rows - 1][i] == 1)
				break;
			dp[i] = 1;
		}
		
		boolean isObstacle = false;
		for (int r = rows - 2; r >= 0; r -- ) {
			if (obstacleGrid[r][cols - 1] == 1)
				isObstacle = true;
			dp[cols - 1] = (isObstacle)? 0: 1;
			
			for (int c = cols - 2; c >= 0; c-- ) {
				if (obstacleGrid[r][c] == 1)
					dp[c] = 0;
				else
					dp[c] = dp[c] + dp[c + 1];
			}
		}
		return dp[0];
	}
	
	
	public int uniquePathInPlace(int[][] obstacleGrid) {
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		
		boolean flag = false;
		for (int i = cols - 1; i >= 0; i -- ) {
			if (obstacleGrid[rows - 1][i] == 1) 
				flag = true;
			obstacleGrid[rows - 1][i] = (flag)? 0: 1;
		}
		
		for (int r = rows - 2; r >= 0; r -- ) {
			obstacleGrid[r][cols - 1] = (obstacleGrid[r][cols - 1] == 1)? 0: obstacleGrid[r + 1][cols - 1];
			
			for (int c = cols - 2; c >= 0; c-- ) {
				if (obstacleGrid[r][c] == 1)
					obstacleGrid[r][c] = 0;
				else
					obstacleGrid[r][c] = obstacleGrid[r+1][c] + obstacleGrid[r][c+1];
			}
		}
		return obstacleGrid[0][0];
	}
	
	
	
}
