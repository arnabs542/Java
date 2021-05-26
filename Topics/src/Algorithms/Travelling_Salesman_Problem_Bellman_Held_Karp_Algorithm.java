package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//	Source of Reference:
//	http://www.math.nagoya-u.ac.jp/~richard/teaching/s2020/Quang1.pdf
//
/*
 * 	The travelling salesman problem (TSP), which can be solved via Bellman Held Karp Algorithm (DP), can be considered
 *  a graph theory problem.
 * 	
 * 	The problem states that given a salesman and a list of cities as well as distances to travel between cities,
 * 	find out the minimum distance that the salesman has to travel to visit each city exactly ONCE, and in case of cycle,
 * 	TRAVELS BACK TO THE STARTING CITY
 * 
 * 	In other words, given a COMPLETE graph (each node is connected to each other), find out the minimum cost
 * 	HAMILTONIAN PATH / CYCLE
 * 
 * 	This problem can be thought in delivery courier services, like package delivery man. What is minimum distance to travel
 * 	so we can minimize the fuel cost?
 * 
 * 	==================================================
 * 
 * 	First, let's grasp the idea of bitmask first. How do we represent whether certain nodes are already explored or not?
 * 	Say each node is numbered from 0, 1... N. 
 * 	To represent that a city has been explored, one can simply use a boolean array of size N isVisited to represent state.
 * 	However, since our N is small, maybe 32 bit of 1 and 0 is sufficient?
 * 
 * 	That is the concept of using bitmask to represent states. See:
 * 		00.....01
 * 	may indicate that node 0 is visited.
 * 
 * 		00...0101
 * 	may indicate that node 2 and node 0 is visited.
 * 
 * 	This involves bit manipulation. To switch on and off a bit, use XOR with 1. To get to certain bit, use left shift <<.
 * 
 *	This method simply shows the possibility that bits can be used to represent the node states. It does not mean strictly
 *	that 1 is visited and 0 is havent visited. It can be other way around, as we will see.
 * 
 * 	=======================================================
 * 
 * 	Another idea to acknowledge is: If we are finding shortest Hamiltonian Cycle, it doesn't matter which vectice we pick
 * 	as the starting vertice, because say the shortest Hamiltonian cycle is:
 * 		A > B > C > D > E > A,
 * 
 * 	If we pick D as starting vertex, it will also find its way:
 * 		D > E > A > B > C > D
 * 
 *  ========================================================
 *  	BRUTE FORCE ALGORITHM
 *  ========================================================
 * 
 * 	The brute force solution involves, of course, trying out all possible routes. 
 * 	Say we have 4 cities. First we pick 1 of 4 cities to visit, then 1 out of 3, then 1 out of 2 and finally the last one.
 * 	Therefore the time complexity is 4x3x2x1 = O(4!).
 * 
 * 	Brute force involves generating all permutations of cities, then evaluating the cost for each permutation, and simply
 * 	determine the lowest cost. As simple as that. You might implement it as DFS + backtracking.
 * 	
 * 	The brute force solution uses time of O(N!), which as N grows, it becomes almost impossible to compute in a reasonably
 * 	amount of time.
 * 	
 * 	=====================================================
 * 		BELLMAN HELD KARP ALGORITHM DYNAMIC PROGRAMMING	
 * 	=====================================================
 * 	
 * 	The idea of this DP algorithm reduces time complexity from O(N!) to O(N^2 * 2^N). Although it is not reduced to polynomial
 * 	time, the efficiency is far, far greater already.
 * 
 * 	DP algorithm think of a solution of a problem based on its sub-problems. In this case, if we have total of 4 nodes, the
 * 	subproblem would be to have 3 nodes, 2 nodes or even 1 node only.
 * 
 * 	Say we have node A, B and C to consider visiting. Out of these 3 nodes, we can try to pick out 1 node to visit last.
 * 	If I pick Node C, that means we are ought to find the optimal cost to travel starting from A (starting node), go thru 
 * 	some intermediate nodes, then finally reached node C as last.
 * 	Say we pick out node C to visit last, then, we are left with nodes A and B as subproblem. From A and B, pick one node
 * 	as the last visited node before C, then use the DP results to obtain the optimal cost!
 * 
 * 	Therefore, the base case is to have available nodes to visit of size 1. Say we have state of only node B, we directly
 * 	assign the DP result to be the cost to travel from starting point (A) to node B.
 * 
 * 	At the end of DP, the result would be in DP array where the state covers all nodes. Specifically, since we want that at
 * 	the end, we go back to node A (starting node), we would refer to DP array where state covers all nodes, and last visited
 * 	node is also Node A!
 */



//=====================================================
//	Travelling Salesman Brute Force DFS + Backtracking
//	
//	This solution assets that starting vertex is always vertex 0!
//=====================================================
class TSP_Brute_Force {
	private List<Integer> optimalPath;
	private int minCost = Integer.MAX_VALUE;
	private int[][] distanceMatrix;
	final private int N;					//	Number of nodes
	final private int MAX_STATE;			//	The state at which all nodes are visited. Eg: N=3, MAX_STATE = 0000..00111
	
	public TSP_Brute_Force(int[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
		this.N = distanceMatrix.length;
		this.MAX_STATE = (1 << N) - 1;		//	Say N=3, we get 1000, then decrement to toggle the rest to bit 1.

		recurse(1, 0, new ArrayList<>(), 0,0);	//	Start out with vertex 0 already visited (Starting vertex)
		
		System.out.println("Optimal cost: " + minCost);
		System.out.println("Optimal path: " + optimalPath);
	}
	
	
	//	Backtracking
	private void recurse(int state, int cost, List<Integer> path, int from, int curr) {
		//	Base case. All nodes are visited. Record if the cost are actually lower than existing one
		if (state == MAX_STATE) {
			if (cost + distanceMatrix[curr][0] < minCost) {
				minCost = cost + distanceMatrix[curr][0];
				optimalPath = new ArrayList<>();
				optimalPath.add(0);
				optimalPath.addAll(path);
				optimalPath.add(0);
			}
			return;
		}
		//	Otherwise, try visit some nodes
		for (int i = 0; i < N; ++i) {
			int mask = 1 << i;
			if ( (state & mask) != 0 ) continue;	//	Node is visited. Attempt next node
			
			//	Otherwise, attempt to visit the node.
			path.add(i);
			recurse( state | mask, cost + distanceMatrix[curr][i], path, curr, i );
			path.remove( path.size() - 1 );
		}
	}
}





//=============================================
//	Bellman-Held-Karp DP solution for TSP
//=============================================
class TSP_DP {
	private int[][] distanceMatrix;
	private int[][] dp;					//	dp[i][j] means at state i, the minimum cost to arrive until latest is node j
	private int[][] path;				//	path[i][j] means at state i, taking what next node, leads to local optimal solution
	final int N;
	final int MAX_STATE;
	private int minCost = Integer.MAX_VALUE;
	
	TSP_DP(int[][] distanceMatrix) {
		//	Preparation
		N = distanceMatrix.length;
		MAX_STATE = (1 << N) - 1;

		this.distanceMatrix = distanceMatrix;
		this.dp = new int[1 << N][N];
		this.path = new int[1 << N][N];
		
		
		//	Bellman Held Karp Algorithm
		//	Generate every state. Takes 2^N time
		for (int state = 1; state <= MAX_STATE; ++state) {
			Arrays.fill(dp[state], Integer.MAX_VALUE);
			
			//	Try to pick one node to visit as the last node to be visited from the given state
			for (int last = 0; last < N; ++last) {
				if ( (state & (1 << last) ) == 0 ) continue;
				int prevstate = state - (1 << last);
				
				//	If prevstate is 0 (Means the state has only 1 node), dp is the cost from start node to the only choosen node
				if (prevstate == 0)
					dp[state][last] = distanceMatrix[0][last];
				else {
					//	From the previous states, pick any node as second last visited. Then compute the cost
					for (int secondlast = 0; secondlast < N; ++secondlast) {
						if ( (prevstate & (1 << secondlast)) == 0) continue;
						if ( dp[prevstate][secondlast] != Integer.MAX_VALUE && 
								dp[prevstate][secondlast] + distanceMatrix[secondlast][last] < dp[state][last]) {
							dp[state][last] = dp[prevstate][secondlast] + distanceMatrix[secondlast][last];
							path[state][last] = secondlast;
						}
					}
				}
			}
		}
		

		
		//	Track the path
		Stack<Integer> stk = new Stack<>();
		int lastNode = 0;
		for (int st = MAX_STATE; st != 0;) {
			int state = st;
			stk.push(lastNode); 
			st -= (1 << lastNode);
			lastNode = path[state][lastNode];
		}
		
		System.out.println("Min Cost: " + dp[MAX_STATE][0]);
		System.out.print("Path: 0 ");
		while (!stk.isEmpty() )
			System.out.print(stk.pop() + " ");
		System.out.println();
	}
}











public class Travelling_Salesman_Problem_Bellman_Held_Karp_Algorithm {
	public static void main(String[] args) {
		
	    int graph[][] = { { 0, 10, 15, 20 },
		                { 10, 0, 35, 25 },
		                { 15, 35, 0, 30 },
		                { 20, 25, 30, 0 } };
	    int test[][] = {{0, 5, 12},
			    		{10, 0, 7},
			    		{8, 1, 0}};
	    
	    TSP_DP tspdp = new TSP_DP(graph);
	    TSP_Brute_Force tspbf = new TSP_Brute_Force(graph);
	    
	}
}
