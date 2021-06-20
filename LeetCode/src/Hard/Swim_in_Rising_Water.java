package Hard;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/swim-in-rising-water/
/*
 * 	Turns out, this is a shortest path / binary search and BFS problem.
 * 
 * 	From the question, what we are actually looking for is to find a path connecting node (0,0) to node (n-1,n-1),
 * 	with the largest element along the path minimized.
 * 
 * 	If we have a strong memory, recall what dijkstra algorithm does (If not familiar, look into Algorithms > Dijkstra Algorithm)
 * 	Dijkstra algorithm performs BFS, but does it greedily by always picking the next move with minimum cost. This is exactly
 * 	what we should do! If we are finding a path from upperleft to lowerright, do it greedily using Dijkstra algorithm
 * 	works perfectly fine!
 * 
 * 	Heap uses log(N) time, and potentially we need to explore all the grid, thus O(N log N). Space complexity is O(N) for
 * 	the heap and also visited array.
 * 
 * 	=========================================================================
 * 
 * 	Another way would be to perform binary search on the solution itself. See:
 * 	
 * 	The solution is always enclosed in range [1,N*N-1] according to the problem (Although impossible to be 1).
 * 	Thus, we can always make a guess on the solution, and check if it is true or not via BFS. 
 * 
 *  In other words, once we make a guess, we try to check if it's possible to travel from upperleft to lowerright
 *  with all elements in path smaller than or equal to our guesses.
 *  
 *  If it is possible, we could be greedy and check whether smaller guesses is also possible. If not possible, then
 *  make a larger guess. That is for binary search.
 */

public class Swim_in_Rising_Water {
	
	//	Dijkstra algorithm solution
	public int swimInWater(int[][] grid) {
		final int len = grid.length;
		final int[][] DIRS = {{-1,0}, {1,0}, {0,-1}, {0,1}};
		boolean[][] visited = new boolean[len][len];
		
		int res = 0;
		PriorityQueue<int[]> heap = new PriorityQueue<>( (x,y)-> {
			return grid[x[0]][x[1]] - grid[y[0]][y[1]];
		});
		heap.add( new int[] {0,0} );
		visited[0][0] = true;
		
		while (!heap.isEmpty() ) {
			int[] next = heap.poll();
			int i = next[0];
			int j = next[1];
			
			//	Update res
			res = Math.max(grid[i][j], res);
			
			//	If reached end, return
			if (i == len-1 && j == len-1) return res;
			
			//	Else explore. Remember do not create cycle
			for (int[] dir: DIRS) {
				try {
					if (!visited[i + dir[0]][j + dir[1]]) {
						visited[i+dir[0]][j+dir[1]] = true;
						heap.add( new int[] { i+dir[0], j+dir[1] } );
					}
				} catch (Exception e) {}
			}
		}
		return -1;
   	}
	
	
	//	Binary search on limit solution
	public int swimInWater2(int[][] grid) {
		final int len = grid.length;
		
		int leftlimit = 0, rightlimit = len*len-1;
		
		while (leftlimit < rightlimit) {
			int target = leftlimit + (rightlimit - leftlimit) / 2;
			
			//	Performs BFS to find out whether the target is possible or not
			if (bfs(grid, target)) rightlimit = target;
			else leftlimit = target+1;
		}
		return leftlimit;
	}
	
	private boolean bfs(int[][] grid, int limit) {
		if (grid[0][0] > limit) return false;
		
		final int len = grid.length;
		final int[][] DIRS = {{-1,0}, {1,0}, {0,-1}, {0,1}};
		
		boolean[][] visited = new boolean[len][len];
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {0,0});
		visited[0][0] = true;
		
		while (!queue.isEmpty() ) {
			int[] pos = queue.poll();
			if (pos[0] == len-1 && pos[1] == len-1) return true;
			
			for (int[] dir: DIRS) {
				int i = pos[0] + dir[0], j = pos[1] + dir[1];
				try {
					if (!visited[i][j] && grid[i][j] <= limit) {
						visited[i][j] = true;
						queue.add( new int[] {i, j});
					}
				} catch (Exception e) {}
			}
		}
		return false;
	}
	
}
