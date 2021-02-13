package Medium;

import java.util.ArrayDeque;
import java.util.Queue;

//https://leetcode.com/problems/shortest-path-in-binary-matrix/
/*
 * 	This is a Breadth First Search problem, since BFS is good for searching shortest distance (Apart from more
 * 	complex algorithms like A* algorithm)
 * 
 * 	Use a Queue data structure to perform BFS. The level of the bfs is important to know the distance of the bfs.
 *	Start from the left upper corner, begin breadth first search algorithm. For each grid, explore it's eight sides.
 *	If the adjacent neighbor is not occupied (0), then add that to the exploration queue.
 *	Also, we have to mark that the grid is pending to be explored, so that it don't get added to the queue twice.
 *	We can do this in place, by marking the grid to 1, or use extra space to enforce immutability.
 *
 *	Time complexity is O(N^2) if square matrix, and space is O(max(N,M) ), either edge is longer will take that much
 *	space in the queue. (Except if use visited set instead of in-place)
 */

public class Shortest_Path_in_Binary_Matrix {
	
	public int shortestPathBinaryMatrix(int[][] grid) {
		final int row = grid.length, col = grid[0].length;
		if (grid[0][0] == 1 || grid[row-1][col-1] == 1) return -1;
		

		final int[][] dirs = { {-1,-1}, {-1, 0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };
		Queue<int[]> bfs = new ArrayDeque<>();
		int level = 0;
		bfs.add( new int[] {0,0} );
		grid[0][0] = 1;
		
		while (!bfs.isEmpty() ) {
			++level;
			int levelSize = bfs.size();
			
			while (levelSize-- > 0) {
				int[] pos = bfs.poll();
				if (pos[0] == row - 1 && pos[1] == col -1 ) return level;
				
				for (int[] dir: dirs) {
					int r = pos[0] + dir[0], c = pos[1] + dir[1];
					try {
						if ( grid[r][c] == 0) {
							bfs.offer( new int[] { r,c } );
							grid[r][c] = 1;
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
		
		return -1;
    }
	
}
