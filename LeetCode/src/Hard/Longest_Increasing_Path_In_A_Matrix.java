package Hard;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
/*
 * 	This is a graph DFS traversal with Dynamic Programming - Memoization problem.
 * 
 * 	Every grid in the matrix is a node in graph. The node is connected to another node if the other node has value strictly
 * 	greater than current node. 
 * 	Due to the nature of going from lower value to higher value, the graph is directed graph, and there shall not exist any
 * 	cycle in the graph.
 * 
 * 	If we were to perform brute force, we iterate through each node. For each of the nodes, we simply perform dfs on it and
 * 	see how many maximum layer it can span.
 * 	At the end, simply return the maximum layer of one source node as answer. 
 * 	Say the matrix is NxN in size, then the time complexity will be about O(N^4), not efficient at all.
 * 
 * 	------------------------------------------------------------------------------------------
 * 
 * 	Some of the nodes may have been visited before! Say i have matrix of 1 row: [5,4,3,2]. Now include the newest column
 * 	[5,4,3,2,1]. On node (1), I have to iterate through 5,4,3,2 once more, although it has been previously determined,
 * 	that (2) can span to level 4!
 * 
 * 	Therefore simply use a dp array to store the results of DP. 
 * 
 * 	Say we are exploring a node. If we found out its neighbour haven't been visited yet, we perform the very same DP on it,
 * 	so that DP value of neighbor will be defined and we are able to evaluate the value of neighbor.
 * 
 * 	Time is thereby reduced to O(N^2), as each node is never reexplored, all is stored in DP array
 */

public class Longest_Increasing_Path_In_A_Matrix {
	
	private static int[][] dirs = { {1,0}, {0,1}, {-1,0}, {0,-1} };
	
	public int longestIncreasingPath(int[][] matrix) {
		final int row = matrix.length, col = matrix[0].length;
		int[][] dp = new int[row][col];
		int res = 1;
		
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (dp[i][j] == 0) dfs(i, j, matrix, dp);		//	Never explore before
				res = Math.max(res, dp[i][j]);					//	In any case, DP[i][j] will now be defined
			}
		}
		return res;
    }
	
	private void dfs(int i, int j, int[][] matrix, int[][] dp) {
		int res = 1;
		for (int[] d: dirs) {
			int i2 = i + d[0], j2 = j + d[1];
			try {
				if (matrix[i][j] < matrix[i2][j2]) {				//	Only explore if neighbor value is greater.
					if (dp[i2][j2] == 0) dfs(i2, j2, matrix, dp);	//	Neighbor never explore b4. Explore neighbor first
					res = Math.max(res, dp[i2][j2] + 1);			
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
		dp[i][j] = res;
	}
	
}
