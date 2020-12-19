package Hard;

//https://leetcode.com/problems/cherry-pickup-ii/
/*
 * 	This is a HARD 3D tabulation Dynamic programming problem. I am unable to solve without looking at solution
 * 
 * 	The unique states of this problem are - At each row, the robots can be at 2 unqiue or same coordinates.
 * 	Therefore, to do DP, we need to have a 3D array
 * 		-	First index is the row that both robots are on
 * 		-	Second index is the column of the first robot
 * 		-	Third index is the column of the second robot
 * 
 * 	On each grid, it records the maximum cherry that can be picked by both the robots, from beginning until this
 * 	grid
 * 
 * 	** Note that from the topmost row to the bottom row is THE SAME AS from bottom row to topmost row. Since they are
 * 	symmetric, we prefer to do bottom up because although we sacrifice extra computations, it makes our mental health
 * 	better 
 * 
 * 	
 * 	At each grid, we know that the robot will pick the cherry at their individual positions
 * 			Cherry pick by robot1 at its pos + Cherry pick by robot2 at its pos
 * 
 * 	Except if both robots are at same position, the cherry cannot be double counted so minus it
 * 
 * 	Then, here is where DP comes in. Since from bottom,
 * 
 * 		>	Say robot is at j. It could come from the below row, j-1, j, or j+1
 * 		>	Say robot is at k. It could come from the below row, k-1, k, or k+1
 * 
 * 	Now, 3 x 3 means 9 grids we have to check for. 
 * 
 * 	Eventually up until the top row, we have our answer at dp[0][0][col-1], meaning
 * 	the maximum cherry picked by robots at row 0, robot1 at 0 and robot2 at col-1
 * 
 * 	------------------------
 * 
 * 	Similarly if we don't want to do bottom up DP by tabulation, we can use recursion with memoization
 * 
 */

public class Cherry_Pickup_II {
	
	public int cherryPickup(int[][] grid) {
		final int rows = grid.length;
		final int cols = grid[0].length;
		
		int[][][] dp = new int[rows][cols][cols];
		
		for (int r = rows - 1; r>=0; --r) {
			for (int c1 = 0; c1 < cols; ++c1 ) {
				for (int c2 = 0; c2 < cols; ++c2) {
					
					int max = 0;
					// Find out best dp
					for (int j1 = c1 - 1; j1 <= c1+1; ++j1) {
						for (int j2 = c2 - 1; j2 <= c2+1; ++j2) {
							int val;
							if (r+1 >= rows || j1 < 0 || j1 >= cols || j2 < 0 || j2 >= cols) val = 0;
							else val = dp[r+1][j1][j2];
							val += grid[r][c1] + grid[r][c2];
							if (c1 == c2) val -= grid[r][c1];
							max = Math.max( val, max);
						}
					}
					
					dp[r][c1][c2] = max;
					
				}
			}
		}
		return dp[0][0][cols-1];
    }
	
	
	
	
	public int cherryPickup2(int[][] grid) {
		int[][][] memoi = new int[grid.length][grid[0].length][grid[0].length];
		return recurse( grid, memoi, 0, 0, grid[0].length - 1);
	}
	private int recurse(int[][] grid, int[][][] memoi, int y, int x1, int x2) {
		
		if (y >= grid.length) return 0;
		final int len = grid[0].length;
		
		int val = grid[y][x1] + grid[y][x2];
		if (x1 == x2) val -= grid[y][x1];
		
		int max = 0;
		for (int nxt1 = x1 - 1; nxt1 <= x1+1; ++nxt1) {
			for (int nxt2 = x2 - 1; nxt2 <= x2+1; ++nxt2 ) {
				if (x1 < 0 || x1 >= len || x2 < 0 || x2 >= len) continue;
				
				if (y+1 < grid.length && memoi[y+1][nxt1][nxt2] != 0) max = Math.max(max, memoi[y+1][nxt1][nxt2] );
				else max = Math.max(max, recurse(grid, memoi, y+1, x1, x2) );
			}
		}
		
		memoi[y][x1][x2] = val + max;
		return val + max;
	}
	
}
