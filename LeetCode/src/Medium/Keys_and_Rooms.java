package Medium;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/keys-and-rooms/
/*
 * 	This is a simple Graph DFS/ BFS problem.
 * 
 * 	For all visited rooms, simply record in visited Set. Remember not to visit any previously visited room!
 * 	At the end, simply check if Set length == total no of rooms
 */

public class Keys_and_Rooms {
	
	//	BFS
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		final int len = rooms.size();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        
        visited.add(0);
        queue.offer(0);
        
        while (!queue.isEmpty() ) {
        	List<Integer> keys = rooms.get( queue.poll() );
        	for (int k: keys) {
        		if (!visited.contains(k) ) queue.offer(k);
        		visited.add(k);
        	}
        }
        
        return visited.size() == len;
    }
	
	//	DFS
	public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
		final int len = rooms.size();
		Set<Integer> visited = new HashSet<>();
		
		recurse(rooms, 0, visited);
		return visited.size() == len;
	}
	private void recurse(List<List<Integer>> rooms, int curr, Set<Integer> visited) {
		visited.add(curr);
		for (int k: rooms.get(curr) ) {
			if (!visited.contains(k) ) recurse(rooms, k, visited);
		}
	}
}
