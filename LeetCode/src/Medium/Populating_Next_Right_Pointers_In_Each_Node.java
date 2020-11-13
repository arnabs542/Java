package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

/*
 * 	This is a Tree Depth First Search/ Breadth First Search Problem
 * 
 * 	First, the most intuitive way is that, if we obtain the nodes layer by layer, and in an orderly manner from left
 * 	to right, then we could keep connecting the node to the next node in line. This way they get connected easily
 * 
 * 	This technique uses Breadth First Search, and is easily implemented using a Queue, since FIFO rule applies.
 * 	Just be careful to know which node is in the current layer. Don't go accidentally connect the next layer's node!
 * 
 * 	==============================================================================================
 * 
 * 	How could we do this using recursion, which is Depth First Search?
 * 	
 * 	The hardest part of the problem isn't to connect the left and right child, but actually to connect those who
 * 	don't really share a direct parent, Like:
 * 
 * 					( )
 * 					/ \
 * 				  ( ) ( )
 * 				  / \ / \
 * 				( )(A)(B)( )
 * 	
 * 	Indeed. using Depth First Search, how would we know that A and B are supposed to be connected?
 * 	The solution is, we have a recursive function which will take in TWO, 2 parent nodes as arguments. This 2 parent nodes
 * 	know that the middle nodes have to be connected well!
 * 
 * 	Basically, this recursive function will do following:
 *			if node is null return
 *			connect the left node's children
 *			connect the right node's children
 *			connect the left node's right to right node's left (The 'Gap' is now connected)
 *
 *			recurse(left node's children)
 *			recurse(right node's children)
 *			recurse(left node's right & right node's left)     (This ensures that the layer below will always be connected)
 *		
 *	==============================================================================================
 *
 *	Introducing the technique to perform Level order traversal using the next pointer in each node
 *
 *	We will keep a leftmost pointer, which only stores the leftmost node in a level. Then, treet this node as parent node
 *	of children. We will conenct the children together.
 *	Now, do we have access to the node next to right children? Yes! 
 *	We keep track of the current parent node, and we can access it's next node in level. The next node's left children
 *	is the node we have to connect!
 */

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node() {}
    public Node(int _val) { val = _val; }
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class Populating_Next_Right_Pointers_In_Each_Node {
	
	//	Breadth First Search Solution
	public Node connect(Node root) {
		if (root == null) return null;
		Deque<Node> bfs = new ArrayDeque<>();
		bfs.offer( root );
		
		while ( !bfs.isEmpty() ) {
			
			//	Get Layer size
			int layerSize = bfs.size();
			
			//	Ensure don't go over layer size!
			while (layerSize-- > 0) {
				Node n = bfs.poll();
				//	If it is not the last element in the layer. The last element remains pointing to null
				if ( layerSize > 0 ) {
					n.next = bfs.peek();
				}

				if (n.left != null) {
					bfs.offer(n.left);
					bfs.offer(n.right);
				}
			}
		}
		
		return root;
    }
	
	
	//	Depth First Search solution
	public Node connect2(Node root) {
		//	Base case: no connection to do
		if (root == null || root.left == null) return root;
		
		//	First 2 nodes need to manually conenct
		root.left.next = root.right;
		
		recurse(root.left, root.right);
		return root;
	}
	private void recurse(Node left, Node right) {
		if (left == null) return;
		connect(left.left, left.right);
		connect(right.left, right.right );
		connect(left.right, right.left );
		
		recurse(left.left, left.right);
		recurse(left.right, right.left);
		recurse(right.left, right.right);
	}
	private void connect(Node left, Node right) {
		if (left == null) return;
		left.next = right;
	}
	
	
	
	//	Optimized Iteration Solution
	public Node connect3(Node root) {
		if (root == null) return null;
		
		Node leftmost = root;
		while (leftmost != null) {
			Node curr = leftmost;
			while (curr != null) {
				connect(curr.left, curr.right);
				if (curr.next != null) {
					connect(curr.right, curr.next.left);
				}
				curr = curr.next;
			}
			leftmost = leftmost.left;
		}
		return root;
}
}
