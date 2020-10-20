package Medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/clone-graph/

/*
 * 	This is a graph, DFS / BFS problem.
 * 
 *  To clone a undirected graph, the hardest part is to establish the undirected connection. How are we going to know:
 *  >	If a node had been created before?
 *  >	If the connection already been established?
 *  
 *  We could make a Hashmap, that maps from the original node to the cloned node itself, it can be node's value, or the
 *  node by itself as the key.
 *  Now, For the DFS approach, we need to treat problems seperately, meaning we have to focus on the current node only.
 *  In the final node, the connections must be well established, all the node references must be already there in the 
 *  neighbors arraylist. 
 *  To achieve this, when we are iterating over the list of neighbors, try to call a DFS function on that neighbor node,
 *  so that it will return a node for us to establish a connection.
 *  What if later the connections are duplicated?
 *  By establishing the connection one way (From current node to the neighbor node, and not the other way around), we
 *  can ensure that no duplicate connection may occur, since in DFS it is already been established)
 *  
 *  Therefore the DFS must look like this.
 *  If the node already exists, return directly the node, so the parent recursive call can establish connection onto this
 *  node.
 *  Otherwise, create a new node, iterate over all neighbors and call dfs on them, and return this node.
 */
public class Clone_Graph {

	//	The Node class used in this problem
	class Node {
	    public int val;
	    public List<Node> neighbors;
	    
	    public Node() {
	        val = 0;
	        neighbors = new ArrayList<Node>();
	    }
	    
	    public Node(int _val) {
	        val = _val;
	        neighbors = new ArrayList<Node>();
	    }
	    
	    public Node(int _val, ArrayList<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	}
	
	/*
	 *  BFS solution, using queue to fully visit the nodes one by one
	 */
	public Node cloneGraph(Node node) {
		if (node == null) return null;
		
        Node[] nodeRef = new Node[101];
        nodeRef[node.val] = new Node( node.val, new ArrayList<>() );

        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        while ( !queue.isEmpty() ) {
        	Node pop = queue.poll();
        	Node copy = nodeRef[pop.val];
        	
        	for ( Node n: pop.neighbors ) {
        		if ( nodeRef[n.val] == null ) {
        			nodeRef[n.val] = new Node( n.val, new ArrayList<>() );
        			queue.add(n);
        		}
        		copy.neighbors.add( nodeRef[n.val] );
        	}
        	
        }
        return nodeRef[ node.val ];
    }
	
	
	/*
	 *  DFS solution using recursion
	 */
	public Node cloneGraph2( Node node ) {
		if (node == null) return null;
		
		Node[] nodeRef = new Node[101];
		return dfs( node, nodeRef );
	}
	
	private Node dfs( Node node, Node[] nodeRef ) {
		if (nodeRef[node.val] != null ) return nodeRef[node.val];
		
		nodeRef[ node.val ] = new Node( node.val, new ArrayList<>() );
		
		for ( Node n: node.neighbors ) {
			nodeRef[ node.val ].neighbors.add( dfs(n, nodeRef) );
		}
		
		return nodeRef[node.val];
	}
}
