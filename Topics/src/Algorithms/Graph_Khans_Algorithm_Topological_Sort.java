package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * The Khan's Algorithm for Topological Sort
 * 
 * 	This algorithm takes advantage for the fact that for an acyclic graph (If the graph is cyclic, there would be no Topological sort for
 * 	it), the resulting sort must start with an vertex which has no incoming edge for it
 * 
 * 	Therefore the algorithm will count the number of in-degree for each vertex, and those with 0 in-degrees, will be added to a LIFO/FIFO
 * 	data structure
 * 	The function of the LIFO/FIFO data structure is to keep track of the vertex which had been reduced to 0 in-degrees, including the ones
 * 	that are initially already 0.
 * 
 * 	While there are still vertices in the LIFO/FIFO, take the vertex out, and cancel out the out-degrees of it pointing to other vertices.
 * 	We do this by iterating through the vertices connected to the node, and decrementing the count of in-degrees of that vertex by 1.
 * 	Once the in-degree of any connected nodes are reduced to 0, we can add them into the LIFO/FIFO data structure for it to be explored
 * 	later.
 * 	
 * 	After the iterations, the vertices in the LIFO/FIFO (or if you have them extracted out to external lists) will be ordered topologically.
 * 	
 * 	This algorithm even works with cyclic graphs. If the graph is cyclic, there will always be some vertices that will never
 *  have its in-degree reduced to 0, and the LIFO/FIFO will be exhausted before that. Therefore we could detect it by checking if the size
 *  matches the number of vertices in the graph
 */

public class Graph_Khans_Algorithm_Topological_Sort {
	
	//The edges are represented in array of size 2, [source, target]
	public static List<Integer> khanAlgo(int numNodes, int[][] edges) {
		
		//Construction of the graph, and also count the indegree counts
		HashMap<Integer, Set<Integer> > graph = new HashMap<>();
		int[] indegree = new int[numNodes];
		
		for (int[] edge: edges) {
			graph.putIfAbsent( edge[0], new HashSet<>() );
			graph.get(edge[0] ).add(edge[1] );
			
			indegree[edge[1] ] ++;
		}
		//Done construction
		
		//We can use LIFO/FIFO, or use a linear data structure with a pointer
		int pointer = 0;
		List<Integer> li = new ArrayList<>();
		
		//Add all vertices with in-degree 0 to the list
		for (int i = 0; i < numNodes; i ++ )
			if (indegree[i] == 0) li.add(i);
		
		//Then, go canceling the out-degrees of the vertices one by one, until either we hit cycle or the entire graph is explored
		while (pointer < numNodes && li.size() > pointer) {
			int vertex = li.get(pointer);
			
			for (int toCancel: graph.getOrDefault(vertex, new HashSet<>() ) ) {
				indegree[toCancel] --;
				
				if (indegree[toCancel] == 0) li.add(toCancel);
			}
			pointer ++;
		}
		
		//If the pointer is pointing to the end of list (which should be full of vertices by now, return it, else SOemthing's wrong with
		// the graph!
		return (pointer == numNodes)? li: null;
	}
	
	
	//----------------------------------------------------------------------------------------------------
	
	public static void main(String[]args) {
		int[][] edges = {
			{0,1},
			{0,2},
			{2,3},
			{2,4},
			{4,3}
		};
		int[][] cycle = {
				{0,1},
				{0,2},
				{2,3},
				{2,4},
				{4,2}
		};
		
		System.out.println( khanAlgo(5, edges) );
		System.out.println( khanAlgo(5, cycle) );
	}

}
