package Algorithms;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*
 * 	Graph are made of vertices and edges. Vertices are also called as nodes. Edges are lines connecting between vertices.
 * 	Graph are usually noted by notation G(V,E) where V is vertices and E is edges
 * 	Graph can be both undirected (No pointing, just connection), or directed (Each edge must be one directional only, from one node to another)
 * 	In directed graphs the number of in-degree edges must be equal to number of out-degree edges. An edge is in-degree and out-degree at
 * 	the same time. It must be pointing to a vertex (as in-degree) from source vertex (as out-degree)
 * 
 * 	Degree of a vertex - Meaning number of edges connected to a particular vertex
 * 	In-degree of a vertex - Meaning number of edges pointing to the particular vertex (Directed graph only)
 * 	Out-degree of a vertex - Meaning number of edges pointing out from the particular vertex (Directed graph only)
 * 
 * 	Topological Sort of a graph is the linear ordering (like array) of the vertices/nodes such that for every edges (Arrows) pointing from
 * 	u to v, the vertex u must always be ordered before v in the ordering.
 * 	By observation, if we were to arrange the vertices in a linear order, all the edges will be pointing to the right and none of it
 * 	pointing to the left.
 * 
 * 	An example for it is given a list of tasks, and some tasks must be executed before another.
 * 
 * 	Given a directed graph (node pointing to another node) which is acyclic (No cycles), how can you find a possible ordering
 * 	of the nodes such that it covers ALL THE NODES, and follows the direction of the edges?
 * 
 *  One example of this is that given a list of courses, which some courses may have some prerequisites of another course. Find
 *  a way to cover all the courses in order. (Leetcode problem - Course Schedule)
 *  Another example would be to build a software system, where some software is dependent on another software and will crash without it
 *  . Find the order to build the softwares so that no software will crash or error due to some software haven't been built.
 *  
 *  This sorting of graph is called Graph topological sort, where the graph with the lower incoming edges should be sorted in front
 *  of those with more incoming edges, and the first node when sorted should have 0 incoming edges going to them.
 *  
 *  
 *  One approach is to use DFS with a Stack representing the sorted nodes and a Visited Set to record if a node has been visited.
 *  We would go over all the nodes to avoid missing out any of the nodes. We would perform a DFS on each node, where the node is first
 *  added to the visited Set, and DFS the child nodes. Once the child nodes had all been explored, the node itself is said to be
 *  safe and can be added to the Sorting Stack.
 *  
 *  At the end, we would be popping out elements from the stack and in the order that it is popped, is the nodes arranged topologically.
 *  
 *  -------------------------------------------------------------------------------------------------------------------------------
 *  
 *  However, if the graph is cyclic, how would we go detecting them, and make conclusion that to cover all the nodes are impossible?
 *  We would use another Set to represent the nodes that's being covered so far. For each node, add the node itself into the cycle set,
 *  and pass it into the recursive function when performing DFS of child nodes. If the recursive function detects that a node was in
 *  a cycle set, it immediately knows that it had already formed a cycle, and therefore return indicators that a cycle is found.
 *  
 *  It is similar to coloring the nodes. White would mean unexplored nodes. Grey would mean nodes that are in the midst of performing DFS, and
 *  Black would mean the node is fully explored and closed. If during DFS a node is encountered to be Grey, it indicates a cycle is found.
 *  This coloring of nodes can be implemented using HashMap, where node itself is the key and color is the value.
 *  
 *  ------------------------------------------------------------------------------------------------------------------------------------
 *  
 *  
 */


public class Graph_Topological_Sort {
	
	//Each node shall be numbered from 0 to numNodes - 1
	//Edges are represented in array of size 2. [From , To]
	public static List<Integer> findOrder(int numNodes, int[][] edges) {
		
		//Construction of Graph
		Map<Integer, List<Integer>> graph = new HashMap<>();
		
		for (int[] edge: edges) {
			graph.putIfAbsent(edge[0], new LinkedList<>() );
			graph.get(edge[0] ).add(edge[1] );
		}
		
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> topoSort = new Stack<>();
		Set<Integer> inCycle = new HashSet<>();
		
		//Looping through all the nodes and perform recursion on them
		for (int i = 0; i < numNodes; i ++ ) {
			//If the recursion returns false, which means it has detected cycle, then just return null indicating it's impossible to cover all
			//nodes
			if (!recurse(i, graph, visited, inCycle, topoSort) ) return null;
		}
		
		LinkedList<Integer> res = new LinkedList<>(topoSort);
		Collections.reverse(res);
		return res;
		
	}
	
	private static boolean recurse(int node, Map<Integer, 
			List<Integer>> graph, 
			Set<Integer> visited, 
			Set<Integer> cycle, 
			Stack<Integer> topoSort) {
		
		//If this node, which is in process of DFS, is being DFS again, it's a cycle!
		if (cycle.contains(node) ) return false;
		//If this node is visited and closed, then just don't visit this node
		if (visited.contains(node) ) return true;
		
		//Add this node into part of cycle and mark as visited
		visited.add(node);
		cycle.add(node);
		
		//Some nodes may be the last node (Meaning no edges pointing to another node). They are absent in the Hash Map itself
		if (graph.containsKey(node) ) {
			//Loop through all the edges of the node
			for (int nextNode: graph.get(node) ) {
				
				//If recursion returns false meaning there is a cycle, then pass the information to the upper recursive layer
				if (!recurse(nextNode, graph, visited, cycle, topoSort) ) return false;
			}
		}
		
		//This node is done exploration. Remove this node from cycle and mark this node as closed. Push it into the topological sort Stack
		cycle.remove(node);
		topoSort.push(node);
		return true;
	}

	
//-----------------------------------------------------------------------------------------------------------
	
	public static void main(String[]args) {
		int[][] edges = {
			{0,1},
			{0,2},
			{2,3},
			{2,4},
			{2,4}
		};
		int[][] cycle = {
				{0,1},
				{0,2},
				{2,3},
				{2,4},
				{4,2}
		};
		
		System.out.println( findOrder(5, edges) );
		System.out.println( findOrder(5, cycle) );
	}
	
}
