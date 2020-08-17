package Algorithms;

import java.util.Arrays;

//https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights/problem

/*
 * 	Floyd Warshall's algorithm is an DP algorithm used to find the shortest path between nodes. The difference is,
 * 	it will process ALL the nodes in the graph, so any query of the shortest path going from x to y will be retrieved in
 * 	O(1) time, since it is all preprocessed.
 * 
 * 	Dijkstra Algorithm, which finds the shortest path to all nodes from only a single source. If we have to know for
 * 	all the shortest path from all the nodes to any of the nodes, it will take O(N^3) time by repeating Dijkstra algorithm
 * 	for all the nodes as its single source node.
 * 	Alternatively, Floyd Warshall's algorithm does the same thing, and in O(N^3) time, but is rather easier to implement,
 * 	and IT WORKS EVEN IF THERE IS A NEGATIVE WEIGHTED GRAPH
 * 
 * ---------------------------------------------------------------------------------------------------------------------
 * 
 * 	First, we have to prepare a NxN matrix for storing the distance between the two nodes. 
 * 	The row (y axis) represents the source, and the col (x axis) represents the destination.
 * 	The values for each grid shall be:
 * 		>	0 if the source and target is same. In other words, row = col, which runs as a vertices line
 * 		>	Infinity if the source does not have a edge connecting to target node. It may be connected indirectly (Need to travel
 * 			through one or more extra nodes) or not at all. But for initialization, only consider direct edges
 * 		>	Edge weight if the source is connected to target node via a weighted edge directly
 * 
 *  Optionally, if we want to track the path, we can have another NxN matrix, which stores the INTERMEDIATE node that the shortest
 *  path is derived from. Initially, those are going to be the source node stored itself
 *  
 * 
 * 	Now we will run 3 loops.
 * 	The first outer loop will loop through nodes from 1 to n. This loop determines the intermediate node, which we will compare
 * 	with the currently existing shortest path weights. Let the intermediate node be K.
 * 	The other 2 loops are just looping the Row (source node) and Col (Target nodes). The question asked for each grid is:
 * 
 *		Is ( X to Y ) cheaper or ( X to K then K to Y ) cheaper?
 *
 * 	There will be some cases that we will encounter:
 * 		>	Source and target are same
 *			-	Since it is initialized as 0, The question ( 0 ) vs ( X to K, K to Y ) shall be selected if X to K and K to Y
 *				results in negative weight or not. If it does have negative, then that means going through some nodes and return to
 *				itself will result in negative distance (which is the minimum)
 *		>	No paths to source and target
 *			-	Since there are no paths directly from X to Y, the value is INFINITY, we can only hope that there is a path from
 *				X to K, K to Y such that the value is not INFINITY which is able to overwrite the prior value
 *	
 *	Basically how it works is it considers all the intermediate nodes that X and Y can go through, and take the minimum
 *	between the previous result, and the new result which involves passing through intermediate node ( INFINITY if it is not
 *	possible to go through intermediate node)
 *
 *	If the intermediate node path did overwrite the previous result, then the intermediate node shall be recorded in the source
 *	matrix, indicating that the shortest path reaching the target node comes from the intermediate node
 */

public class Floyd_Warshall_Algorithm {
	
	private int[][] distances;
	private int[][] parent;

	//	Note: This is a directed graph implementation! Assuming there is no 0 weight edges
	//	n is number of nodes. Source, target and weight are the edges respectively
	public void preprocess (int n, int[] source, int[] target, int[] weight) {
		int m = source.length;
		
		distances = new int[n][n];
		parent = new int[n][n];
		
		//	Preprocessing: Connecting the edges into distances matrix, as well as parent matrix
		for (int i = 0; i < m; i ++ ) {
			int src = source[i];
			int tgt = target[i];
			int wgt = weight[i];
			
			distances[src - 1][tgt - 1] = wgt;
			parent[src -1][tgt - 1] = src;
		}
		//	For the rest which doesn't have direct edge connection, unless src and target is same, set it to INFINITY
		for (int r = 0; r < n; r ++ ) {
			for (int c = 0; c < n; c ++ ) {
				if (distances[r][c] == 0 && r != c)
					distances[r][c] = Integer.MAX_VALUE;
			}
		}
		
		//	The intermediate node selected
		for (int k = 1; k <= n; k ++ ) {
			
			for (int r = 0; r < n; r ++ ) {
				for (int c = 0; c < n; c ++ ) {
					
					//	There is path from src to K, and there is path from K to target, and it is cheaper than direct path
					//	from src to target
					if (distances[r][k - 1] != Integer.MAX_VALUE &&
						distances[k - 1][c] != Integer.MAX_VALUE &&
						distances[r][k-1] + distances[k-1][c] < distances[r][c] )
					{
						distances[r][c] = distances[r][k-1] + distances[k-1][c];
						parent[r][c] = k;
					}
					
				}
			}
			
		}
		
	}
	//	End of preprocessing
	
	
	//	Do note that if it returns MAX VALUE, it means going from src to tgt is impossible!
	public int query(int from, int to) {
		return distances[from - 1][to - 1];
	}
	
}
