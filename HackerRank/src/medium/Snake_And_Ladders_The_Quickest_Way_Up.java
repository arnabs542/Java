package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//https://www.hackerrank.com/challenges/the-quickest-way-up/problem

/*
 * 	My approach is to using DP.
 * 	The steps required to reach 1 is of course 0.
 * 	For each new step, I will be checking the previous 6 blocks, as well as the answer stored inside this grid itself.
 * 	(If the grid itself is uninitialized, then i will take INFINITY, else, that means we've reached here before)
 *  Take the minimum from the previous 6, and add 1 to it.
 * 	This way i will get the answer to the minimum step to reach this grid.
 * 
 * 	Then, if this grid is the ladder, then I will just store the current grid dp value into the target grid itself, but we shall
 * 	compare with the answer stored in the target grid first (If uninitialized, INFINITY), take the minimum out of the two
 * 
 * 	If the grid is a snake however, we need to check if the target grid has higher steps than the current grid now. If yes,
 * 	we will move the pointer down to the target grid, overwrite it and start over again. Else, just ignore the snake and continue
 * 
 * 	=================================================================================
 * 
 * 	Another easier approach is to just use BFS.
 * 	We represent each of the snake grids as a node in the graph. A node will have 6 out-degree edges pointing to the 6 nodes in front
 * 	of it.
 * 	Now, a key point to realize is that those grids which are the starting point of a ladder or snake, as soon as we land on it,
 * 	we will be transported immediately to that grid. Therefore instead of having a node pointing to the one which is the starting
 * 	point of ladder or snake, we direct the edge to directly point to the target node of the snake and ladder.
 * 
 * 	Keeping the VISITED set and a Queue to store the nodes to be explored. We initiailly put the first node into the Queue.
 * 	When popped, we add all of the possible nodes that first node can travel to into the queue, if they are not in the VISITED set.
 * 	
 * 	As soon as we popped a node 100 from the queue, we can already know the answer about how much steps required to reach the node.
 * 	Else if it is an impossible game, the Queue would have been exhausted first and we know that node 100 is beyond reach	
 * 
 * 
 * 
 */

public class Snake_And_Ladders_The_Quickest_Way_Up {
	
//	static int quickestWayUp(int[][] ladders, int[][] snakes) {
//		Map<Integer, Integer> grid = new HashMap<>();
//		for (int[] i: ladders)
//			grid.put(i[0], i[1]);
//		for (int[] i: snakes)
//			grid.put(i[0], i[1]);
//		
//		int[] dp = new int[101];
//		int pointer = 2;
//		
//		while (pointer <= 100) {
//			dp[pointer] = findmin(dp, pointer, grid);
//			
//			//	Have a ladder or a snake
//			if (grid.containsKey(pointer) ) {
//				int dest = grid.get(pointer);
//				
//				//Ladder
//				if (dest > pointer) {
//					dp[dest] = Math.min( dp[dest] == 0? Integer.MAX_VALUE: dp[dest] , dp[pointer] );
//				}
//				//Snake
//				else {
//					if (dp[dest] > dp[pointer] ) {
//						dp[dest] = dp[pointer];
//						pointer = dest + 1;
//						continue;
//					}
//				}
//			}
//
//			pointer ++;
//		}
//		
//		return dp[100];
//		
//    }
//	
//	private static int findmin(int[] dp, int pointer, Map<Integer, Integer> grid ) {
//		int min = dp[pointer] == 0? Integer.MAX_VALUE: dp[pointer];
//		
//		int limit = Math.max(0, pointer - 6);
//		for (int i = pointer - 1; i >= limit; i --) {
//			if (grid.containsKey(i) ) continue;
//			
//			min = Math.min(min, dp[i] + 1);
//		}
//		
//		return min;
//	}
	
	
	static int quickestWayUp(int[][] ladders, int[][] snakes) {
		int[][] adjacentList = new int[101][6];
		
		//	Setting up the adjacency List
		for (int i = 1; i <= 100; i ++ ) {
			int[] node = adjacentList[i];
			int limit = Math.min(100, i + 6);
			int idx = 0;
			for (int j = i + 1; j <= limit; j ++ ) {
				node[idx++] = j;
			}
		}
		
		//	Change the ladders starting grids to the targets node
		for (int[] ladder: ladders) {
			int start = ladder[0], target = ladder[1];
			for (int i = 1; i <= 6; i ++ ) {
				if ( start - i <= 0 ) break;
				int[] node = adjacentList[start - i];
				node[i - 1] = target;
			}
		}
		
		//	Change the snakes starting grids to the targets node
		for (int[] snake: snakes) {
			int start = snake[0], target = snake[1];
			for (int i = 1; i <= 6; i ++ ) {
				if ( start - i <= 0 ) break;
				int[] node = adjacentList[start - i];
				node[i - 1] = target;
			}
		}
		
		int rounds = 0;
		
		//	We MUST at least set up a visited set so that each node is visited exactly ONCE only!
		boolean[] visited = new boolean[101];
		Queue<Integer> nodes = new LinkedList<>();
		nodes.add(1);
		
		while ( !nodes.isEmpty() ) {
			int elems = nodes.size();
			
			for (int i = 0; i < elems; i ++ ) {
				int node = nodes.poll();
				if (node == 100) return rounds;
				
				visited[node] = true;
				
				for (int adj: adjacentList[node] ) {
					if (visited[adj] ) continue;
					nodes.add( adj);
				}
			}
			rounds ++;
		}
		
		return -1;
	}
	
	
	
	

}
