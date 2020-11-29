package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/jump-game-iii/
/*
 *	This is a graph DFS/BFS problem with possible recursion implementation
 *
 *	From a node with a specific value, I can jump to the node of index i - val or i + val. This is the graph edges
 *	connecting from this current node to the node i-val and i+val.
 *
 *	Therefore we have to explore the graph and see if we ever encounter the node 0 or not. If yes, then immediately we can
 *	return true.
 *
 *	Do remember that cycle can possibly exist in the graph. Therefore we need a way to record if we had already visited the
 *	graph or not to prevent revisiting a node that is currently already in the midst of visited, and therefore a node
 *	should never be visited twice
 *
 *	Worst case complexity is always O(N) for both space and time. Imagine [1,1,1,1,1,1,1,1,1,1,0] and starting at index 0.
 *	Every node has to be explored and recorded.
 */


public class Jump_Game_III {
	
	//	Recursion DFS method
	public boolean canReach(int[] arr, int start) {
		boolean[] visited = new boolean[arr.length];
		return recurse(arr, start, visited );
	}
	private boolean recurse(int[] arr, int idx, boolean[] visited) {
		if (idx < 0 || idx >= arr.length || visited[idx] ) return false;		//	Out of bound or visited
		if (arr[idx] == 0) return true;		//	Found the index 0 node
		
		visited[idx] = true;	//	Mark as visited
		
		//	Recurse on left and right
		return recurse(arr, idx-arr[idx], visited) || recurse(arr, idx+arr[idx], visited);
	}
	
	
	//	Iterative Stack Method (DFS)
	public boolean canReach2(int[] arr, int start) {
		final int len = arr.length;
		Deque<Integer> deque = new ArrayDeque<>();
		deque.push(start);
		
		boolean[] visited = new boolean[ len ];
		
		while ( !deque.isEmpty() ) {
			int node = deque.pop();
			int val = arr[node];
			
			if (val == 0) return true;
			visited[node] = true;
			
			if (node - val >= 0 && !visited[node-val] ) deque.push(node-val);
			if (node + val < len && !visited[node+val] ) deque.push(node+val);
		}
		return false;
	}
	
}
