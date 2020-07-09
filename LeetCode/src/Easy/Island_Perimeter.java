package Easy;

//https://leetcode.com/problems/island-perimeter/

/*
 * 	This question is solved by iterating through all the grids. If encountered a land, then find out the perimeter associated with this piece of land, where 
 * 	each side facing a water will increase the perimeter by 1.
 * 
 * 	To optimize, since we are iterating from top left corner to the right and eventually until bottom right, and realizing the fact that for a piece of land which
 * 	it has another neighboring island, then the perimeter is already effectively -2 since the neighboring island cannot include the perimeter where it is facing
 * 	with the current island now.
 * 	Therefore when we iterate, we only check the right and bottom islands, and count the number of islands as well as neighbors. After the iteration,
 * 	return islands * 4 (Each island maximum has 4 unit of perimeter, but unlikely if the land is connected) - neighbors * 2 (We count the neighbors of right and bottom only)
 * 	
 */

public class Island_Perimeter {
	public int islandPerimeter(int[][] grid) {
		int perim = 0;
		
		for (int row = 0; row < grid.length; row ++ ) {
			for (int col = 0; col < grid[0].length; col ++ ) {
				if (grid[row][col] == 1) {
					perim += 4 - (row != 0 && grid[row-1][col] == 1? 1: 0)
							   - (row != grid.length - 1 && grid[row+1][col] == 1? 1: 0)
							   - (col != 0 && grid[row][col-1] == 1? 1: 0)
							   - (col != grid[0].length - 1 && grid[row][col+1] == 1? 1: 0);
				}
			}
		}
		return perim;
	}
	
	
	public int islandPerimeterOpt(int[][] grid) {
		int islands = 0, neighbours = 0;
		
		for (int row = 0; row < grid.length; row ++ ) {
			for (int col = 0; col < grid[0].length; col ++ ) {
				if (grid[row][col] == 1) {
					islands ++;
					if (row < grid.length - 1 && grid[row+1][col] == 1) neighbours ++;
					if (col < grid[0].length - 1 && grid[row][col+1] == 1) neighbours ++;
				}
			}
		}
		
		return islands * 4 - neighbours * 2;
	}
}
