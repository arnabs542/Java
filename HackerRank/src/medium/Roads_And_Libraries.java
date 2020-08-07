package medium;

//https://www.hackerrank.com/challenges/torque-and-development/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaigns

/*
 * 	This is a Graph Theory Problem.
 * 
 * 	First, all the graph are clustered, and may be seperated into different groups (Not conencted by any roads).
 * 	Since this graph is not weighted, each cluster may only need 1 library, and the rest will just connect to the city by road.
 * 	That is sufficient enough to achieve lowest cost
 * 
 * 	Notice if the cost of building a library is cheaper or equal to cost of deblocking the road, then it's much better to just
 * 	build a library in every city.	(Special case)
 * 
 * 	Now, traverse through each node. If the node is found to not yet connected to any library (By recording a visited HashSet,
 * 	or do some trick on the graph -> empty the graph of the current node after exploring it)
 * 	Then we will be building 1 library on this city, and for each edges connected to this city, retrieve the road until the
 * 	whole cluster of node has been visited once.
 * 
 * 	The main iteration will be traversing each city numbered from 1 to n. If it is found that city i does not yet connect to
 * 	a library, then call upon the recursive function.
 * 	The function of the recursion is to calculate the cost of retrieving roads. Be careful to avoid infinite loops because
 * 	the graph is bidirectional.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Roads_And_Libraries {
	
	static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
		//We need to take care of long overflow issue. Whenever there is int, make it long
		if (c_lib <= c_road ) return n * 1l * c_lib;
		
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 1; i <= n; i ++ ) {
			map.put(i, new LinkedList<>() );
		}
		for (int[] edge: cities ) {
			map.get( edge[0]).add( edge[1] );
			map.get( edge[1]).add( edge[0] );
		}
		
		long totalCost = 0;
		
		
		for (int i = 1; i <= n; i ++ ) {
			if ( map.get(i) != null )
				//Notice we don't need to build road on the city of library itself, so we minus the cost of road
				totalCost += c_lib * 1l + buildRoad(i ,map, c_road, new HashSet<>() ) - c_road * 1l;
		}
		
		return totalCost;

    }
	
	static long buildRoad(int node, Map<Integer, List<Integer>> graph, int croad, Set<Integer> visited) {
		if (visited.contains(node) ) return 0;
		
		long res = croad;
		visited.add(node);
		List<Integer> paths = graph.get(node);
		
		for (int i: paths) {
			res += buildRoad(i, graph, croad, visited);
		}
		
		//This city is fully explored. Therefore remove from graph 
		graph.remove(node);
		
		return res;
	}
}
