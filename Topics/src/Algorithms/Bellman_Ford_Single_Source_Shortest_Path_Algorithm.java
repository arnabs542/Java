package Algorithms;

import java.util.Arrays;

/*
 * 	Dijkstra's algorithm, which takes approx O(N log N) time, does not work with negative edges!
 * 	Is there an algorithm to overcome this?
 * 
 * 	Introducing Bellman's Ford Algorithm, which is also single sourced, shortest path algorithm, but
 * 	potentially could be very slow.
 * 
 * 	----------------------------------------------------------
 * 
 * 	The idea of the algorithm comes from Dynamic Programming. It goes as follows:
 * 	
 * 	>	We will have a DP table, say d[], which d[i] indicates the shortest distance (so far) to reach
 * 		vertex i from source node.
 * 	>	We will have a list of ALL edges, and we will be iterating over that list of edges over and over
 * 		again, for V - 1 times. V = number of vertices.
 * 	>	In each iteration of edges, the cost to reach from i to j is simply d[i] + c(i,j) where c(i,j) is
 * 		the cost to travel from i to j. 
 * 		We'll be performing RELAXATION if possible. Relaxation simply means update the minimum cost if
 * 		the newly calculated cost is smaller than the recorded value.
 * 
 * 	-------------------------------------------------------------
 * 
 * 	Why the list of edges has to be iterated V - 1 times? See the following case:
 * 
 * 		(1) ------- (2) --------(3) -------(4)
 * 			  4				2			6
 * 
 * 	Say if we only iterate the egdes from backwards, namely (3) to (4), then (2) to (3), then (1) to (2), then
 * 	surely it needs to take 4-1 = 3 iterations to fully connect (1) to (4)!
 * 
 * 	-------------------------------------------------------------
 * 
 * 	The downside of this algorithm is:
 * 		1.	Slow. In case of a graph which has maximum edge connections possible, the number of edges will be square
 * 			of vertcies. E = V^2. Thus O(V^3)
 * 
 * 		2.	Does not work if there is a negative edge cycle. This is due to each iteration, the negative edge will
 * 			take effect and keep relaxing the nodes even when it is not supposed to.
 * 			Don't get it wrong though. Positive edge cycle is perfectly fine as in the next iteration, it wont get
 * 			relaxed as the cost had increased, not decreased.
 * 
 * 			However, this also means detecting a negative edge cycle in a graph is possible in this algorithm. Simply
 * 			run the algorithm for N-1 times, then one extra time and check if any of the nodes are relaxed once more.
 * 	
 */

class Bellman_Ford_Single_Source_Shortest_Path_Algorithm {
	
	//	Edges contain edge which is array in format { from, to, weight }
	public static void bellmanFordShortest(int n, int src, int[][] edges) {
		
		//	There are n edges available, from 0 to n-1
		int[] dp = new int[n];
		int[] parent = new int[n];
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[src] = 0;
		parent[src] = Integer.MAX_VALUE;
		
		//	Loop N-1 times to ensure all paths are minimum
		for (int i = 0; i < n-1; ++i) {
			for (int[] edge: edges) {
				int cost = dp[ edge[0] ] + edge[2];
				
				//	Relaxation
				if (dp[edge[1]] > cost) {
					dp[edge[1]] = cost;
					parent[edge[1]] = edge[0];
				}
			}
		}
		
		//	Print Result
		System.out.println("Node\t\tCost\t\tParent");
		for (int i = 0; i < n; ++i)
			System.out.println(i + "\t\t" + dp[i] + "\t\t" + parent[i] );
		
	}
	
	
	
	
	public static void main(String[]args) {
		int N = 5;
		int[][] lists = { {0,1,5}, {0,3,2}, {3,1,2}, {1,4,1}, {4,2,1}, {1,2,5} };
		
		bellmanFordShortest(N, 0, lists);
	}

}
