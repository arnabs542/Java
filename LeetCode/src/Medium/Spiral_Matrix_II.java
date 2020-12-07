package Medium;

//https://leetcode.com/problems/spiral-matrix-ii/

/*
 * 	This is an Array problem.
 * 
 * 	The way we can solve it is by simulate the process as if we are filling it ourselves.
 * 	
 * 		First, we fill from top row, from left to right. Note that after done, the top row is done so we
 * 		will not touch it anymore.
 * 
 * 		Then, we fill in at rightmost column, from previous row + 1 to bottom. Note that after done, the rightmost
 * 		column is done so we will not touch it anymore
 * 
 * 		Then, we fill in at bottom row, from previous column until leftmost column. Note that after done, the bottom
 * 		row is done so we will not touch it anymore
 * 
 * 		Lastly, we fill in at leftmost column, from previous row - 1 until top (but not touching the first row which
 * 		is done!). Note that after done, the leftmost column is done so we will not touch it anymore. 
 * 
 * 	As we can see, we just have to keep 4 variables indicating the bounds of the 4 sides, and with 4 loops in a
 * 	larger loop where each iteration completes one cycle, we can get it done.
 * 
 * 	----------------------------------------------------------------------------------
 * 
 * 	To prevent 4 loops in one large loop, we can use array and a direction variable to indicate the direction
 * 	we are travelling. As soon as we hit the edges or the next grid to fill is not 0 (Previously filled), then
 * 	we will change direction.
 */

public class Spiral_Matrix_II {
	
	public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        
        int[] rowBounds = {0, n};
        int[] colBounds = {0, n};
        
        for (int i = 1; i <= n*n; ) {
        	//	Left to right in topmost row
        	for (int a = colBounds[0]; a < colBounds[1]; ++a ) 
        		res[rowBounds[0]][a] = i++;
        	++rowBounds[0];
        	
        	//	Top to bottom in rightmost column
        	for (int a = rowBounds[0]; a < rowBounds[1]; ++a)
        		res[a][colBounds[1] - 1] = i++;
        	--colBounds[1];
        	
        	//	Right to left in bottom row
        	for (int a = colBounds[1] - 1; a >= colBounds[0]; --a)
        		res[rowBounds[1] - 1][a] = i++;
        	--rowBounds[1];
        	
        	//	Bottom to top in leftmost column
        	for (int a = rowBounds[1] - 1; a >= rowBounds[0]; --a)
        		res[a][colBounds[0]] = i++;
        	++colBounds[0];
        }
        return res;
    }
	
	
	
	
	public int[][] generateMatrix2(int n) {
		int[][] directions = { {0,1}, {1,0}, {0,-1}, {-1,0} };
		int[][] res = new int[n][n];
		int direction = 0;
		int row = 0;
		int col = 0;
		
		
		for (int i = 1; i <= n*n; i++ ) {
			res[row][col] = i;
			
			//	Predict the next grid to fill. If unable, change direction
			int nextRow = row + directions[direction][0];
			int nextCol = col + directions[direction][1];
			
			//	Bounds checking for next grid, also check if is filled previously
			if ( (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) ||
				 (res[nextRow][nextCol] != 0) )
				direction = (direction + 1) % 4;
			
			//	Update next row and column
			row = row + directions[direction][0];
			col = col + directions[direction][1];
		}
		return res;
	}
	
}
