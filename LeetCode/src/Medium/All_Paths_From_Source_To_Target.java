package Medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/all-paths-from-source-to-target/

/*
 * 	This is a DFS / BFS problem which requires to find all paths from node 0 to node n-1. We are required to explore each node at least once.
 * 	
 * 	By using BFS, we would fully explore one node. For each child node, we would create a linked list including the parent node itself and all the previous nodes,
 * 	append it with the neighboring node, and add it to the queue.
 * 
 * 	Each time the element is polled from the queue, the very last element (Node) is checked. If it is indeed the last node we are reaching, then add that to the
 * 	result list. Else, do the same as above: For each neighboring nodes, make a copy of the linked list, append neighbor, and add to queue for further searching
 * 
 * 	-----------------------------------------------------------------------------------------
 * 
 *  For DFS to implement by searching, it is almost same as above. Fully search for the last node from the 0th node till the end.
 *  
 *  You may notice that if the graph is very big, probabilities of the same node being searched again and again will become greater. This is when
 *  we can implement caching (Or Memoization) which records all the paths to reach last node from current node. Then if the same node is being DFS again,
 *  it can immediately return the result without having to search again.
 *  
 *  Notice if we found out by the end of the iterations the possible paths for this node to the last node is empty, just put null, indicating we've searched
 * 	this node, but no available paths to go to the last node.
 */

public class All_Paths_From_Source_To_Target {
	
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		
		HashMap<Integer, List<List<Integer> > > cache = new HashMap<>();
		
		recurse(0, graph, cache);
		
		return cache.get(0) == null? new LinkedList<>(): cache.get(0);
		
	}
	
	private static void recurse(int node, int[][] graph, HashMap<Integer, List<List<Integer>> > cache) {
		if ( cache.containsKey(node) ) return;
		
		List<List<Integer>> paths = new LinkedList<>();
		
		if ( node == graph.length - 1) {
			paths.add( new LinkedList<>() );
			paths.get(0).add(node);
			cache.put( node, paths);
			return;
		}
		
		for (int nextNode: graph[node] ) {
			recurse(nextNode, graph, cache);
			
			List<List<Integer> > nextCache = cache.get(nextNode);
			if (nextCache != null) {
				for (List<Integer> i: nextCache) {
					List<Integer> newli = new LinkedList<>(i);
					newli.add(0, node);
					paths.add(newli);
				}
			}
			
		}
		
		if (paths.isEmpty() ) cache.put( node, null);
		else cache.put( node, paths);
		
	}
	
	
	public static void main(String[]args) {
	
	}
}
