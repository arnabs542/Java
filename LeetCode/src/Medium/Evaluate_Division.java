package Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/evaluate-division/

/*
 * 	This is a Graph / DFS problem.
 * 
 * 	Essentially, the divisions can actually be represented as graph connections. The operands are the nodes, and the result are
 * 	the weight
 * 
 * 	Say A/B = 2, then in graph:
 * 
 * 		   2
 * 	(A) ------> (B)
 * 		<------
 * 		   1/2
 * 
 * 	See it forms a directed graph, but connections must go both way. Essentially from parent node to the next node, the weight is esentially
 * 	meaning: 		PARENT / DESTINATION
 * 
 * 	Now, after graph is formed, we will see in the query. Essentially we are finding a path from the numerator to the denominator. If
 * 	we can find it, then we will know the result. Otherwise, it is impossible and we will return -1
 * 	This is done by DFS + backtracking (Visited set backtracking)
 * 	
 * 	====================================================================================================================================
 * 
 *	It is also possible to solve this via Disjoint sets. The disjoint set tree must have a weight associated with it as well to represent
 *	the result of one node to another... 	
 * 
 * 
 * 
 */

public class Evaluate_Division {
	
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		int q = queries.size();
		int e = equations.size();
		
        Map<String, Map<String, Double> > graph = new HashMap<>();
        
        //	Setting up of the graph for DFS
        for (int i = 0; i < e; i ++ ) {
        	List<String> eq = equations.get(i);
        	double val = values[i];
        	
        	graph.putIfAbsent( eq.get(0) , new HashMap<>() );
        	graph.putIfAbsent( eq.get(1) , new HashMap<>() );
        	
        	graph.get( eq.get(0) ).put( eq.get(1) , val );
        	graph.get( eq.get(1) ).put( eq.get(0), 1.0 / val );
        }
        
        double[] res = new double[q];
        
        for (int i = 0; i < q; i ++ ) {
        	List<String> query = queries.get(i);
        	double result = -1;
        	
        	if (graph.containsKey(query.get(0) ) && graph.containsKey(query.get(1) ) ) {
        		result = dfs(graph, query, new HashSet<>(), query.get(0), 1);
        	}
        	
        	res[i] = result;
        }
        
        return res;
    }
	
	
	private static double dfs(Map<String, Map<String,Double>> graph, List<String> query, Set<String> visited, String curr, double val) {
		if (curr.equals( query.get(1) ) ) return val;
		
		Map<String, Double> adjacents = graph.get( curr );
		visited.add( curr );
		
		for (String key: adjacents.keySet() ) {
			if ( !visited.contains(key) ) {
				double res = dfs(graph, query, visited, key, val * adjacents.get(key) );
				if (res != -1 ) return res;
			}
		}
		
		visited.remove( curr );
		return -1;
	}
}
