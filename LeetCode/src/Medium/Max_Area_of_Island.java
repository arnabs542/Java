package Medium;

//https://leetcode.com/problems/max-area-of-island/
/*
 * 	This is a classical DFS problem (Flood fill).
 * 
 * 	Iterate through each grid. If the grid is a land, perform DFS on it which check for
 * 	neighbors if it is land, but before doing that, ensure that the originating land is marked
 * 	as visited so we don't end up in infinite recursion loop.
 * 	
 * 	This can be modelled as a graph, where each node is connecting to its adjacent 4 sides in
 * 	the grid.
 */

public class Max_Area_of_Island {
	
	private int[][] DIRS = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public int maxAreaOfIsland(int[][] grid) {
		int res = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j)
				res = Math.max(res, recurse(grid, i, j) );
		}
		return res;
    }
	
	private int recurse(int[][] grid, int r, int c) {
		if (r == -1 || r == grid.length || c == -1 || c == grid[0].length || grid[r][c] == 0)
			return 0;
		
		int res = 1;		//	Since the grid is a land, start with 1
		grid[r][c] = 0;		//	Mark as visited. !Mutates the grid
		
		for (int[] d: DIRS)
			res += recurse(grid, r+d[0], c+d[1]);
		return res;
	}
}
