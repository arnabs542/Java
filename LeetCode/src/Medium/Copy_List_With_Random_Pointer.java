package Medium;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/copy-list-with-random-pointer/
/*
 * 	This is obviously a Linked List problem, with one solution using HashMap
 * 
 * 	One easy solution is to use a HashMap which maps from original node to cloned node. This way
 * 	when cloning the Linked List, we can know easily whether a node has been cloned before or not
 * 	when assigning next node or random node.
 * 
 * 	O(N) time with O(N) space cost
 * 
 * 	-----------------------------------------------------------------
 * 
 * 	Let's try constant space solution. The core problem is able to:
 * 		>	Know a cloned node exists or not
 * 		>	Able to retrieve cloned node in O(1) without going through linked list
 * 
 * 	Given constant space, we can try to modify the Linked List itself! We just have to return it to
 * 	original form before finishing.
 * 
 * 	(A) -> (A clone) -> (B) -> (B clone) -> (C) -> (C clone) ...
 * 
 * 	For each original node, create its clone node, and put it as original node's next. Cloned node's
 * 	next will be the next original node.
 * 
 * 	Then, in another loop, since we know already that original node's next is the cloned node, assigning
 * 	random pointer is easy.
 * 
 * 	Finally, return original linked list back to original state. For each original node, get reference to
 * 	its clone node, then:
 * 		>	Original node will connect back to clone node's next
 * 		>	Clone node will connect to next original node's next (Cloned), if exists.
 * 
 * 	Time is O(3N) but O(1) space complexity
 */

public class Copy_List_With_Random_Pointer {
	private class Node {
		int val;
		Node next;
		Node random;
		
		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
	
	public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);
        Node tail = dummy;
        
        while (head != null) {
        	Node node = map.getOrDefault( head, new Node(head.val) );
        	map.put( head , node);
        	
        	if (head.random != null) {
        		Node rand = map.getOrDefault( head.random , new Node(head.random.val) );
        		map.put( head.random, rand);
        		node.random = rand;
        	}
        	
        	tail.next = node;
        	tail = tail.next;
        	head = head.next;
        }
        
        return dummy.next;
    }
	
	
	
	public Node copyRandomList2(Node head) {
		if (head == null) return null;
		Node pt;
		
		//	Insert clone node into old node's next
		for (pt = head; pt != null; pt = pt.next.next) {
			Node clone = new Node(pt.val);
			clone.next = pt.next;
			pt.next = clone;
		}
		
		//	Assign randoms
		for (pt = head; pt != null; pt = pt.next.next){
			if (pt.random != null) 
				pt.next.random = pt.random.next;
		}
		
		Node cloned = head.next;
		//	Disconnect
		for (pt = head; pt != null; pt = pt.next) {
			Node clone = pt.next;
			pt.next = clone.next;
			if (pt.next != null) clone.next = pt.next.next;
		}
		
		return cloned;
	}
	
	
	
}
