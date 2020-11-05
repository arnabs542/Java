package Medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/minimum-height-trees/

/*
 * 	In a tree where every node can be travelled from another root node with only ONE path. (Yes, since there are no cycles,
 * 	a node can be accessed from another node with only one path, NO MULTIPLE PATHS ARE AVAILABLE)
 * 
 * 	We have to think of a root node that we select, that gives us the minimum depth tree available.
 * 	
 * 	One unexpected thought is: How many such minimum depth tree that it can form, given any number of nodes?
 * 	The answer for that is: Maximum 2.
 * 	
 * 	We can imagine the whole tree as a circle or a area. To choose the root node, we must not choose the edge 
 * 	ones because the distance from one edge node to another end would span over a great distance and thus resulting in
 * 	not the minimum depth tree.
 * 	Therefore, the optimal root node should be to choose the center ones (Centroid). And by proving by contradiction,
 * 	it can be shown that only maximum 2 such root node can exist in a tree.
 * 	Suppose we have 3 nodes that result in minimum depth tree. When we trimmed them from the leaf nodes, suppose
 * 	the nodes left are those 3 nodes. Those 3 nodes must now have depth of only 1. However, it is impossible since
 * 	those 3 nodes are to be connected somehow, and without cycles. The only way to form equal depth is by a triangular
 * 	edge, but that would oppose the property of tree. Therefore, the maximum is only 2.
 * 
 * 	As you can see, since every node must be connected. One way to solve this is like topological sorting, eliminating
 * 	those leaves nodes, and check to see if the nodes connected are becoming leaf nodes as well. If they does, then
 * 	remove them in the next turn.
 * 	By shredding off the leaf nodes in each turn, eventually we would reach at the centroid nodes which is the root nodes
 * 	that will form minimum depth tree.
 * 
 * 	Another way is to find out the 2 edge nodes, like 'diameter' of the whole tree. First iteration DFS we will start
 * 	with any node, and get the maximum depth node from it. Then in the second DFS, we will use that last node which is
 * 	one of edges node, and attempt to find the other maximum depth node, which is on the other end, while tracking the
 * 	path used to get there.
 * 	Since now we have the nodes on the two opposite ends, we can find out the middle distance, and obtain the centroid
 * 	for it. If the length is even (like there are 2 nodes in between), then we have to return 2 centroid nodes in our
 * 	answer
 * 
 * 
 */

public class Minimum_Height_Trees {
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n <= 2) {
			List<Integer> res = new ArrayList<>();
			for (int i = 0; i < n; i++)
				res.add(i);
			return res;
		}
        
		Set<Integer>[] nodes = new Set[n];
		for ( int[] edge: edges ) {
			int n1 = edge[0];
			int n2 = edge[1];
			if (nodes[n1] == null) nodes[n1] = new HashSet<Integer>();
			if (nodes[n2] == null) nodes[n2] = new HashSet<Integer>();
			
			nodes[n1].add(n2);
			nodes[n2].add(n1);
		}
		
		Deque<Integer> trimmer = new ArrayDeque<>();
		for (int i = 0; i < n; i ++ ) {
			if ( nodes[i].size() == 1 ) trimmer.add( i );
		}
		
		while (n > 2) {
			int bst = trimmer.size();
			
			for (int i = 0; i < bst; i++ ) {
				int leaf = trimmer.poll();
				for ( int connected: nodes[leaf] ) {
					Set<Integer> connectedset = nodes[ connected ];
					connectedset.remove(leaf);
					if (connectedset.size() == 1)
						trimmer.add( connected );
				}
				nodes[leaf] = null;
				n--;
			}
		}
		
		return new ArrayList<>( trimmer );
    }
	
	
	
	public List<Integer> findMinHeightTreesDFS(int n, int[][] edges) {
		if (n <= 2) {
			List<Integer> res = new ArrayList<>();
			for (int i = 0; i < n; i++)
				res.add(i);
			return res;
		}
        
		Set<Integer>[] nodes = new Set[n];
		for ( int[] edge: edges ) {
			int n1 = edge[0];
			int n2 = edge[1];
			if (nodes[n1] == null) nodes[n1] = new HashSet<Integer>();
			if (nodes[n2] == null) nodes[n2] = new HashSet<Integer>();
			
			nodes[n1].add(n2);
			nodes[n2].add(n1);
		}
		int[] distances = new int[n];
		int[] parents = new int[n];
		
		int edge1 = findMaxDist( nodes, distances, parents, 0 );
		
		distances[ edge1 ] = 0;
		parents[ edge1 ] = Integer.MIN_VALUE;
		
		int edge2 = findMaxDist( nodes, distances, parents, edge1 );
		System.out.println( Arrays.toString(parents ) );
		
		List<Integer> res = new ArrayList<>();
		int dist = distances[ edge2 ] + 1;
		int parent = parents[ edge2 ];
		for (int i = dist / 2; i >= 0; i --) {
			parent = parents[ parent ];
			
			if (dist % 2 == 0 && i < 2) {
				res.add( parent );
			}
		}
		
		return res;
	}
	private int findMaxDist( Set<Integer>[] graph, int[] dist, int[] parent, int curr ) {
		int p = parent[curr];
		int maxNode = curr;
		
		for (int neigh: graph[curr] ) {
			if (neigh == p) continue;
			dist[neigh] = dist[curr] + 1;
			parent[neigh] = curr;
			
			int res = findMaxDist( graph, dist, parent, neigh );
			if ( dist[maxNode] < dist[res] ) maxNode = res;
		}
		return maxNode;
	}
	
}








