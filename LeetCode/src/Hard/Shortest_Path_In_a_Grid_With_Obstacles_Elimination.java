package Hard;

import java.util.ArrayDeque;
import java.util.Queue;

//https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
/*
 *	Can you believe this problem can be solved without any DP? Just pure BFS?
 *	This is a BFS, matrix problem.
 *
 *	Let's see some edge cases: If grid is 1x1, the distance is 0. If given k is larger than the row and column length,
 *	we can immediately take the shortest path by going along the edges in L shape.
 *
 *	At a particular grid, we know we can come from above, below, left and right (The problem would have become DP if
 *	we are only allowed to move right/down). Therefore, some grids would take shorter distance to reach if we previously
 *	chose to destroy some obstacles using our moves. How do we record that?
 *
 *	Let's approach the problem first from BFS point of view. Using BFS, we can perform level order traversal which we
 *	know what 'distance' we are currently on. We will have a Queue of 'moves' that we had explored previously to
 *	continue exploring in the current turn. 
 *
 *	Since we are using BFS, whenever we had reached the bottom right, it must be the shortest path so we can immediately
 *	return the current 'level' of BFS we are in. 
 *	However in this problem, we would possibly had reached a grid at different number of k left (Available obstacle eliminations).
 *	Therefore apart from recording visited grid at (x,y), we would extend its dimensionality to record also the k left.
 *
 *	visited[x][y][z] = true 	means that grid[x][y] is visited previously with z available obstacle eliminations left.
 *
 *	This would mean time complexity of O( M * N * K), which in this case suffices. Space complexity is the same.
 */

public class Shortest_Path_In_a_Grid_With_Obstacles_Elimination {
	
	public int shortestPath(int[][] grid, int k) {
		final int[][] DIRS = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		final int rows = grid.length;
		final int cols = grid[0].length;
		if (rows == 1 && cols == 1) return 0;
		if (k >= rows + cols - 2) return rows + cols - 2;
		
		boolean[][][] visited = new boolean[rows][cols][k+1];
		// Stores steps in { row, col, kLeft }
		Queue<int[]> queue = new ArrayDeque<>();
		
		// Start at (0,0) with k eliminations left
		queue.add(new int[] {0, 0, k});
		visited[0][0][k] = true;
		
		// BFS
		for (int layer = 0; !queue.isEmpty(); ++layer) {
			int size = queue.size();
			while (size-- > 0) {
				int[] step = queue.poll();
				int r = step[0], c = step[1], e = step[2];
				
				// 4 directions.
				// Conditions to NOT explore a grid
				// 1. Invalid, out of bounds grid
				// 2. Involves elimination of obstacle with 0 moves left
				// 3. A more inexpensive path already existed

				for (int[] d: DIRS) {
					int nr = r + d[0], nc = c + d[1];
					if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
					int elimLeftIfMove = grid[nr][nc] == 1? e - 1: e;
					if (elimLeftIfMove == -1 || visited[nr][nc][elimLeftIfMove]) continue;
					
					if (nr == rows-1 && nc == cols-1)
						return layer+1;
					
					visited[nr][nc][elimLeftIfMove] = true;
					queue.add(new int[] {nr, nc, elimLeftIfMove});
				}
			}
		}
		return -1;
    }
	
}
