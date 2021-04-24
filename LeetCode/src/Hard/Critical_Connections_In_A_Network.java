package Hard;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/critical-connections-in-a-network/
/*
 * 	This is a Graph - Bridge Finding Problem. The core algorithm required is Tarjan's SCC Algorithm.
 * 	SCC stands for Strongly Connected Components.
 * 
 * 	First, construct the graph as it is. Now, since the nodes are all assured to be connected, we can
 *  randomly select one node and go perform dfs in it.
 *  
 *  Now, we would want to record the 'time' of each recursion call. Time essentially will increment by 1
 *  in each layer of recursion, and no node shall have same time value. So, first node to do dfs will have
 * 	time of 0 (or 1), then 1, 2... and so on.
 * 
 * 	The idea is, first realize that critical connections are essentially bridges in graph terms. It is the edge
 * 	that connect two separate, independent CYCLES.
 * 	In other words, if an edge is present/ participated in forming a cycle in graph, then it will not be
 * 	crucial connection.
 * 
 *  So how do we find out such cycle? The idea is, when dfs, we mark each node by the 'time' mentioned earlier
 *  in incrementing manner.
 *  If suddenly in dfs, we hit a node where 'time' value is less than parent node, then we know, the node is
 *  visited earlier and a cycle is present!
 *  
 *  Now the node knows that it forms a cycle and thus the edge cannot be crucial connection. How can it inform
 *	the upper recursion calls that this path couldn't be a crucial connection?
 *	
 *	Here goes the idea of low-link values. Each node, apart from 'time' value, also need to have low-link values.
 *	Low-link value of a node, is the lowest 'time' available of node in reach, if I started traversal from that node.
 *	If node A of time 8, is able to reach node B of time '1', then the low-link value of A is 1.
 *
 *	Therefore the recursion call will return the low-link value obtained from the node. Thus passing back to upper calls,
 *	the ancestors will get to know if they traversed the child node, what low-link value can it possibly reach.
 *	The low-link values of each node shall be initialized to their 'time 'value
 *
 *	Thus, if on the parent node during recursing child nodes, if they get the return value (low-link value of child) is
 *	lesser or equal to its own 'time' value, then it means a cycle is formed! The edge from current node to that child
 *	cannot be crucial connection.
 *	Reversing the logic, if the return value is greater than parent itself (Should be time+1), then no cycle is formed
 *	from parent to child, so IT IS crucial connection
 */

public class Critical_Connections_In_A_Network {
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		//	Construction of the graph
		List<Integer>[] graph = new List[n];
		for (List<Integer> edge: connections) {
			int u = edge.get(0), v = edge.get(1);
			if (graph[u] == null) graph[u] = new LinkedList<>();
			if (graph[v] == null) graph[v] = new LinkedList<>();
			graph[u].add(v);
			graph[v].add(u);
		}
	
		int[] lowlink = new int[n];
		Arrays.fill(lowlink, -1);
		List<List<Integer>> res = new LinkedList<>();
		
		recurse(0, lowlink, 1, graph, res);
		return res;
    }
	
	
	private static int recurse(int node, int[] lowlink, int time, List<Integer>[] graph, List<List<Integer>> res) {
		if (lowlink[node] != -1) return lowlink[node];
		
		lowlink[node] = time;
		
		int dfs_min = time;
		for (int adj: graph[node] ) {
			if (lowlink[adj] == time - 1) continue;	//	Is parent.
			int dfs = recurse(adj, lowlink, time+1, graph, res);
			if (dfs > time)
				res.add( Arrays.asList(node, adj) );
			dfs_min = Math.min(dfs_min, dfs);
		}
		
		lowlink[node] = dfs_min;
		return dfs_min;
	}
}
