package Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.com/problems/possible-bipartition/

/*
 * 	This is a graph theory, BIPARPITE GRAPH problem with DFS/BFS solving algorithm. The disliking relationship of the peoples can be 
 * 	represented in a graph, and the graph's pointer will be double sided (double arrow). Notice that when the graph is drawn, when we jump
 * 	from a node to another, it switches team.
 * 	Eg:				O <--------> O <---------> O <-------------> O
 * 				Group 1		Group 2			Group 1			Group 2
 * 
 * 	It works as follows: For each person we would color it in RED, and the people that the person dislikes, will be colored in BLUE, which
 * 	depicts 2 opposite teams.
 * 	Initially, the graph will be uncolored. Therefore we implement a hash map that points the person's number (KEY) to its color (MAP)
 *  
 *  We do this using DFS/BFS such that after coloring the current person, we would want to try and color each people that person dislikes, in
 *  opposite color. If a particular person is already colored (exists in map already), we would need to check if the person matches the supposed
 *  color that it should have. If there is a conflict (Eg: We want to color this person BLUE but it is already colored RED), 
 *  that's a sign that it is impossible to contain them all in just 2 teams.
 *  
 *  We don't have to care about if one particular disliked person is the source/parent (where the source of the search) because it would have matched
 *  the supposed color to paint
 *  
 *  
 *  
 */

public class Possible_Bipartition {
	

	HashMap<Integer, Boolean> color = new HashMap<>();
	
	public boolean possibleBipartition (int N, int[][] dislikes) {
		HashMap <Integer, LinkedList<Integer> > dislikeMap = new HashMap<>();
		HashSet <Integer> toVisit = new HashSet<>();
		for (int i = 1; i <= N; i ++ ) {
			dislikeMap.put(i, new LinkedList<Integer>() );
		}
		for (int[] d: dislikes) {
			dislikeMap.get(d[0] ).add(d[1] );
			dislikeMap.get(d[1] ).add(d[0] );
			toVisit.add(d[0] );
		}
		
		for (int i = 1; i <= N; i ++ ) {
			//Do the DFS only if it should be visited aka hates someone (has valid connection)
			if (toVisit.contains(i) ) {
				if ( !dfs(dislikeMap, toVisit, i, true ) ) return false;
			}
		}
		return true;
	}

	public boolean dfs ( HashMap<Integer,LinkedList<Integer>> map, HashSet<Integer> set, int toSearch, boolean paintRed) {
		if (color.containsKey(toSearch) ) {
			return color.get(toSearch) == paintRed;
		}
		color.put(toSearch, paintRed);
		set.remove(toSearch);
		
		for (int link: map.get(toSearch) ) {
			if (!dfs(map, set, link, !paintRed) ) return false;
		}
		return true;
	}
	
}
