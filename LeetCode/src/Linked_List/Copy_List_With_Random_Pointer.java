package Linked_List;

import java.util.HashMap;

//https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1229/

/*
 * 	To copy a linked list with random pointers, a more intuitive way to solve it is to use mapping so that the node from original linked list points to the
 * 	duplicated node, which can be used in constructing the deep copy of the linked list
 * 
 * 	Starting to iterating from the head, we check in each iteration if the current node has a duplicate already due to previous node's random pointer. If no,
 * 	then create a duplicate and put it into the mapping as well. If yes, just extract it and assign it as the previous node's next.
 * 	Now take a look at original node's random pointer. Check again if the mapping already contains the duplicate node constructed. If no, create one and assign it
 * 	to duplicate node's random pointer, else, extract it and assign it.
 * 
 * 	---------------------------------------------------------------------------------------------------
 * 
 * 	To avoid using O(n) space (excluding the extra space due to copying) due to hash map, we could use Weaving technique, but it requires 3 iterations in total
 * 	-The first iteration is just duplicating the nodes and attaching it to the node's next
 * 	-The second iteration will involve only the duplicated nodes, assigning their random pointer to the original one random's next (Which is duplicated in last iteration)
 * 	-The third iteration will revert the changes by extracting the duplicated nodes, and undo the changes on the original linked list so that the original is back
 * 		to normal
 */

public class Copy_List_With_Random_Pointer {
	class Node {
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
		
		Node newHead = new Node(0);
		Node tail = newHead;
		HashMap<Node, Node> map = new HashMap<>();
		
		while (head != null) {
			
			map.putIfAbsent(head, new Node(head.val) );
			Node copy = map.get(head);
			
			if (head.random != null) {
				map.putIfAbsent(head.random, new Node(head.random.val) );
				copy.random = map.get(head.random);
			}
			tail.next = copy;
			tail = tail.next;
			head = head.next;
		}
		
		return newHead.next;
	}
	
	public Node copyRandomListSpace(Node head) {
		if (head == null) return null;
		
		Node iterate = head;
		
		//First iteration: Duplication of nodes
		while (iterate != null) {
			Node temp = iterate.next;
			iterate.next = new Node(iterate.val);
			iterate.next.next = temp;
			
			iterate = temp;
		}
		iterate = head;
		//Second iteration: Assigning random pointers
		while (iterate != null) {
			Node copy = iterate.next;
			if (iterate.random != null) {
				copy.random = iterate.random.next;
			}
			iterate = iterate.next.next;
		}
		//Third iteration: Revert changes and extracting duplicated linked list
		iterate = head;
		Node newhead = iterate.next;
		
		while (iterate != null) {
			//This is the duplicated node
			Node copy = iterate.next;
			//The node that is supposed to be iterated next loop
			Node nextOri = iterate.next.next;
			
			//If copy is not the last node, which means there must be another duplicated node in front
			if (copy.next != null) {
				copy.next = copy.next.next;
			}
			iterate.next = nextOri;
			iterate = nextOri;
		}
		return newhead;
	}
	
	
}
