package Medium;

//https://leetcode.com/problems/out-of-boundary-paths/
/*
 * 	This is a Dynamic Programming Problem
 * 
 * 	Given maximum moves, we want to know how many ways to kick the ball out of boundary.
 * 
 * 	Let's start up small. Say we only given 1 moves, then the only way is when the ball is at some boundary
 * 	itself. Check the 4 directions, and see if it goes out of bound. If it does, add 1 to the ways
 * 
 * 	Now, move up to 2 moves. Of course, we will still check for adjacent boundaries. However, we can also kick
 * 	it to adjacent grid that are not out of bounds! In that case, once the ball is kicked onto the selected grid,
 * 	I will be left with N-1 moves, right?
 * 	If I know the number of ways to kick the ball out of boundary with N-1 moves on that grid beforehand, I would
 * 	easily obtain the ways!
 * 
 * 	That introduces the Dynamic Programming solution, where we use 2D grid for tabulation! Every move, we simply
 * 	tabulate the number of ways to kick ball out of boundary based on previous tabulated data.
 */

public class Out_of_Boundary_Paths {

	//	Dynamic Programming solution
	public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
		final int[][] DIRS = {{-1,0}, {1,0}, {0,-1}, {0,1}};
		
		int[][] prev = new int[m][n];
		
		while (maxMove-- > 0) {
			int[][] curr = new int[m][n];
			
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					for (int[] d: DIRS) {
						try {
							curr[i][j] = (curr[i][j] + prev[i + d[0]][j + d[1]] ) % 1000000007;
						} catch (Exception e) {
							curr[i][j] += 1;
						}
					}
				}
			}
			prev = curr;
		}
		return prev[startRow][startColumn];
    }
	
}
