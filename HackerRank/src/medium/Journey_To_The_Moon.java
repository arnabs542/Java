package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://www.hackerrank.com/challenges/journey-to-the-moon/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

/*
 * 	This can be thought as a graph problem (Which can further be thinked of as a DISJOINT SET PROBLEM).
 * 
 * 	Those pairs of astronaut from same country are actually edges of the graph.
 * 	
 * 	After forming the graph according to the edges, it will probably form several clusters of graph (We can think it as disjoint sets).
 * 
 * 	For Combinatorics,
 * 	So, If we have N astronauts before, and now we include a new country with has M astronauts, the total number of ways to form
 * 	pairs of astronauts would increase by N * M possible combinations.
 * 
 * 	Therefore when explored a new country (new disjoint set cluster), we need the information:
 * 		>	How many astronauts representing this new country
 * 		>	How many total astronauts from other country prior to when the current new country was introduced
 * 
 * 	For each new country, we will add N * M into result, then add M into the cumulative variable, counting the total no of 
 * 	astronauts
 * 
 * 	----------------------------------------------------------------------------------------
 */

public class Journey_To_The_Moon {
	
	static long journeyToMoon(int n, int[][] astronaut) {
		Map<Integer, List<Integer> > graph = new HashMap<>();
		
		for (int[] pairs: astronaut) {
			graph.putIfAbsent( pairs[0], new LinkedList<>() );
			graph.putIfAbsent( pairs[1], new LinkedList<>() );
			graph.get( pairs[0] ).add( pairs[1] );
			graph.get( pairs[1] ).add( pairs[0] );
		}
		
		Set<Integer> visited = new HashSet<>();
		
		long res = 0;
		long cum = 0;
		for (int i = 0; i < n; i ++ ) {
			if ( visited.contains(i) ) continue;
			
			int nCluster = countNodes(i, graph, visited);
			res += cum * 1l * nCluster;
			cum += nCluster;
		}
		
		return res;
		
    }
	
	private static int countNodes (int node, Map<Integer, List<Integer>> graph, Set<Integer> visited ) {
		if (visited.contains(node) ) return 0;
		
		visited.add(node);

		if ( graph.get(node) == null) return 1;
		
		int count = 1;
		for (int i: graph.get(node) ) {
			count += countNodes( i, graph, visited);
		}
		return count;
	}
	
}
