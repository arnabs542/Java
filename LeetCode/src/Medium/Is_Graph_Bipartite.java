package Medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/is-graph-bipartite/
/*
 * 	This is a graph coloring problem. Asking whether there can be set A and B where each node can only be either in A or B,
 * 	and its neighbors can only in the other set, is just exactly same with coloring the graph with 2 different colors!
 * 
 * 	------------------------------------------------------------
 * 
 * 	One solution is by breadth first search. Starting out by coloring one node, its neighbors must be in another color. 
 * 	If detected that neighboring nodes are of same color as parent node, then it is not a bipartite graph
 * 
 * 	------------------------------------------------------------
 * 
 * 	DFS solution also works well. Simply pass the colors array  which we use to record whether a node is in Set A
 * 	or B (or set) around.  
 */

public class Is_Graph_Bipartite {
	
	
	//	Breadth First Search solution. Note that Array can be used to mark A or B
	public boolean isBipartite(int[][] graph) {
		final int n = graph.length;
		Set<Integer> A = new HashSet<>();
		Set<Integer> B = new HashSet<>();
		
		//	Each node loop
		for (int i = 0; i < n; ++i) {
			//	Subgraph visited already. Skip
			if (A.contains(i) || B.contains(i) ) continue;
			
			//	Otherwise perform BFS
			Queue<Integer> queue = new ArrayDeque<>();
			Set<Integer> curr = A;
			Set<Integer> other = B;
			queue.offer(i);
			A.add(i);
			
			while ( !queue.isEmpty() ) {
				int lvl = queue.size();
				while (lvl-- > 0) {
					int node = queue.poll();
					
					for (int adj: graph[node] ) {
						if (curr.contains(adj) ) return false;
						if ( !other.add(adj) ) continue;		//	Other set already contains the node before. Explored!
						queue.offer(adj);
					}
				}
				curr = curr == A? B: A;
				other = curr == A? B: A;
			}
		}
		return true;
    }
	
	
	
	//	Depth First Search solution
	public boolean isBipartite2(int[][] graph) {
		final int n = graph.length;
		Set<Integer> A = new HashSet<>();
		Set<Integer> B = new HashSet<>();
		
		for (int i = 0; i < n; ++i) {
			if (A.contains(i) || B.contains(i) ) continue;
			A.add(i);
			if (!recurse(i, A, B, graph) ) return false;
		}
		return true;
	}
	private boolean recurse(int n, Set<Integer> curr, Set<Integer> other, int[][]graph) {
		for (int adj: graph[n]) {
			if (curr.contains(adj) ) return false;
			if ( !other.add(adj) ) continue;		//	Other set already contains the node before. Explored!
			if (!recurse(adj, other, curr, graph) ) return false;
		}
		return true;
	}
}
