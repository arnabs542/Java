package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 	Given a directed or non-directed graph with weights. We can think of the weights as the distance or cost to travel 
 * 	from one node to another.
 * 	
 * 	Find the shortest distance to travel from a single source to another destination node. Or maybe, I want to know 
 * 	the minimum cost to travel from single source to ALL of the other nodes. 
 * 	
 * 	Dijkstra Algorithm works if the weight is ALL NON-NEGATIVE VALUES. In case of graph involving negative edges,
 * 	it may or may not work!
 * 
 * 	The idea is to perform a breadth first search on the starting node, and record the cost to get from source node 
 * 	to its adjacent nodes into a table. Meaning, we would record all the minimum cost to travel to all the nodes, 
 * 	until we've reached at the target node or fully visited the whole graph
 * 	
 *  Initially, all the costs to travel to the other nodes are initialized at a very large number (possibly infinity).
 *  When we saw the cost to travel to this node was actually cheaper than the recorded cost (initially infinity), 
 *  we'll replace it (and possibly record the parent node so that we can trace back the path later)
 * 
 * 	Say we have fully explored the starting node and ready to jump into the next node to explore. How do we select
 * 	the next node? Do we just choose randomly?
 * 	Dijkstra thinks that when selecting, we should select the one node that has the minimum cost to travel to, from
 * 	the starting node. Since we are going to the node via starting node, of course greedily, we would always want
 * 	the cheapest cost to go to the node.
 * 
 * 	Therefore, while exploration, instead of a queue, we will use heap to arrange the next layer of nodes to explore
 * 	which arranges the cost by increasing order.
 */

public class Dijkstra_Algorithm {
	//Say there are n nodes, numbered from 0 to n-1 inclusive.
	//The lists represent the relationship of the nodes, in array form [from, to, weight]
	//Src is the Source node and Dst is the Destination node
	//We were to find the shortest cost to go from src to dst, and perhaps show the path we've taken
	public static void dijkstra(int n,int[][] lists, int src) {
		
		//Construction of the graphs
		ArrayList<Integer>[] adjacent = new ArrayList[n];
		ArrayList<Integer>[] weights = new ArrayList[n];
		for (int i = 0; i < adjacent.length; i ++ ) {
			adjacent[i] = new ArrayList<>();
			weights[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < lists.length; i ++ ) {
			int from = lists[i][0], to = lists[i][1], weight = lists[i][2];
			adjacent[from].add(to);
			weights[from].add(weight);
			//Add the below two lines to make this a double directed graph
//			adjacent[to].add(from);
//			weights[to].add(weight);
		}
		//End of construction of graph -----------------------------
		
		
		//The heap will store the explored node in the form [node, price, parent], it will be polled and exploration of adjacent nodes will be performed
		
		//Comparator based on the price
		PriorityQueue<int[]> heap = new PriorityQueue<>(2 * n, (x,y) -> {
			return x[1] - y[1];
		});
		//path - Tells us the parent of this node which leads to the shortest / cheapest path
		//minCost - The table to tabulate the DP results. Path means the target node, minCost tabulates the minimum cost to travel there, and
		//parent tabulates where the minimum cost come from (parent node)
		int[] path = new int[n];
		int[] minCost = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		
		//Put in the source node to kick start the algorithm
		heap.add(new int[] {src, 0, Integer.MIN_VALUE} );
		
		while (!heap.isEmpty() ) {
			int[] arr = heap.poll();
			int node = arr[0], price = arr[1], parent = arr[2];
			
			//If the price is found to be cheaper than tabulated one, replace the price and parent
			if (minCost[node] > price) {
				minCost[node] = price;
				path[node] = parent;
			}
			
			List<Integer> explore = adjacent[node];
			List<Integer> prices = weights[node];
			for (int i = 0; i < explore.size(); i ++ ) {
				if (visited[explore.get(i) ]) continue;
				heap.add(new int[] {explore.get(i), price + prices.get(i), node} );
			}
			visited[node] = true;
		}
		
		System.out.println("Node	|MinCost	|Parent		");
		for (int i = 0; i < minCost.length; i ++ ) {
			System.out.printf("%d	|%d		|%d		 \n", i, minCost[i], path[i]);
		}
	}
	
	public static void main(String[]args) {
		int[][] lists = { {0,1,5}, {0,3,2}, {3,1,2}, {1,4,1}, {4,2,1}, {1,2,5} };
		dijkstra(5,lists, 0);
	}
}



