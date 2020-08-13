package Algorithms;

import java.util.PriorityQueue;

import Data_Structures.Disjoint_Sets;

//https://www.hackerrank.com/challenges/primsmstsub/problem

/*
 * 	Kruskals Algorithm is an algorithm to find minimum spanning tree.
 * 	Before reading this, you shall be knowing about Prims Algorithm first! See Prims_Algorithm
 * 
 * 	To find the minimum spanning tree of a graph in Kruskals way, we have to do the following:
 * 
 * 		Since Kruskals always select the minimum edge first without consideration of BFS, we first need to sort the edges all into
 * 		ascending order (In min heap)
 * 
 * 		All the nodes has to be grouped into disjoint sets (To represent the groups connected), which initially all node are all
 * 		individual set of their own
 * 
 * 	Whenever we poll an edge, we check if both nodes are in same set in disjoint set. If not, that means they are not connected in
 * 	groups yet. Connect them (Select this edge).
 * 	If they are in same set, then just ignore this edge. They have been connected before with smaller edge (Since all edges are sorted)
 */

public class Kruskals_Algorithm {

	static int kruskals(int n, int[][] edges, int start) {
		PriorityQueue<int[] > heap = new PriorityQueue<>( (x, y) -> {
			return x[2] - y[2];
		});
		boolean[] visited = new boolean[n + 1];
		
		for (int[] edge: edges) {
			heap.offer(edge);
		}
		
		Disjoint_Sets dset = new Disjoint_Sets(n);
		
		int cost = 0;
		int selectedEdges = 0;
		
		while ( !heap.isEmpty() && selectedEdges < n - 1 ) {
			int[] polled = heap.poll();
			int node1 = polled[0];
			int node2 = polled[1];
			int weight = polled[2];
			
			if ( dset.findRepresentative(node1) == dset.findRepresentative(node2) ) continue;
			
			dset.union( node1, node2);
			cost += weight;
			selectedEdges ++;
		}
		
		return cost;
	}
}
