package Medium;

//https://leetcode.com/explore/challenge/card/june-leetcoding-challenge/540/week-2-june-8th-june-14th/3360/

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
//https://leetcode.com/problems/cheapest-flights-within-k-stops/

/*
 * 	This problem is a DP graph problem which utilizes Dijkstra's Algorithm to find the least costly path to reach destination from single source.
 * 	The extra here is with the addition of the limitation of the stops to be made
 */

public class Cheapest_K_Flights_Within_K_Stops {
	
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		HashMap<Integer, List<Integer> > direct = new HashMap<>();
		HashMap<Integer, List<Integer> > prices = new HashMap<>();
		PriorityQueue<int[]> queue = new PriorityQueue<>(2*n, (x, y) -> {
			return x[1] - y[1];
		});
		
		//Construction of the graph //
		for (int[] connect: flights) {
			direct.putIfAbsent(connect[0] , new ArrayList<>() );
			prices.putIfAbsent(connect[0] , new ArrayList<>() );
			direct.get(connect[0] ).add(connect[1] );
			prices.get(connect[0] ).add(connect[2] );
		}
		//Initialization. Put in the source node for it to be first searched//
		//The heap stores size 3 arrays in the following order: [node, price, K steps left]
		queue.offer(new int[] {src, 0, K} );
		
		//The Breadth First search here, using heaps (PriorityQueue)
		while (!queue.isEmpty() ) {
			int[] poll = queue.poll();
			//If the polled node is the destination, just return the price to reach here
			if (poll[0] == dst) return poll[1];
			//If the polled node is already out of steps, we cannot do anything, so just ignore it
			if (poll[2] < 0 ) continue;
			
			//Until here, we have to explore the adjacent nodes associated with this polled node
			List<Integer> flight = direct.get(poll[0]);
			List<Integer> fee = prices.get(poll[0]);
			//If this node has no adjacent nodes, just ignore it. It can't lead us to destination!
			if (flight == null) continue;
			
			//Loop through all of its adjacent nodes to the queue to be explored
			for (int i = 0; i < flight.size(); i ++ ) {
				queue.offer(new int[] {flight.get(i), poll[1] + fee.get(i), poll[2] - 1} );
			}
		}
		//The destination is never met. Therefore we have to return -1.
		return -1;
	}
}
