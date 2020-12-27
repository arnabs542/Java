package Hard;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/jump-game-iv/
/*
 *  This is a Graph Traversal - BFS problem.
 *  
 *  We want to find the shortest distance from the starting node to the end node, with the steps of either:
 *  		>	Go to next node
 *  		>	Go to previous node
 *  		>	Go to node with same value
 *  
 *  Therefore we need to Record all the nodes with the same value, by the index in the array. We can use a HashMap
 *  for this
 *  
 *  We can choose to record the shortest distance needed to reach a particular node, using a HashMap as well.
 *  
 *  On each Node, we have to record the distances to
 *  	-	Nodes of same values
 *  	-	Next node and Previous node
 *  
 *  Whenever we detected we had reached the end node, immediately return the distance to it.
 *  
 *  -----------------------
 *  
 *  However, consider case 1,7,7,7,7,7,7,7,7,7,7,7,77,7,7,7...2. There are many 7 in between. In the worst case, we might
 *  have to go through all the 7's!. On each node 7, we have to go through all the indices of nodes with value 7, causing
 *  O(N^2) time! We must need to optimize it!
 *  
 *  ----------------------------------------------------------------------------------------------------
 *  
 *  One little optimization is, through observation, we see that actually for a series of nodes with same value, like
 *  7,7,7,7, only the first one and last one is important! Therefore, we might just ignore the intemeidate nodes when
 *  constructing the graph. This solves the earlier problem where the nodes are bunched together, but now consider
 *  another case:
 *  
 *  		1,2,7,2,7,2,7,2,7... 3
 *  
 *  Since they are seperated, solution above won't work! Is there a better way?
 *  
 *  ----------------------------------------------------------------------------------------------------
 *  
 *  The key is, since we are using BFS, when we had traversed to a node for first time, we must be having the shortest
 *  distance so far. Therefore, The other nodes having the same value, we will just write to them ONCE, that is during
 *  we first explored the node of this value. After that, we immediately delete the record of nodes with same value.
 *  Therefore the subsequent times we met the node, we won't be checking each one again, causing O(N^2) time!
 *  
 *  -----------------------------------------------------------------------------------------------------
 *  
 *  Can we optimize space? Instead of using HashMap which records which node has which shortest distance from origin,
 *  remember thta we are using BFS, which we know the distance is just the 'layer' of BFS! Just keep a variable that
 *  keep tracks of the layers we have been through, and that is our distance!
 */

public class Jump_Game_IV {
	
	public int minJumps(int[] arr) {
		final int len = arr.length;
		
		//	Construct of the graph which maps a value to indices of array which share the same value
		//	Explicitly exclude the starting node. We don't want to come back to starting node
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int i = 0; i < len; ++i) {
			
			//	Optimization Technique - 
			//  Say we have 3,3,3,3. The middle two 3's actually don't make a difference. So we skip them
			if ( (i-1 >= 0 && arr[i-1] == arr[i]) && (i+1 < len && arr[i+1] == arr[i]) ) continue;
			
			graph.putIfAbsent( arr[i] , new HashSet<>() );
			graph.get( arr[i] ).add(i);
		}
		
		//	Construction of the state recording HashMap - Index -> distance
		//	Preferred to use HashMap because not all node necessary is explored in BFS or DFS
		Map<Integer, Integer> dist = new HashMap<>();
		dist.put(0, 0);
		
		//	Queue of the index to be explored
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		
		//	Exploration BFS begins
		while (!queue.isEmpty() ) {
			int idx = queue.poll();
			int dis = dist.get(idx);
			
			//	Reached the end index. Since is BFS it shall be shortest path
			if (idx == len - 1) return dis;
			
			for (int shared: graph.get(arr[idx]) ) {
				//	Only write when the shared node is never touched before
				if (!dist.containsKey(shared) ) {
					dist.put( shared, dis + 1);
					queue.add( shared );			// Push to queue to be explored later
				}
			}
			
			//	Left side
			if ( idx - 1 > 0 && !dist.containsKey(idx-1) ) {
				dist.put( idx - 1, dis + 1);
				queue.add( idx - 1 );
			}
			
			// Right side
			if (idx + 1 < len && !dist.containsKey(idx+1) ) {
				dist.put(idx + 1, dis + 1);
				queue.add( idx + 1);
			}
		}
		return -1;
	}
	
	
	
	
	
	//	Since we are BFS, we guarantee that when we retrieved the node, it will be at minimum distance from start
	//	Therefore for nodes with same value, after assigning each one of them a value, immediately delete them
	//	since the job is done: We assigned them with minimum distance already!
	//	Also, we don't have to make a map from index to distance. Using BFS property of counting layer (Like counting
	//	depth of BT), we can just use a visited Set
	public int minJumps2(int[] arr) {
		final int len = arr.length;
		
		//	Construct of the graph which maps a value to indices of array which share the same value
		//	Explicitly exclude the starting node. We don't want to come back to starting node
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int i = 0; i < len; ++i) {
			graph.putIfAbsent( arr[i] , new HashSet<>() );
			graph.get( arr[i] ).add(i);
		}
		
		Set<Integer> visited = new HashSet<>();
        visited.add(0);
		
		//	Queue of the indices to be explored in BFS
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		
		int layerDepth = 0;
		
		//	Exploration BFS begins
		while (!queue.isEmpty() ) {
			int f = queue.size();
			
			for (int i = 0; i < f; ++i) {
				int idx = queue.poll();
				
				//	Reached the end index. Since is BFS it shall be shortest path
				if (idx == len - 1) return layerDepth;
				
				if ( graph.containsKey( arr[idx] ) ) {
					
					for (int shared: graph.get(arr[idx]) ) {
						if ( visited.add(shared) ) queue.add( shared );			
					}
					//	Remove so we don't have to iterate once more!
					graph.remove( arr[idx] );
				}
				
				
				//	Left side
				if ( idx - 1 > 0 && visited.add(idx-1) ) queue.add( idx-1 );
				
				// Right side
				if (idx + 1 < len && visited.add(idx+1) ) queue.add( idx+1 );
			}
            ++layerDepth;
		}
		return -1;
	}
	
}
