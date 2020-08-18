package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://www.hackerrank.com/challenges/even-tree/problem

/*
 * 	THis is a DFS problem.
 * 	Given the tree is a tree with even number of nodes, we can attempt to disconnect every subtree that has even nodes in it.
 * 
 * 	At a given node, as soon as we found out that a subtree of it contains an even number of nodes in it, we can immediately
 * 	disconnect the subtree and the current node. That is the idea of solving the problem
 * 
 * 	So we will perform a DFS similar to finding the nodes in the subtree. As soon as one of the subtree node count is divisible by
 * 	2 (but not 0!), increment the count variable by 1 (Meaning we disconnected the subtree)
 */

public class Even_Tree {
	
	static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {
		Map<Integer, Set<Integer> > tree = new HashMap<>();
		for (int i = 1; i <= t_nodes; i ++ ) {
			tree.put( i, new HashSet<>() );
		}
		
		for (int i = 0; i < t_edges; i ++ ) {
			int n1 = t_from.get(i);
			int n2 = t_to.get(i);
			
			tree.get(n1).add(n2);
			tree.get(n2).add(n1);
		}
		
		int[] res = new int[1];
		
		recurse(1, tree, new boolean[t_nodes + 1], res );
		return res[0];
    }
	
	static int recurse(int node, Map<Integer, Set<Integer> > tree, boolean[] visited, int[] res) {
		if (visited[node]) return 0;
		
		visited[node] = true;
		int count = 1;
		
		for (int next: tree.get(node) ) {
			int recurseRes = recurse(next, tree, visited, res);
			if (recurseRes != 0 && recurseRes % 2 == 0) {
				res[0] ++;
			} else {
				count += recurseRes;
			}
		}
		return count;
	}
	
}
