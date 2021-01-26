package Medium;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/path-with-minimum-effort/
/*
 *	This is a Graph problem. In the matrix, each grid is a node, and it connects to other nodes
 *	via an edge which the weight is calculated by the absolute difference of the two grid values.
 *
 *	Therefore, the problem now boils down to become finding shortest path from top left grid to bottom
 *	right grid only.
 *
 *	--------------------------------------------------------------------------
 *
 *	Binary Search with BFS
 *	----------------------
 *
 *	We could set a limit on the maximum edge weight we are allowed to pass through only. If the BFS does
 *	find a path connecting to bottom right, we could be more greedy and try to find a lower limit.
 *
 *	Otherwise if such path doesn't exist, the limit is too low. Try a higher limit.
 *
 *	The time complexity for this one is O(MN log(maxcost) ). In this case maxcost = 10^6 which logged will
 *	become approximately 19.
 *
 *	------------------------------------------------------------------------------
 *
 *	Dijkstra Algorithm
 *	--------------------
 *
 *	The popular shortest path algorithm is Dijkstra's Algorithm. Since the paths cannot be negative, it works
 *	very fine in this case.
 *
 *	Visit Topics > Algorithms > Dijkstra_Algorithm for more info.
 *
 *	--------------------------------------------------------------------------------
 *
 *	Bellman's Ford Algorithm 
 *	-------------------------
 *
 *	The algorithm works, but it is slower as the number of edges are quite many. Bellman's Ford algorithm
 *	is workable in this case as there is no negative edge cycle involved.
 *
 *	Visit Topics > Algorithms > Bellman_Ford_Single_Source_Shortest_Path_Algorithm for more info.
 */

public class Path_With_Minimum_Effort {
	
	//	A Structured Data containing x coord, y coord and the effort cost
	//	Optionally, you can also use int[] of size 3, much better
	class Path {
		int x, y, effort;
		public Path(int x, int y, int effort) {
			this.x = x; this.y = y; this.effort = effort;
		}
	}
	
	
	//	Binary Search (Guessing least effort) + BFS solution
	public int minimumEffortPath(int[][] heights) {
		int l = 0, r = 1000000;
		
		while (l < r) {
			int mid = l + (r - l) / 2;
			if (bfs(heights, mid) ) r = mid;
			else l = mid + 1;
		}
		return l;
	}
	private boolean bfs(int[][]heights, int limit) {
		final int row = heights.length, col = heights[0].length;
		final int[] dirs = {0, -1, 0, 1, 0};			//	A trick to be applied: encoding for all directions
		
		boolean[][] visited = new boolean[row][col];
		visited[0][0] = true;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add( new int[] {0,0} );
		
		while (!queue.isEmpty() ) {
			int[] n = queue.poll();
			int x = n[0], y = n[1];
			if (x == row -1 && y == col -1) return true;
		
			for (int i = 0; i < 4; ++i) {
				int a = x + dirs[i], b = y + dirs[i+1];
				
				if (a < 0 || a >= row || b < 0 || b >= col) continue;
				if (visited[a][b] ) continue;
				if (Math.abs(heights[a][b] - heights[x][y] ) > limit) continue;
				visited[a][b] = true;
				queue.add( new int[] {a,b} );
			}
		}
		return false;
	}
	
	
	//	Dijkstra Algorithm except it just record visited in a table.
	//	Dijkstra works because the edges are no way negative  - it is absolute difference
	public int minimumEffortPath2(int[][] heights) {
		final int row = heights.length, col = heights[0].length;
		
		boolean[][] visited = new boolean[row][col];
        PriorityQueue<Path> heap = new PriorityQueue<>( (x,y)-> {
        	return x.effort - y.effort;
        });
        heap.add( new Path(0,0,0) );
        
        while (!heap.isEmpty() ) {
        	Path node = heap.poll();
        	int x = node.x;
        	int y = node.y;
        	int eff = node.effort;
        
        	if ( visited[x][y] ) continue;
        	if (x == row - 1 && y == col - 1) return eff;
        
        	//	Go up
        	if (y - 1 >= 0 && !visited[x][y-1] )
        		heap.add( new Path(x, y-1, Math.max(eff, Math.abs(heights[x][y] - heights[x][y-1] ) ) ) );
        	//	Go down
        	if (y + 1 < col && !visited[x][y+1] )
        		heap.add( new Path(x, y+1, Math.max(eff, Math.abs(heights[x][y] - heights[x][y+1] ) ) ) );
        	//	Go left
        	if (x - 1 >= 0 && !visited[x-1][y] )
        		heap.add( new Path(x-1, y, Math.max(eff, Math.abs(heights[x][y] - heights[x-1][y] ) ) ) );
        	//	Go right
        	if (x + 1 >= 0 && !visited[x+1][y] )
        		heap.add( new Path(x+1, y, Math.max(eff, Math.abs(heights[x][y] - heights[x+1][y] ) ) ) );
        	
        	visited[x][y] = true;
        }
        return -1;
    }

}
