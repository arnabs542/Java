package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://www.hackerrank.com/challenges/bfsshortreach/problem

/*
 * 		This problem is solved using BFS technique. Given a starting node, we will perform search layer by layer, where in
 * 		each layer we increase the cost by 6.
 * 		
 * 		Things to notice:	There are duplicates in provided edges. So use a HashSet instead of a linked list to avoid collisions
 * 						    and infinite loops
 * 							It's better to check for visited when it is popped, not check it when we are looping through the 
 *							adjacent nodes. This is because it may have duplicate in provided edges, meaning the same node
 *							would have been added to queue if we didn't check for visited on popped!
 */

public class Breadth_First_Search_Shortest_Reach {
	
	static int[] bfs(int n, int m, int[][] edges, int s) {
		
		Map<Integer, Set<Integer> > graph = new HashMap<>();
		for (int[] edge: edges) {
			graph.putIfAbsent( edge[0], new HashSet<>() );
			graph.putIfAbsent( edge[1], new HashSet<>() );
			graph.get(edge[0] ).add(edge[1] );
			graph.get(edge[1] ).add(edge[0] );
		}
		
		int[] res = new int[n - 1];
		for (int i = 0; i < n - 1; i ++ ) res[i] = -1;
		
		boolean[] visited = new boolean[n];
		Queue<Integer> bfs = new LinkedList<>();
		
		int currcost = 0;
		bfs.offer(s);
		
		while (!bfs.isEmpty() ) {
			int size = bfs.size();
			
			for (int i = 0; i < size; i ++ ) {
				int node = bfs.poll();
				
				if (visited[node - 1] ) continue;
				
				visited[node -1] = true;
				
				if (node > s)
					res[node - 2] = currcost;
				else if (node < s)
					res[node - 1] = currcost;
				
				if (graph.containsKey(node) ) {
					for (int next: graph.get(node) ) {
						bfs.offer(next);
					}
				}
				
			}
			
			currcost += 6;
		}
		
		return res;
		
    }
	
}
