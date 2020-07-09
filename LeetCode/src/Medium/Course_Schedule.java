package Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

//https://leetcode.com/problems/course-schedule/

/*
 * 	The question, when represented on a graph, tell us to see if we can find a cycle in the graph. This is a DFS / BFS question
 * 
 */

public class Course_Schedule {
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer> > courses = new ArrayList<>(numCourses);
        Collections.fill(courses, new ArrayList<Integer>() );
        HashSet<Integer> toVisit = new HashSet<>();
        
        for (int[] requisites : prerequisites) {
        	courses.get(requisites[0] ).add( requisites[1]);
        	toVisit.add(requisites[0] );
        }
        
        for (int i = 0; i < numCourses; i ++ ) {
        	if (toVisit.contains(i) ) {
        		if ( !search(i, courses, new boolean[numCourses], toVisit ) ) return false;
        	}
        }
        return true;
    }
	
	public boolean search(int toSearch, ArrayList<ArrayList<Integer>> graph, boolean[] visited, HashSet<Integer> toVisit) {
		if (visited[toSearch] ) return false;
		
		visited[toSearch] = true;
		toVisit.remove(toSearch);
		for (int i: graph.get(toSearch) ) {
			if (!search(i, graph, visited, toVisit) ) return false;
		}
		visited[toSearch] = false;
		return true;
	}
}
