package Medium;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

/*
 * 	This is a Tree BFS / DFS problem. The hard part is to solve it in O(1) space 
 * 
 * 	The most easiest implementation is to use Queue to perform level order search. At each iteration,
 * 	the nodes at the same level should all be placed in Queue. Taking note of the number of nodes in the
 * 	queue, pop them out and connect one to the next, except the last one. Also push the child nodes into the
 * 	queue as well for next level iteration (Be careful not to connect across levels!)
 * 
 * 	-------------------------------------------------------------------------------------------------
 * 
 * 	To avoid using Stack, we should use the fact that from top-down, the upper level must all have the next
 * 	pointer already populated. How would we use the upper level's next pointer to aid us in connecting the nodes
 * 	in current level?
 * 
 * 	For DFS solution, preferably we should always encounter the leftmost node in the level, and upper level
 * 	has all next pointer populated. In that way:
 * 		
 * 		As soon as we encounter a node, check if the node's rightmost child node is connected or not. If not,
 * 		start the connection process across the whole level.
 * 		This is done by using the fact that current node has the next pointer already populated. Using loops,
 * 		find the next closest node to connect.
 * 		Once connected, this is not finished. We shouldn't leave the connection done halfway in a level. Set
 * 		current node to that closest node, and continue the process until hit the level's end.
 * 
 * 	Then, we recurse on the lower nodes, knowing this level connection is established, it is safe to recurse.
 * 
 * 	---------------------------------------------------------------------------------------------------
 * 
 * 	The most ingenious solution is using the concept of linked list. 
 * 
 * 	Start from top level - Level 1.
 *  
 * 	While the node is not null:
 * 	
 * 		Set up a linked list equivalent structure for the level:
 * 		>	Create a dummy node, and set tail to be dummy node.
 * 		>	While node is not null, then
 * 				if node left exists, connect it to linked list, and update tail
 * 				then
 * 				if node right exists, connect it to linked list, and update tail
 * 			Once done, proceed to the node's next
 * 
 * 		>	Once the node hit null, it means the level is done traversing. How do we go to the next level's
 * 			leftmost node? Notice the dummy node is head of linked list, and the leftmost node is connected
 * 			next to head! Go to it!!!
 * 
 */

public class Populating_Next_Right_Pointers_In_Each_Node_II {
	
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;
	    public Node() {}
	    public Node(int _val) {
	        val = _val;
	    }
	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	}

	public Node connect(Node root) {
		if (root == null) return null;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        
        while (!queue.isEmpty() ) {
        	int levelSize = queue.size();
        	
        	while (levelSize-- > 1) {
        		Node node = queue.poll();
        		node.next = queue.peekFirst();
        		
        		if (node.left != null) queue.offer(node.left);
        		if (node.right != null) queue.offer(node.right);
        	}
        	
        	//	Last node in the level shouldn't be connected
        	Node node = queue.pop();
        	if (node.left != null) queue.offer(node.left);
    		if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
	
	
	
	//	Own solution based on last problem. 
	public Node connect2(Node node) {
		if (node == null) return null;
		
		Node curr = node;
		Node rightmost = curr.right != null? curr.right: curr.left;   //The rightmost node of the current node.
		
		
		//	Loop to initiate the connecting process across a level
		//	Only iterate when current is not null (due to loop), rightmost node is not null (Have at least 1 child
		//	node) and rightmost next is still not set (to avoid repeat when a connected node is met again)
		while (curr != null && rightmost != null && rightmost.next == null) {
			//	Connect left to right if possible
			if (curr.left != null) {
				curr.left.next = curr.right;
			}
			
			//	Find the next node to connect for the rightmost node of current node.
			Node next = curr.next;
			
			//	Skip useless nodes which does not have any child node
			while (next != null && next.left == null && next.right == null ) next = next.next;
			
			//	If a node with child node is found, it can be connected.
			if (next != null) {
				rightmost.next = next.left != null? next.left: next.right;
				curr = next;
				rightmost = next.right != null? next.right: next.left;
			}
			//	Otherwise we reached at the end of level. Break out of loop
			else break;
		}
		
		//	Recurse on left and right. Although this means the connected node would be met again, it will not
		//	iterate due to the rightmost node is connected (rightmost.next == null)
		connect( node.left  );
		connect( node.right  );
		
		return node;
	}
	
	
	
	
	//	Optimal Solution - Linked List-like solution ÅiReferred)
	public Node connect3(Node root) {
		Node curr = root;
		
		while (curr != null) {
			Node dummy = new Node();
			Node tail = dummy;
			
			while (curr != null) {
				if (curr.left != null) {
					tail.next = curr.left;
					tail = tail.next;
				}
				if (curr.right != null) {
					tail.next = curr.right;
					tail = tail.next;
				}
				curr = curr.next;
			}
			curr = dummy.next;
		}
		return root;
	}

	
}
