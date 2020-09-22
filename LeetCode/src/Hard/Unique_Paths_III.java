package Hard;

//https://leetcode.com/problems/unique-paths-iii/

/*
* 	This is a backtracking, DFS problem
* 
* 	First, we have to obtain extra information from the grid itself. We need to know:
* 	>	How many non-obstacle grids are there, so we know when we have explored all non-obstacle grids
* 	>	The starting grid position, to know where to start the DFS backtracking
* 
* 	Then, for each grid in the recursion (or do it iteratively), we first need to define the base case:
* 		>	If it is the ending grid, add 1 to the result (Meaning one path discovered) only if all the non-obstacle grids are
* 			explored (Represented as a counter which decrements)
* 	Otherwise, mark the grid as explored (Changing the value in place), then explore all 4 directions, but based on some constraints
* 	>	Not explored grid
* 	>	Not out of bounds
* 	>	Not obstacle
* 
* 	After doing the exploration on 4 direction and obtained their results, revert the change to the grid itself. and return the value
*/

public class Unique_Paths_III {
	
	public int uniquePathsIII(int[][] grid) {
		
		int row = grid.length;
		int col = grid[0].length;
		
		//Find out number of obstacles and empty paths, and starting position
		int n = row * col;
		int[] start = new int[2];
		for (int i = 0; i < row; i ++ ) {
			for (int j = 0; j < col; j ++ ) {
				n -= (grid[i][j] == -1)? 1: 0;
				
				if (grid[i][j] == 1) {
					start[0] = i;
					start[1] = j;
				}
			}
		}
		
		return recurse(grid, start[0], start[1], n - 1, row, col);
		
	}
	
	private static int recurse(int[][] grid, int r, int c, int left, int row, int col) {
		int path = grid[r][c];
		
		if (path == -1 || path == Integer.MAX_VALUE) return 0;
		if (path == 2) return left == 0? 1: 0;
		
		int res = 0;
		grid[r][c] = Integer.MAX_VALUE;
		
		if (r-1 >= 0 ) res += recurse(grid, r-1, c, left - 1, row, col);
		if (r+1 < row ) res += recurse(grid, r+1, c, left - 1, row, col);
		if (c-1 >= 0 ) res += recurse(grid, r, c-1, left - 1, row, col);
		if (c+1 < col ) res += recurse(grid, r, c+1, left - 1, row, col);
		
		grid[r][c] = path;
		
		return res;
	}
	
}
