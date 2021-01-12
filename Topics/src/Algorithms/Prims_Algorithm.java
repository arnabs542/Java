package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 	Given an weighted, undirected graph. We shall find the spanning tree for it!
 * 
 * 	A Spanning tree is a sub-tree (sub-graph) of the origianl grpah, where there is exactly N - 1 edges only (where N is no of nodes)
 * 	In other words, it is the tree that has exactly included all the original nodes, but the edges are kept at a minimum
 * 	
 * 	Eg: 
 * 
 * 
 * 			(1)	---	(2)												 (1) --- (2)
 * 			 |	 /   |     ======SPANNING TREE(N-1 Edges ONLY) ==>    |		  |
 * 			(3)	---	(4)												 (3)     (4)
 * 
 * 
 * 	Since the edges are weighted, we can try and find the MINIMUM Spanning tree, where the sum of the edges of spanning tree
 * 	are the minimum out of all possible combinations
 * 
 * 	There are 2 algorithms for this: Prim's Algorithm and Kruskal's Algorithm
 * 
 * 	-----------------------------------------
 * 	
 * 	For Prim's algorithm, we start out from a starting node. We first explore the starting node's connected nodes, and add
 * 	them to a MIN HEAP.
 * 	Now, after fully explored, we will be polling from the heap. The polled edge will always be the smallest (Due to MIN HEAP).
 * 	We check if the edge is leading us to an unexplored node, without FORMING A CYCLE (Leading to both already visited nodes).
 * 	If it does, then we will choose to form this edge, and continue on with the node exploration
 * 
 * 	Prims algorithm follows BFS, with also GREEDY Method for edge selecting, which requires usage of a MIN HEAP
 * 	Using Prim's algorithm, a tree is always maintained without having a far away edge to be selected. The implementation
 * 	is done using Queues and Min Heap.
 * 
 * 	Eg:
 * 			(1)
 * 		  5	/ \ 2
 * 		  (2)-(3)
 * 			 3
 * 
 * 	Say we start BFS at (1). Explore (1). Edges of 5 and 2 are added to the min heap. Since 2 is smallest, 2 is popped,
 * 	and an connection from (1)-(3) is formed. 
 * 	
 * 	(3) is established so explore (3). We discover Edge of weight 3. Push into heap. Now heap contains 3 and 5, and 3
 * 	is smaller so it is popped.
 * 
 * 	The MST is formed as edges popped is now N-1. Total cost is 2+3 = 5
 */

public class Prims_Algorithm {
	
	static int prims(int n, int[][] edges, int start) {
		
		/*	Construction of graph.
		 * 	The list index is the node that we are current visiting. We shall not use index 0.
		 * 	The list item is an Hashmap, where
		 * 		>	The key is the node that is connected to current node by an edge
		 * 		>	The value is the weight of this path
		 * 
		 * 	Note that there may be duplicate edges. Therefore we use hashmap, replacing with minimum edge out of duplicates
		 */
		List< Map<Integer, Integer> > graph = new ArrayList<>();
		for (int i = 0; i <= n; i ++ )
			graph.add( new HashMap<>() );
		
		for (int[] edge: edges) {
			Map<Integer, Integer> node1 = graph.get( edge[0] );
			Map<Integer, Integer> node2 = graph.get( edge[1] );
			
			node1.put( edge[1], Math.min( edge[2], node1.getOrDefault(edge[1], Integer.MAX_VALUE) ) );
			node2.put( edge[0], Math.min( edge[2], node1.getOrDefault(edge[0], Integer.MAX_VALUE) ) );
		}
		//	END OF GRAPH CONSTRUCTION
		
		
		
		
		
		//	Ensure that a node is never visited twice
		boolean[] visited = new boolean[n + 1];
		//	The edges will be stored as { node, weight }
		//	The heap will arrange them in ascending order based on the weights
		PriorityQueue<int[]> queue = new PriorityQueue<>( (x,y) -> {
			return x[1] - y[1];
		});
		
		//Initialize
		queue.offer( new int[] {start, 0} );
		
		int cost = 0;
		
		while (!queue.isEmpty() ) {
			int[] explore = queue.poll();
			int node = explore[0];
			int weight = explore[1];
			
			if (visited[node] ) continue;
			
			visited[node] = true;
			cost += weight;
			
			Map<Integer, Integer> edge = graph.get(node);
			for (int adj: edge.keySet() ) {
				if (!visited[adj] ) {
					queue.add( new int[] {adj, edge.get(adj) } );
				}
			}
		}
		
		return cost;
    }

}
