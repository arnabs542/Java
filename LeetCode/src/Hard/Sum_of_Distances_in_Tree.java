package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/sum-of-distances-in-tree/
/*
 * 	This is a Tree, Dynamic Programming, DFS problem.
 *	(Able to come up with solution, nice)
 *
 *	This problem takes some observation. First, one would propose to do brute force by considering each node
 *	as the root node, and the total distance would be just distances between the node to all of its child
 *	nodes. However, that would take O(N^2) time and simply not sufficient for this problem.
 *
 *	Let's see some computational algorithms first:
 *
 *	1. How to get number of nodes in subtree?
 *		- Relatively simple. Each node itself counts as 1, null nodes count as 0. The answer is simply
 *				1 + getNumNodes( childnodes ) 
 *
 *	2. How to get total distances from current node to ALL child nodes?
 *		- This require us to know the number of nodes covered by this subtree beforehand. Use algorithm (1)
 *		- If null node, there is no distance to cover, so return 0.
 *		  Otherwise, get a sum of all the distance covered by child nodes. Record it for DP purposes
 *		  When returning to the parent node, here's where we consider a fact:
 *				From Parent's perspective, all the nodes are further away by distance of 1. Therefore when
 *				returning, we need to return [ sumDistances + numNodesCoveredByThisSubtree ]
 *
 *	To aid in understand, consider the nodes:
 *			(0)
 *		  /		\
 *		(1)		(2)
 *			    /|\
 *			  (3)(4)(5)
 *
 *	First is the number of nodes covered by subtree, second is the total distance to child nodes
 *	(0) - [ 6, 8]
 *	(1) - [ 1, 0]
 *	(2) - [ 4, 3]
 *	(3) - [ 1, 0]
 *  (4) - [ 1, 0]
 *  (5) - [ 1, 0]
 *  
 *  
 *	Now, hopefully you'll see the solution is closer to us now. The total distances of a non-root node
 *	is calculated by:
 *			( total child node distances + total parent distances )
 *
 *	On an intermediate node, we already have total child node distances. We will need a reference to parent
 *	node, then we can calculate the total parent distances via:
 *
 *	We do DFS, so parent is already solved							Since all the other nodes would increase distance by 1
 *				V																		V
 *	[ (total distances of parent) - (contribution of yourself to parent) + (number of nodes not covered by yourself) ]
 *
 *	You may see implementation below for clearity.
 */

class Node {
	int id;
	public List<Node> connected;
	int childDistances = 0;
	int numNodes = 0;
	
	public Node(int id) {
		this.id = id;
		connected = new ArrayList<>();
	}
}

public class Sum_of_Distances_in_Tree {
	
	public int[] sumOfDistancesInTree(int n, int[][] edges) {
		// Step 1: Build graph
		Map<Integer, Node> graph = new HashMap<>();
		for (int i = 0; i < n; ++i)
			graph.put(i, new Node(i) );
		for (int[] edge: edges) {
			Node n1 = graph.get(edge[0]);
			Node n2 = graph.get(edge[1]);
			n1.connected.add(n2);
			n2.connected.add(n1);
		}
		
		// Step 2: Fill in number of nodes in subtrees
		fillNumNodes( graph.get(0), null);
		
		// Step 3: Fill child distances
		fillChildDistances(graph.get(0), null);
		
		// Step 4: Deduce the solution
		int[] res = new int[n];
		fillOverallDistances( graph.get(0), null, res);
		return res;
    }
	
	private void fillOverallDistances(Node node, Node parent, int[] res) {
		// Deduce the distance of this node
		
		// If the parent is non existent, then is a root. The distances are simply child distances
		if (parent == null) {
			res[node.id] = node.childDistances; 
		}
		// Otherwise, total distances is child distances + parent distances
		else {
			res[node.id] = node.childDistances
					+ ( res[parent.id] - (node.childDistances + node.numNodes) )	// Parent distance excluding youself
					+ (res.length - node.numNodes);		// All other node's distance + 1
		}
		
		for (Node n: node.connected) {
			if (n == parent) continue;
			fillOverallDistances(n, node, res);
		}
	}
	
	private int fillChildDistances(Node root, Node src) {
		if (root == null) return 0;
		int s = 0;
		for (Node c: root.connected) {
			if (c == src) continue;
			s += fillChildDistances(c, root);
		}
		root.childDistances = s;
		return s + root.numNodes;
	}
	
	private int fillNumNodes(Node root, Node src) {
		if (root == null) return 0;
		int n = 1;
		for (Node c: root.connected) {
			if (c == src) continue;
			n += fillNumNodes(c, root);
		}
		root.numNodes = n;
		return n;
	}
	
}
