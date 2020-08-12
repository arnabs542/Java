package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//https://www.hackerrank.com/challenges/dijkstrashortreach/problem

/*
 * 	This is a Dijkstra's Algorithm problem. Given weighted graph, we need to find the shortest path to every nodes given a starting
 * 	node
 * 
 * 	Notice that since there may be duplicated edges of different weight, we only always want to take the one edge with smallest
 * 	weight (distance), so when constructing the graph, we will use a List of HashMap, where:
 * 		>	List index is the node, and the content is the HashMap which stores the adjacent nodes
 * 		>	Key of HashMap is the adjacent node, and Value is the weight to the adjacent node from current node
 * 
 * 	Since we are using HashMap, avoiding duplicates are more easier. When met with a duplicate, just check if the value
 * 	of previous weight is greater or this one. Take the smaller one
 * 
 * 	
 */

public class Dijkstra_Shortest_Reach_2 {
	
	static int[] shortestReach(int n, int[][] edges, int s) {
		
		//Construction of Graph
		List< Map<Integer, Integer> > adjacent = new ArrayList<>();
		
		//Index 0 shall never be used, unless there is a node 0
		for (int i = 0; i <= n; i ++ ) {
			adjacent.add( new HashMap<>() );
		}
		
		for (int[] i: edges ) {
			Map<Integer, Integer> map1 = adjacent.get(i[0] );
			Map<Integer, Integer> map2 = adjacent.get(i[1] );
			
			map1.put( i[1], Math.min(map1.getOrDefault( i[1], Integer.MAX_VALUE), i[2] ) );
			map2.put( i[0], Math.min(map2.getOrDefault( i[0], Integer.MAX_VALUE), i[2] ) );
		}
		//Construction of graph done
		
		
		
		boolean[] visited = new boolean[n + 1];
		int[] result = new int[n - 1];
		for (int i = 0; i < n - 1; i ++ ) result[i] = -1;
		
		//  The records in the Heap will be an int array, { node , costSoFar }
		//	THis question does not require to find the parent node, so no need for that
		PriorityQueue<int[]> heap = new PriorityQueue<>( (x,y) -> {
			return x[1] - y[1];
		});
		
		//	The starting point add to the heap
		heap.offer( new int[] {s, 0} );
		
		while ( !heap.isEmpty() ) {
			int[] pairs = heap.poll();
			int node = pairs[0], cost = pairs[1];
			
			//	Since paths are always selected in optimal way in Dijkstra algo, if a node is visited before, we already have the shortest
			//	path already
			if (visited[pairs[0] ] ) continue;
			
			//	Note since question requires to exclude the starting node, if node is greater than s, then it should index at -2.
			//	Otherwise just index - 1 because of 0 indexing
			if (node > s && (result[node - 2] > cost || result[node - 2] == -1) ) {
				result[node - 2] = cost;
			}
			else if (node < s && (result[node - 1] > cost || result[node - 1] == -1) ) {
				result[node - 1] = cost;
			}
			
			//	For each adjacent, add them to the heap if not visited before
			Map<Integer, Integer> adj = adjacent.get(node);
			for (int i: adj.keySet() ) {
				if ( !visited[ i ] ) {
					heap.add( new int[] { i , cost + adj.get(i) } );
				}
			}
			
			visited[node] = true;
			
		}
		
		return result;
    }
	
}
