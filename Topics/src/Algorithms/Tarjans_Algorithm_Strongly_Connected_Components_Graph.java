package Algorithms;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//	Materials:
//https://www.youtube.com/watch?v=aZXi1unBdJA&ab_channel=WilliamFiset
//	Problems:
//https://leetcode.com/problems/critical-connections-in-a-network/

/*
 * 	In graph theory, a bridge is essentially an edge, that when removed, the number of components increase.
 * 	In easier terms, when you remove that edge, the graph will essentially split into 2 subgraph.
 * 
 * 	Similar idea is articulation point. It is a node, which when removed, the graph will also split into 2 subgraphs.
 * 
 * 	You will observe that, the graph may have several cycles, like so:
 * 			()        ()
 *         /  \      /  \
 * 		  ()  ()----()  ()
 *		   \  /      \  /
 * 			()        ()
 * 	The left side and right side is 2 cycles. Now, see that for the bridge, it is always the edge that don't get involved
 * 	in cycles. In the example, it is the center one.
 * 	
 * 	So, how do we able to identify bridges and articulation points in graph? That's what Tarjan's algorithm is used for.
 * 	(There is another similar algorithm called Kosaraju's Algorithm) 
 * 
 * 	The idea is simple to explain: Perform dfs on a random node. If you suddenly dfs to an earlier visited node, you've
 * 	detected the cycle. That means, every edge from that earlier collided node until current node, is NOT bridges.
 * 	Vice versa, if the dfs proceeds and returns without reporting colliding into earlier node, then it means there is no
 * 	alternate path. Thus the edge from the node to dfs'd child, is bridge.
 * 
 * 	
 * 	The algorithm introduces the concept of 'time', which is nothing but incrementing value only. The first node to get dfs
 * 	will be timed '0', the second one will get '1', and so on...
 * 	The time allows us to compare whether the node is visited earlier from parent node. Eg: If I am on node with time 8,
 * 	and suddenly when dfs its child, I am met with node time 1, that means THERE IS A CYCLE FROM NODE TIME 1 TO 8! No
 * 	bridges can be formed.
 * 
 * 	However, that fact is only known to current recursion call. How do we propagate that information back to node
 *  of time 7,6,5... until 1? Introducing concept of low-link value.
 *  Low-link value of a node records the lowest 'time' node that it can reach if started traversal from that node.
 *  Say I have node of time 8. If after 3 children jumps I am met with node of time 1, then the low-link value of
 *  time 8, shall become 1.
 *  Be caution that 'time' value and low-link value is different.
 *  
 *  Thus from parent node, after dfs on its child, the parent can check on child's low-link value. If the low-link value
 *  is higher than its own 'time' value, then it can be safely said that there is no cycles from that parent node to child.
 *  A bridge is found.	
 *  Vice versa, if child's low-link value is lower or equal to parent's 'time' value, that indicates a cycle is found linking
 *  back to parent itself, or even its ancestor! Therefore no bridges.
 *  
 *  
 */

public class Tarjans_Algorithm_Strongly_Connected_Components_Graph {
	
	
	//	Modified from Leetcode Problem - Link see above.
	//	Returns list of bridges.
	//	Undirected graph. n is the number of vertices. edges are array size 2 - From, to
	public List<List<Integer>> tarjan_algo(int n, List<List<Integer>> edges) {
		//	Construction of the graph
		List<Integer>[] graph = new List[n];
		for (List<Integer> edge: edges) {
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
		//	Previously visited. Immediately return value
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
		
		//	Do not update lowlink[node] in midst of exploring children! Because further children may link back
		//  to this node, and get incorrect lowlink value (Supposedly, children shall get 'time' value if visit this parent)
		lowlink[node] = dfs_min;
		return dfs_min;
	}
}
